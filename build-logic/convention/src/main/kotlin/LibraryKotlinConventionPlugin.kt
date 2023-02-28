@file:Suppress(
    "UnstableApiUsage",
)

import com.adkhambek.app.Config
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class LibraryKotlinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
            }

            tasks.withType(KotlinCompile::class).all {
                kotlinOptions {
                    jvmTarget = Config.javaVersion.toString()
                    freeCompilerArgs = (freeCompilerArgs + Config.freeCompilerArgs).distinct()
                }
            }
        }
    }
}
