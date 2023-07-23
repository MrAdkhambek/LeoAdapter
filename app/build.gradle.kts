@file:Suppress(
    "DSL_SCOPE_VIOLATION",
    "UnstableApiUsage"
)

plugins {
    id("com.adkhambek.android.application")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        applicationId = "com.adam.leoadapterapp"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
//    implementation(projects.library.leo)
//    implementation(projects.library.paging)

    implementation("com.adkhambek.leo:leo:0.0.4")
    implementation("com.adkhambek.leo:leo-paging:0.0.4")

    implementation(libs.coreKtx)
    implementation(libs.activityKtx)

    implementation(libs.recyclerview)
    implementation(libs.viewpager2)

    implementation(libs.loremIpsum)
    debugImplementation(libs.leakcanary)
}
