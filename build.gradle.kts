@file:Suppress(
    "DSL_SCOPE_VIOLATION",
    "UnstableApiUsage"
)

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Android plugins
    alias(libs.plugins.android.app) apply false
    alias(libs.plugins.android.lib) apply false

    // Kotlin plugins
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.android) apply false


    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)

    alias(libs.plugins.dokka) apply false
    alias(libs.plugins.publish)
}

allprojects {
    tasks.withType(KotlinCompile::class).all {
        kotlinOptions {
            jvmTarget = "11"
//            freeCompilerArgs = (freeCompilerArgs + Config.freeCompilerArgs).distinct()
        }
    }
}
