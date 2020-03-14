import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler


/*
Define common dependencies, so they can be easily updated across feature modules
 */
fun DependencyHandler.addTestDependencies() {
    testImplementation(TestLibraryDependency.JUNIT)
    testImplementation(TestLibraryDependency.KOIN)
    androidTestImplementation(TestLibraryDependency.JUNIT_ANDROID)
    androidTestImplementation(TestLibraryDependency.ESPRESSO)
}

fun DependencyHandler.commonDevelopmentDependencies() {
    api(LibraryDependency.APPCOMPAT)
    api(LibraryDependency.CONSTRAINT)
    api(LibraryDependency.KOIN)
    api(LibraryDependency.KOIN_SCOPE)
    api(LibraryDependency.KOIN_VIEWMODEL)
    api(LibraryDependency.LIFECYCLEEXTENSIONS)
    api(LibraryDependency.COREKTX)
    api(LibraryDependency.LIFECYCLEViewModel)

}

fun DependencyHandler.diDependencies() {
    api(LibraryDependency.KOIN)
    api(LibraryDependency.KOIN_SCOPE)
    api(LibraryDependency.KOIN_VIEWMODEL)
}

fun DependencyHandler.networkDependencies() {
    api(LibraryDependency.RETROFITCOROUTINESADAPTER)
    api(LibraryDependency.GSON)
    api(LibraryDependency.RETROFIT)
    api(LibraryDependency.RETROFITGSONADAPTER)
    api(LibraryDependency.HTTPLOGGER)
    api(LibraryDependency.coroutines_core)
    api(LibraryDependency.coroutines_android)

}

/*
 * These extensions mimic the extensions that are generated on the fly by Gradle.
 * They are used here to provide above dependency syntax that mimics Gradle Kotlin DSL syntax in module\build.gradle.kts.kts files.
 */
fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

private fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

private fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)
