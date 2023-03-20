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

    object SqlDelight {
        const val android = "app.cash.sqldelight:android-driver:${Versions.sqlDelight}"
        const val native = "app.cash.sqldelight:native-driver:${Versions.sqlDelight}"
    }

    object Kodein {
        const val core = "org.kodein.di:kodein-di:${Versions.kodein}"
    }

    object MokoMvvm {
        const val core = "dev.icerock.moko:mvvm-core:${Versions.mokoMvvm}"
        const val flow = "dev.icerock.moko:mvvm-flow:${Versions.mokoMvvm}"
        const val flowCompose = "dev.icerock.moko:mvvm-flow-compose:${Versions.mokoMvvm}"
    }

    object Tests {
        const val junit = "junit:junit:${Versions.junit}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
    }

    object Utils {
        const val napier = "io.github.aakira:napier:${Versions.napier}"
        const val libresCompose = "io.github.skeptick.libres:libres-compose:${Versions.libres}"
        const val okio = "com.squareup.okio:okio:${Versions.okio}"
    }

    object Coil {
        const val core = "io.coil-kt:coil:${Versions.coil}"
        const val compose = "io.coil-kt:coil-compose:${Versions.coil}"
    }
}