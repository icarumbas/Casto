plugins {
    id(androidApp)
    kotlin(androidPlugin)
    id(junit5) version Versions.junit5Plugin
}

android {
    namespace = "com.icarumbas.casto.android"
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = "com.icarumbas.casto.android"
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeKotlinCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))

    with(Deps.Compose) {
        val composeBom = platform(bom)
        implementation(composeBom)
        androidTestImplementation(composeBom)

        implementation(material3)
        implementation(toolingPreview)
        debugImplementation(uiTooling)
        implementation(activities)
        implementation(viewModels)
    }

    with(Deps.JUnit5) {
        testImplementation(api)
        testRuntimeOnly(engine)
    }

}