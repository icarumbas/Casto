package com.icarumbas.casto.android.screens.portfolio

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.icarumbas.casto.android.designsystem.AppTypography
import com.icarumbas.casto.screens.portfolio.*
import dev.icerock.moko.mvvm.flow.compose.observeAsActions

@Composable
fun PortfolioScreen(
    viewModel: PortfolioViewModel = viewModel()
) {
    val viewState: PortfolioState by viewModel.state.collectAsState()
    viewModel.sideEffects.observeAsActions { sideEffect ->

    }

    LaunchedEffect(key1 = viewState, block = {
        viewModel.obtainIntent(intent = PortfolioIntent.EnterScreen)
    })

    Text(text = "Portfolio Screen")
    when (val state = viewState) {
        is PortfolioState.Data -> {
            CoinsList(coinsData = state.items)
        }
        else -> {

        }
    }

}

@Composable
fun CoinsList(coinsData: List<PortfolioCoin>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        items(
            items = coinsData,
            key = { message ->
                message.ticker
            }
        ) { coin ->
            CoinCard(coin)
        }
    }
}

@Composable
fun CoinCard(coin: PortfolioCoin) {
    val padding = 16.dp
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
    ) {
//        Image(bitmap = coin.icon, contentDescription = null)
        Spacer(modifier = Modifier.size(padding))
        Column {
            Text(text = coin.ticker, style = AppTypography.titleMedium)
            Text(text = coin.price, style = AppTypography.labelMedium)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(text = coin.holdings, style = AppTypography.titleMedium)
            Text(text = coin.holdingsPrice, style = AppTypography.labelMedium)
        }
    }

}