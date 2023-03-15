package com.icarumbas.casto.android.portfolio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.icarumbas.casto.android.core.designsystem.AppTypography

@Composable
fun PortfolioScreen() {
    Text(text = "Portfolio Screen")
    CoinsList(coinsData = )
}

@Composable
fun CoinsList(coinsData: List<PortfolioCoin>) {
    LazyColumn {
        items(coinsData) { coin ->
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
        Image(bitmap = coin.icon, contentDescription = null)
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