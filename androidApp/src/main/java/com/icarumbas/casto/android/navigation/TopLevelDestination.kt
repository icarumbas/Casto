package com.icarumbas.casto.android.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.icarumbas.casto.android.R
import com.icarumbas.casto.android.core.designsystem.AppIcons

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val textRes: Int,
) {
    PORTFOLIO(
        selectedIcon = AppIcons.PortfolioFilled,
        unselectedIcon = AppIcons.PortfolioOutline,
        textRes = R.string.navigation_portfolio,
    ),
    SETTINGS(
        selectedIcon = AppIcons.SettingsFilled,
        unselectedIcon = AppIcons.SettingsOutline,
        textRes = R.string.navigation_settings,
    ),
}