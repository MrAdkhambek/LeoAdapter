import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.adkhambek.app.Config
import com.adkhambek.app.configureKotlinAndroid
import com.adkhambek.app.configureTestAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<BaseAppModuleExtension> {
                configureKotlinAndroid(this)
                configureTestAndroid(this)
                defaultConfig.targetSdk = Config.targetSdkVersion
            }
        }
    }
}
