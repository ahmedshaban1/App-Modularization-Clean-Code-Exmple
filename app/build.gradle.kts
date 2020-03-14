plugins {
    id(GradlePluginId.ANDROID_APP)
    id(GradlePluginId.BASE_GRADLE_PLUGIN)
}

dependencies {
    commonDevelopmentDependencies()
    implementation(project(FeaturesDependency.Splash))
    implementation(project(ModulesDependency.REMOTE))

}