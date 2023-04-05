package com.icarumbas.casto.screens.portfolio

import PortfolioService
import com.icarumbas.casto.di.appDI
import com.icarumbas.casto.models.PortfolioCoinData
import dev.icerock.moko.mvvm.flow.CFlow
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.kodein.di.instance


data class PortfolioState(
    var isLoading: Boolean = true,
    var items: List<PortfolioCoinItem> = emptyList(),
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

    private val portfolioService: PortfolioService by appDI.instance()

    fun obtainIntent(intent: PortfolioIntent) {
        when (intent) {
            is PortfolioIntent.EnterScreen -> {
                fetchData()
            }
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            portfolioService.portfolioFlow.onEach {
                val
                _state.value = _state.value.copy(items = )
            }
        }
    }

    private fun storageCoinToPortfolioCoinItem(coin: PortfolioCoinData): PortfolioCoinItem {
        val priceChangePercent= coin.price.changePercent1h.toString()
        val priceIncrease = coin.price.changePercent1h > 0

        return PortfolioCoinItem(
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