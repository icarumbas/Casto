plugins {
    val kotlinVersion = Versions.kotlin
    val agpVersion = Versions.agp
    val sqldelightVersion = Versions.sqlDelight
    val kswiftVersion = Versions.kswift

    kotlin(multiplatform).version(kotlinVersion).apply(false)
    kotlin(androidPlugin).version(kotlinVersion).apply(false)
    kotlin(serialization).version(kotlinVersion).apply(false)
    id(androidApp).version(agpVersion).apply(false)
    id(androidLib).version(agpVersion).apply(false)
    id(sqldelight_plugin).version(sqldelightVersion).apply(false)
    id(kswift_plugin).version(kswiftVersion).apply(false)
}

buildscript {
    dependencies {
        classpath("io.github.skeptick.libres:gradle-plugin:${Versions.libres}")
    }
}

repositories {
    mavenCentral()
}

subprojects {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}