import com.icarumbas.casto.CoinCapApiIdsQueries
import com.icarumbas.casto.MarketDataQueries
import com.icarumbas.casto.api.market.CoinCapMarketDataApi
import com.icarumbas.casto.models.DomainMarketData
import com.icarumbas.casto.utils.CurrentTimeProvider

class MarketDataRepository(
    private val coinCapMarketDataApi: CoinCapMarketDataApi,
    private val coinCapIdsQueries: CoinCapApiIdsQueries,
    private val marketDataQueries: MarketDataQueries,
    private val currentTimeProvider: CurrentTimeProvider,
) {

    suspend fun getMarketData(ticker: String): DomainMarketData? {
        val localMarketData = marketDataQueries.selectByTicker(ticker).executeAsOneOrNull()
        if (localMarketData != null) {
            val currentTime = currentTimeProvider.getCurrentTimeMils()
            if (currentTime - localMarketData.lastUpdate < 5000) {
                val storageData = marketDataQueries.selectByTicker(ticker).executeAsOne()
                return DomainMarketData.fromStorage(storageData)
            }
        }
        val localCoinCapId = coinCapIdsQueries.selectByTicker(ticker).executeAsOneOrNull()
        if (localCoinCapId == null) {
            val assetListResponse = coinCapMarketDataApi.searchCoinCapAssets(ticker) ?: return null
            val assetResponse = assetListResponse.data.find { it.symbol == ticker } ?: return null

            marketDataQueries.insert(
                assetResponse.symbol,
                assetResponse.changePercent24Hr.toDouble(),
                assetResponse.priceUsd.toDouble(),
                assetListResponse.timestamp
            )
            coinCapIdsQueries.insert(assetResponse.symbol, assetResponse.id)
            return DomainMarketData.fromCoinCapResponse(assetResponse)
        } else {
            val assetListResponse = coinCapMarketDataApi.getCoinCapAsset(localCoinCapId) ?: return null
            val assetResponse = assetListResponse.data.first()
            marketDataQueries.insert(
                assetResponse.symbol,
                assetResponse.changePercent24Hr.toDouble(),
                assetResponse.priceUsd.toDouble(),
                assetListResponse.timestamp
            )
            return DomainMarketData.fromCoinCapResponse(assetResponse)
        }
    }
}