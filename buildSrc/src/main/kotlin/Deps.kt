object Deps {

    object Compose {
        const val bom = "androidx.compose:compose-bom:${Versions.composeBomVersion}"
        const val material3 = "androidx.compose.material3:material3"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val uiTooling = "androidx.compose.ui:ui-tooling"
        const val activities = "androidx.activity:activity-compose:${Versions.composeActivities}"
        const val viewModels = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModels}"
        const val iconsExtended = "androidx.compose.material:material-icons-extended"
    }

    object Coroutines {
        const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object JUnit5 {
        const val api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5}"
        const val engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}"
    }

    object Ktor {
        const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val android = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        const val ios = "io.ktor:ktor-client-darwin:${Versions.ktor}"
        const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
        const val jsonSerializer = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
    }

    object Serialization {
        const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
    }

    object DateTime {
        const val core = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.dateTime}"
    }

    object Navigation {
        const val uiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val compose = "androidx.navigation:navigation-compose:${Versions.navigation}"
    }
}