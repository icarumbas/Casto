
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.icarumbas.casto.*
import com.icarumbas.casto.api.portfolio.PortfolioApi
import com.icarumbas.casto.api.portfolio.responses.PortfolioCoinInfoResponse
import com.icarumbas.casto.api.user.UserApi
import com.icarumbas.casto.service.IconsService
import com.icarumbas.casto.models.PortfolioCoinData
import com.icarumbas.casto.models.PortfolioData
import com.icarumbas.casto.storage.user.UserSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.*

interface PortfolioService {

    val portfolioFlow: StateFlow<PortfolioData>

    suspend fun saveBinanceCredentials(publicKey: String, privateKey: String)

    suspend fun loadPortfolio()
}

class PortfolioServiceImpl(
    private val portfolioApi: PortfolioApi,
    private val userApi: UserApi,
    private val iconsService: IconsService,
    private val holdingsInfoQueries: HoldingsInfoQueries,
    private val coinsInfoQueries: CoinsInfoQueries,
    private val coinsPriceQueries: CoinsPriceQueries,
    private val userSettings: UserSettings,
    private val database: Database,
) : PortfolioService {

    private val scope = CoroutineScope(Dispatchers.Default)

    override val portfolioFlow: StateFlow<PortfolioData> =
        holdingsInfoQueries.selectWithPricesAndInfo()
            .asFlow()
            .mapToList(scope.coroutineContext)
            .map { selectInfo ->
                val asyncResults = selectInfo.map {
                    scope.async {
                        it.toPortfolioCoinData()
                    }
                }
                asyncResults.awaitAll()
            }.map {
                PortfolioData(it)
            }.stateIn(scope, SharingStarted.WhileSubscribed(), PortfolioData(emptyList()))

    override suspend fun loadPortfolio() {
        val userId = loadUserId()
        val response = portfolioApi.getPortfolio(userId) ?: return

        database.transaction {
            response.coins.forEach { coin ->
                val coinId = coin.id
                with(coin.toCoinInfo()) {
                    coinsInfoQueries.insert(coinId, ticker, name)
                }

                with(coin.toCoinPrice()) {
                    coinsPriceQueries.insert(
                        coinId, price, changePercent1h, changePercent24h,
                        changePercent7d, changePercent14d, changePercent30d, changePercent60d,
                        changePercent1y
                    )
                }
                with(coin.toHoldingsInfo()) {
                    holdingsInfoQueries.insert(coinId, sourceId, holdings)
                }
            }
        }
    }

    override suspend fun saveBinanceCredentials(publicKey: String, privateKey: String) {
        if (userSettings.binanceSetUp) {
            return
        }
        
        val id = loadUserId()
        val success = portfolioApi.saveBinanceCredentials(id, publicKey, privateKey)
        if (success) {
            userSettings.binanceSetUp = true
        }
    }

    private suspend fun loadUserId(): String {
        return userSettings.id.ifEmpty {
            val newId = userApi.getId()
            userSettings.id = newId
            newId
        }
    }

    private fun PortfolioCoinInfoResponse.toCoinInfo(): CoinsInfo {
        return CoinsInfo(id, ticker, name)
    }

    private fun PortfolioCoinInfoResponse.toCoinPrice(): CoinsPrice {
        return CoinsPrice(
            id,
            price.toDouble(),
            priceChangePercent.changePercent1h.toDouble(),
            priceChangePercent.changePercent24h.toDouble(),
            priceChangePercent.changePercent7d.toDouble(),
            priceChangePercent.changePercent14d.toDouble(),
            priceChangePercent.changePercent30d.toDouble(),
            priceChangePercent.changePercent60d.toDouble(),
            priceChangePercent.changePercent1y.toDouble(),
        )
    }

    private fun PortfolioCoinInfoResponse.toHoldingsInfo(): HoldingsInfo {
        return HoldingsInfo(id, "Binance", holdings.toDouble())
    }

    private suspend fun SelectWithPricesAndInfo.toPortfolioCoinData(): PortfolioCoinData {
        val iconPath = iconsService.getIconPath(ticker)
        return PortfolioCoinData(
            info = CoinsInfo(id, ticker, name),
            price = CoinsPrice(id, price, changePercent1h, changePercent24h, changePercent7d,
                changePercent14d, changePercent30d, changePercent60d, changePercent1y),
            holdingsInfo = HoldingsInfo(id, sourceId, holdings),
            iconPath = iconPath
        )
    }
}