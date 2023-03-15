package com.icarumbas.casto.android.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.icarumbas.casto.android.navigation.TopLevelDestination

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CastoApp(appState: AppState = rememberAppState()) {
    Surface(color = MaterialTheme.colorScheme.background) {
        Scaffold(
            modifier = Modifier,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = {
                AppBottomBar(
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination,
                )
            },
        ) { padding ->
            Text(text = "Hello Maxim!", Modifier.padding(padding))
        }
    }
}

@Composable
private fun AppBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = NavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = { if (selected) {
                    Icon(imageVector = destination.selectedIcon, contentDescription = null)
                } else {
                    Icon(imageVector = destination.unselectedIcon, contentDescription = null)
                }},
                modifier = modifier,
                enabled = true,
                label = { Text(stringResource(destination.iconTextId)) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NavigationDefaults.navigationSelectedItemColor(),
                    unselectedIconColor = NavigationDefaults.navigationContentColor(),
                    selectedTextColor = NavigationDefaults.navigationSelectedItemColor(),
                    unselectedTextColor = NavigationDefaults.navigationContentColor(),
                    indicatorColor = NavigationDefaults.navigationIndicatorColor(),
                ),
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

object NavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}