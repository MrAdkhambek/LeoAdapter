@file:Suppress(
    "UnstableApiUsage",
)

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

class PublisherPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.vanniktech.maven.publish")
                apply("org.jetbrains.dokka")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("dokkaHtmlPlugin", libs.findLibrary("dokka-java").get())
            }
        }
    }

    private fun Project.getLocalProperty(key: String, file: String = "local.properties"): Any {
        val properties = Properties()
        val localProperties = File(rootDir, file)

        if (localProperties.isFile) {
            InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
                properties.load(reader)
            }
        } else {
            error("File from not found")
        }

        return properties.getProperty(key)
    }
}
