plugins {
    kotlin(multiplatform)
    id(androidLib)
    id(kswift_plugin)
    id(sqldelight_plugin)
    kotlin(cocoapods)
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "15.0"
        podfile = project.file("../iosApp/Podfile")
        version = "1.0"
        framework {
            baseName = "MultiPlatformLibrary"
            isStatic = false

            export(Deps.MokoMvvm.core)
            export(Deps.MokoMvvm.flow)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.Coroutines.common)

                with(Deps.Ktor) {
                    implementation(core)
                    implementation(contentNegotiation)
                    implementation(jsonSerializer)
                }
                with(Deps.MokoMvvm) {
                    implementation(core)
                    implementation(flow)
                }
                implementation(Deps.Serialization.json)
                implementation(Deps.DateTime.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Deps.Ktor.android)

                with(Deps.Compose) {
                    val composeBom = platform(bom)
                    implementation(composeBom)

                    implementation(material3)
                    implementation(toolingPreview)
                    implementation(activities)
                    implementation(viewModels)
                    implementation(iconsExtended)
                }

                with(Deps.Navigation) {
                    implementation(compose)
                    implementation(uiKtx)
                }
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(Deps.Ktor.ios)
            }

            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = ConfigData.compileSdkVersion
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeKotlinCompiler
    }
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("")
            schemaOutputDirectory.set(file("src/commonMain/sqldelight/data/schema"))
            migrationOutputDirectory.set(file("src/commonMain/sqldelight/data/migrations"))
        }
    }
}

kswift {
    install(dev.icerock.moko.kswift.plugin.feature.SealedToSwiftEnumFeature)
}