package com.icarumbas.casto.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.icarumbas.casto.android.designsystem.AppTheme
import com.icarumbas.casto.android.screens.CastoApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                CastoApp()
            }
        }
    }
}
