package com.icarumbas.casto.screens.portfolio

import com.icarumbas.casto.di.appDI
import dev.icerock.moko.mvvm.flow.CFlow
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.kodein.di.instance


data class PortfolioState(
    var isLoading: Boolean = true,
    var items: List<PortfolioCoin> = emptyList(),
)

sealed class PortfolioIntent {
    object EnterScreen : PortfolioIntent()
}

sealed class PortfolioSideEffect {
    object DisplayErrorFetchingData : PortfolioSideEffect()
}

class PortfolioViewModel : ViewModel() {

    private val _state = MutableStateFlow(PortfolioState())
    val state: CStateFlow<PortfolioState> = _state.cStateFlow()

    private val _sideEffects = MutableSharedFlow<PortfolioSideEffect>()
    val sideEffects: CFlow<PortfolioSideEffect> = _sideEffects.cFlow()

    private val coinsRepository: CoinsRepository by appDI.instance()

    fun obtainIntent(intent: PortfolioIntent) {
        when (intent) {
            is PortfolioIntent.EnterScreen -> {
                fetchData()
            }
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            val coins = coinsRepository.loadBinancePortfolio()
                .map(::storageCoinToPortfolioCoin)
            _state.value = PortfolioState(false, coins)
        }
    }

    private fun storageCoinToPortfolioCoin(coin: CoinAccountData): PortfolioCoin {
        val priceChangeStr = coin.priceChangePercent24.toString()
        val priceIncrease = coin.priceChangePercent24 >= 0
        val take = if (priceIncrease) {
            4
        } else {
            5
        }
        val priceChangePercent = priceChangeStr.take(take)
        return PortfolioCoin(
            iconPath = coin.iconPath,
            ticker = coin.ticker,
            price = coin.price.toString(),
            holdings = coin.holdings.toString(),
            holdingsPrice = coin.holdingsPrice.toString(),
            priceChangePercent = priceChangePercent,
            priceIncrease = priceIncrease
        )
    }
}