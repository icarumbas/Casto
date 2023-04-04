
import app.cash.sqldelight.coroutines.asFlow
import com.icarumbas.casto.CoinsInfoQueries
import com.icarumbas.casto.CoinsPriceQueries
import com.icarumbas.casto.HoldingsInfoQueries
import com.icarumbas.casto.api.portfolio.PortfolioApi
import com.icarumbas.casto.interactor.IconsInteractor
import com.icarumbas.casto.models.PortfolioData
import com.icarumbas.casto.utils.CurrentTimeProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface PortfolioInteractor {

    val portfolioFlow: StateFlow<PortfolioData>

    suspend fun loadPortfolio()
}

class PortfolioInteractorImpl(
    private val portfolioApi: PortfolioApi,
    private val currentTimeProvider: CurrentTimeProvider,
    private val iconsInteractor: IconsInteractor,
    private val coinsInfoQueries: CoinsInfoQueries,
    private val coinsPriceQueries: CoinsPriceQueries,
    private val holdingsInfoQueries: HoldingsInfoQueries
) : PortfolioInteractor {

    override val portfolioFlow = MutableStateFlow(PortfolioData(emptyList()))
    private val scope = CoroutineScope(Dispatchers.Default)

    init {
        scope.launch {
            holdingsInfoQueries.selectWithPricesAndInfo().asFlow()
                .onEach {
                    val data = it.executeAsOne()
                }
                .collect()
        }
    }

    override suspend fun loadPortfolio() {
        listOf(1).flatMap {  }
    }


}