package com.icarumbas.casto.screens.portfolio

import dev.icerock.moko.mvvm.flow.*
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow


sealed class PortfolioState {
    object Loading : PortfolioState()

    data class Data(
        val items: List<PortfolioCoin>,
    ) : PortfolioState()

    object Empty: PortfolioState()
}

sealed class PortfolioIntent {
    object EnterScreen : PortfolioIntent()
}

sealed class PortfolioSideEffect {
    object DisplayErrorFetchingData : PortfolioSideEffect()
}

class PortfolioViewModel() : ViewModel() {

    private val _state = MutableStateFlow<PortfolioState>(PortfolioState.Loading)
    val state: CStateFlow<PortfolioState> = _state.cStateFlow()

    private val _sideEffects = MutableSharedFlow<PortfolioSideEffect>()
    val sideEffects: CFlow<PortfolioSideEffect> = _sideEffects.cFlow()

    fun obtainIntent(intent: PortfolioIntent) {
        val state = state.value
        when (state) {
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

    }

    private fun reduceEmpty(intent: PortfolioIntent) {
        when (intent) {
            PortfolioIntent.EnterScreen -> fetchData()
        }
    }

    private fun fetchData() {

    }
}