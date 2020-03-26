plugins {
    id(GradlePluginId.ANDROID_LIB)
    id(GradlePluginId.KAPT)
    id(GradlePluginId.BASE_GRADLE_PLUGIN)

}

dependencies {
    //network libs
    localRoomDependencies()
    diDependencies()
    implementation(project(ModulesDependency.MODEL))
}
