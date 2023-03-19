package com.icarumbas.casto.screens.portfolio

import com.icarumbas.casto.di.appDI
import com.icarumbas.casto.repository.CoinsRepository
import com.icarumbas.casto.storage.models.StorageCoin
import dev.icerock.moko.mvvm.flow.CFlow
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.kodein.di.instance


sealed interface PortfolioState {
    object Loading : PortfolioState

    data class Data(
        val items: List<PortfolioCoin>,
    ) : PortfolioState

    object Empty: PortfolioState
}

sealed class PortfolioIntent {
    object EnterScreen : PortfolioIntent()
}

sealed class PortfolioSideEffect {
    object DisplayErrorFetchingData : PortfolioSideEffect()
}

class PortfolioViewModel : ViewModel() {

    private val _state = MutableStateFlow<PortfolioState>(PortfolioState.Loading)
    val state: CStateFlow<PortfolioState> = _state.cStateFlow()

    private val _sideEffects = MutableSharedFlow<PortfolioSideEffect>()
    val sideEffects: CFlow<PortfolioSideEffect> = _sideEffects.cFlow()

    private val coinsRepository: CoinsRepository by appDI.instance()

    fun obtainIntent(intent: PortfolioIntent) {
        when (val state = _state.value) {
            is PortfolioState.Loading -> reduceLoading(intent)
            is PortfolioState.Data -> reduceData(intent)
            is PortfolioState.Empty -> reduceEmpty(intent)
        }
    }

    private fun reduceLoading(intent: PortfolioIntent) {
        when (intent) {
            PortfolioIntent.EnterScreen -> fetchData()
        }
    }

    private fun reduceData(intent: PortfolioIntent) {
        when (intent) {
            PortfolioIntent.EnterScreen -> fetchData()
        }
    }

    private fun reduceEmpty(intent: PortfolioIntent) {
        when (intent) {
            PortfolioIntent.EnterScreen -> fetchData()
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            val coins = coinsRepository.getAllCoins()
                .map(::storageCoinToPortfolioCoin)
            _state.value = PortfolioState.Data(coins)
        }
    }

    private fun storageCoinToPortfolioCoin(coin: StorageCoin): PortfolioCoin {
        return PortfolioCoin(
            ticker = coin.ticker,
            price = coin.price.toString(),
            holdings = coin.holdings.toString(),
            holdingsPrice = coin.holdingsPrice.toString()
        )
    }
}