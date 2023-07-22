@file:Suppress(
    "DSL_SCOPE_VIOLATION",
    "UnstableApiUsage"
)

plugins {
    id("com.adkhambek.android.library")
    id("com.adkhambek.publish")
}

android {
    kotlinOptions {
        freeCompilerArgs = (freeCompilerArgs + listOf("-Xexplicit-api=warning")).distinct()
    }
}

dependencies {
    api(projects.library.core)
    compileOnly(libs.viewbinding)

    compileOnly(libs.viewpager2)
    compileOnly(libs.recyclerview)
}
