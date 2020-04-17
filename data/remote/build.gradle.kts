plugins {
    id(GradlePluginId.ANDROID_LIB)
    id(GradlePluginId.KAPT)
    id(GradlePluginId.BASE_GRADLE_PLUGIN)
    id(GradlePluginId.SAFE_ARGS)

}

dependencies {
    implementation(project(ModulesDependency.COMMON))

    //network libs
    lifeCycleDependencies()
    diDependencies()
    networkDependencies()
}
