import com.icarumbas.casto.CoinCapApiIdsQueries
import com.icarumbas.casto.api.market.CoinCapMarketDataApi
import com.icarumbas.casto.models.DomainMarketData

class MarketDataRepository(
    private val coinCapMarketDataApi: CoinCapMarketDataApi,
    private val coinCapIdsQueries: CoinCapApiIdsQueries,
) {

    suspend fun getMarketData(ticker: String): DomainMarketData {
        val coinCapId = coinCapIdsQueries.selectByTicker(ticker).executeAsOneOrNull()?.id
        if (coinCapId == null) {

        }
    }
}