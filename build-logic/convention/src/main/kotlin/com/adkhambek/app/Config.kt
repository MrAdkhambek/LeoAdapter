package com.adkhambek.app

import org.gradle.api.JavaVersion

internal object Config {
    const val compileSdkVersion = 33

    const val minSdkVersion = 21
    const val targetSdkVersion = 33

    val javaVersion = JavaVersion.VERSION_11

    val freeCompilerArgs = listOf(
        "-opt-in=kotlin.RequiresOptIn",
        "-Xjvm-default=all",
    )
}
