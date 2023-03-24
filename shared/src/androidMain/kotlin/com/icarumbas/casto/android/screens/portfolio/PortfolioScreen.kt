package com.icarumbas.casto.android.screens.portfolio

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.icarumbas.casto.Res
import com.icarumbas.casto.android.designsystem.ListItemHeadline
import com.icarumbas.casto.android.designsystem.ListItemSupporting
import com.icarumbas.casto.screens.portfolio.PortfolioCoin
import com.icarumbas.casto.screens.portfolio.PortfolioIntent
import com.icarumbas.casto.screens.portfolio.PortfolioState
import com.icarumbas.casto.screens.portfolio.PortfolioViewModel
import dev.icerock.moko.mvvm.flow.compose.observeAsActions
import io.github.aakira.napier.Napier
import io.github.skeptick.libres.compose.painterResource

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

    when (val state = viewState) {
        is PortfolioState.Data -> {
            CoinsList(coinsData = state.items)
        }
        else -> {

        }
    }

}

@Composable
fun CoinsList(
    coinsData: List<PortfolioCoin>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
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
fun CoinCard(
    coin: PortfolioCoin
) {
    val padding = 16.dp
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        CoinIcon(path = coin.iconPath)
        Spacer(modifier = Modifier.size(padding))
        Column {
            ListItemHeadline(text = coin.ticker)
            ListItemSupporting(text = coin.price)
        }
        Spacer(modifier = Modifier.weight(1f, true))
        Column {
            ListItemHeadline(text = coin.holdings)
            ListItemSupporting(text = coin.holdingsPrice, Modifier.align(Alignment.End))
        }
    }
}

@Composable
fun CoinIcon(path: String?) {
    val thumbPainter = painterResource(image = Res.image.coin_logo_stub)
    val modifier = Modifier.size(40.dp)
    if (path != null) {
        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(Uri.parse(path))
            .crossfade(true)
            .build()

        val onError: ((AsyncImagePainter.State.Error) -> Unit) = { error ->
            Napier.e("Error loading coin icon", error.result.throwable, "CoinIcon")
        }

        AsyncImage(
            model = imageRequest,
            contentDescription = null,
            error = thumbPainter,
            onError = onError,
            modifier = modifier,
            contentScale = ContentScale.FillHeight
        )
    } else {
        Image(
            painter = thumbPainter,
            contentDescription = null,
            modifier = modifier,
        )
    }
}