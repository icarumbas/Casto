package com.icarumbas.casto.android.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.icarumbas.casto.android.R
import com.icarumbas.casto.android.core.designsystem.CastoIcons

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val textRes: Int,
) {
    PORTFOLIO(
        selectedIcon = CastoIcons.PortfolioFilled,
        unselectedIcon = CastoIcons.PortfolioOutline,
        textRes = R.string.navigation_portfolio,
    ),
    SETTINGS(
        selectedIcon = CastoIcons.SettingsFilled,
        unselectedIcon = CastoIcons.SettingsOutline,
        textRes = R.string.navigation_settings,
    ),
}