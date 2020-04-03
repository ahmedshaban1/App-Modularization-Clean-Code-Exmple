plugins {
    id(GradlePluginId.ANDROID_LIB)
    id(GradlePluginId.KAPT)
    id(GradlePluginId.BASE_GRADLE_PLUGIN)
    id(GradlePluginId.SAFE_ARGS)

}

dependencies {
    localRoomDependencies()
    implementation(project(ModulesDependency.COMMON))
    implementation(project(ModulesDependency.REMOTE))
    implementation(project(ModulesDependency.MODEL))
    implementation(project(ModulesDependency.LOCAL))

    addTestDependencies()


}
