package com.icarumbas.casto.screens.portfolio

import PortfolioService
import com.icarumbas.casto.di.appDI
import com.icarumbas.casto.models.PortfolioCoinData
import com.icarumbas.casto.platformSpecific.utils.roundDecimal
import dev.icerock.moko.mvvm.flow.CFlow
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
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
    class SetupBinance(val publicKey: String, val privateKey: String) : PortfolioIntent()
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
            is PortfolioIntent.SetupBinance -> {

            }
            else -> {}
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            portfolioService.portfolioFlow.collect {
                val items = it.coins.map(::storageCoinToPortfolioCoinItem)
                _state.value = _state.value.copy(items = items)
            }
        }

        viewModelScope.launch(Dispatchers.Default) {
            portfolioService.saveBinanceCredentials(
                "UxrWvc6moNEClFXXiam6243wHC8FJL3Th17nwqzf7eKbd1dSHoOQ30Wl7b4FkJaV",
                "X6dH4M0xxw5blzz9ejkHiVH28op03EFxTbEUUa4AzaxpqffUeDk9fTnQcFvC3WYp"
            )
            portfolioService.loadPortfolio()
        }
    }

    private fun setupBinance() {
        viewModelScope.launch {

        }
    }

    private fun storageCoinToPortfolioCoinItem(coin: PortfolioCoinData): PortfolioCoinItem {
        val price = coin.price
        val info = coin.info
        val holdings = coin.holdingsInfo
        val holdingsPrice = holdings.holdings * price.price

        val priceChangePercent = roundDecimal(price.changePercent1h, 2) + "%"
        val holdingsPriceStr = roundDecimal(holdingsPrice, 2)
        val priceStr = roundDecimal(price.price, 2)
        val holdingsStr = roundDecimal(holdings.holdings, 2)
        val priceIncrease = price.changePercent1h > 0

        return PortfolioCoinItem(
            iconPath = coin.iconPath,
            ticker = info.ticker,
            price = priceStr,
            holdings = holdingsStr,
            holdingsPrice = holdingsPriceStr,
            priceChangePercent = priceChangePercent,
            priceIncrease = priceIncrease
        )
    }
}