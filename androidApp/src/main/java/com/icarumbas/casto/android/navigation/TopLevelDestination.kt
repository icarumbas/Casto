package com.icarumbas.casto.android.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.icarumbas.casto.android.R
import com.icarumbas.casto.android.core.designsystem.CastoIcons

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    WALLET(
        selectedIcon = CastoIcons.PortfolioFilled,
        unselectedIcon = CastoIcons.PortfolioOutline,
        iconTextId = R.string.navigation_wallet,
        titleTextId = R.string.navigation_wallet,
    ),
    SETTINGS(
        selectedIcon = CastoIcons.SettingsFilled,
        unselectedIcon = CastoIcons.SettingsOutline,
        iconTextId = R.string.navigation_settings,
        titleTextId = R.string.navigation_settings,
    ),
}