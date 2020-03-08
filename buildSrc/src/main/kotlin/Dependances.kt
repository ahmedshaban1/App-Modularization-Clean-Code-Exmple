object AndroidConfig {

    const val COMPILE_SDK_VERSION = 29
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 29
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1"

    const val ID = "com.example.appmodularizationexample"
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}


interface BuildType {

    companion object {
        const val RELEASE = "release"
        const val DEBUG = "release"
    }

    val isMinifyEnabled: Boolean
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled = false
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled = true
}

object CoreVersion {
    const val KOTLIN = "1.3.61"
    const val NAVIGATION = "2.2.0"
    const val ANDROID_GRADLE = "3.5.3"
    const val KTLINT_GRADLE = "9.2.1"
    const val KTLINT = "0.34.2"
    const val DETEKT = "1.0.0"
}

object GradlePluginId {
    const val ANDROID_APP = "com.android.application"
    const val ANDROID_LIB = "com.android.library"
    const val ANDROID = "kotlin-android"
    const val ANDROID_EXT = "kotlin-android-extensions"
    const val SAFE_ARGS = "androidx.navigation.safeargs"
    const val BASE_GRADLE_PLUGIN = "base-gradle-plugin"
    const val KTLINT_GRADLE = "org.jlleitschuh.gradle.ktlint"
    const val KTLINT_MAVEN = "https://plugins.gradle.org/m2/"
    const val DETEKT = "io.gitlab.arturbosch.detekt"
}

object GradleClasspath {
    const val KOTLIN_PlUGIN = "gradle-plugin"
    const val ANDROID_GRADLE = "com.android.tools.build:gradle:${CoreVersion.ANDROID_GRADLE}"
    const val SAFE_ARGS =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${CoreVersion.NAVIGATION}"
    const val KTLINT_CLASSPATH = "org.jlleitschuh.gradle:ktlint-gradle:${CoreVersion.KTLINT_GRADLE}"
}


object LibraryDependency {
    object Version {
        const val SUPPORT_LIB = "1.1.0"
        const val CONSTRAINT = "1.1.3"
        const val KOIN = "2.0.1"
        val kotlin = "1.3.21"
        val gradle = "3.3.2"
        val compileSdk = 28
        val minSdk = 23
        val targetSdk = 28
        val appCompat = "1.1.0-alpha04"
        const val coreKtx = "1.1.0-alpha04"
        val constraintLayout = "1.1.3"
        val junit = "4.12"
        val androidTestRunner = "1.1.2-alpha02"
        val espressoCore = "3.2.0-alpha02"
        const val retrofit = "2.5.0"
        const val retrofitCoroutines = "0.9.2"
        const val retrofitGson = "2.4.0"
        const val gson = "2.8.5"
        const val okHttp = "3.12.1"
        val coroutines = "1.1.1"
        val koin = "1.0.2"
        val timber = "4.7.1"
        const val lifecycle = "2.1.0-alpha04"
        val nav = "2.0.0"
        val room = "2.1.0-alpha06"
        val recyclerview = "1.0.0"
        val safeArgs = "2.1.0-alpha01"
        val glide = "4.9.0"
        val mockwebserver = "2.7.5"
        val archCoreTest = "2.0.0"
        val androidJunit = "1.1.0"
        val mockk = "1.9.2"
        val fragmentTest = "1.1.0-alpha06"
        val databinding = "3.3.2"
    }

    const val KOTLIN_STD = "org.jetbrains.kotlin:kotlin-stdlib:${CoreVersion.KOTLIN}"
    const val CORE = "androidx.core:core-ktx:${Version.SUPPORT_LIB}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Version.SUPPORT_LIB}"
    const val MATERIAL = "com.google.android.material:material:${Version.SUPPORT_LIB}"
    const val CONSTRAINT = "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT}"
    const val NAVIGATION_FRAGMENT =
        "androidx.navigation:navigation-fragment-ktx:${CoreVersion.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${CoreVersion.NAVIGATION}"
    const val KOIN = "org.koin:koin-android:${Version.KOIN}"
    const val KOIN_VIEWMODEL = "org.koin:koin-androidx-viewmodel:${Version.KOIN}"
    const val KOIN_SCOPE = "org.koin:koin-androidx-scope:${Version.KOIN}"

    const val COREKTX = "androidx.core:core-ktx:${Version.coreKtx}"
    const val LIFECYCLEViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
    const val LIFECYCLEEXTENSIONS = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycle}"

    //retrofit
    const val RETROFITCOROUTINESADAPTER =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Version.retrofitCoroutines}"
    const val GSON = "com.google.code.gson:gson:${Version.gson}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val RETROFITGSONADAPTER = "com.squareup.retrofit2:converter-gson:${Version.retrofitGson}"
    const val HTTPLOGGER = "com.squareup.okhttp3:logging-interceptor:${Version.okHttp}"


}

object ModulesDependency {
    const val COMMON = ":libraries:common"
    const val REMOTE = ":data:remote"

}

object FeaturesDependency {
    const val prefix = ":features:"
    const val Splash = "${prefix}splash"
}

object TestLibraryDependency {
    object Version {
        const val JUNIT = "4.13"
        const val JUNIT_ANDROID = "1.1.1"
        const val ESPRESSO = "3.2.0"
    }

    const val JUNIT = "junit:junit:${Version.JUNIT}"
    const val JUNIT_ANDROID = "androidx.test.ext:junit:${Version.JUNIT_ANDROID}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Version.ESPRESSO}"
    const val KOIN = "org.koin:koin-test:${LibraryDependency.Version.KOIN}"
}