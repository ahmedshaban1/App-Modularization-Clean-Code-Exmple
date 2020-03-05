plugins {
    id(GradlePluginId.ANDROID_APP)
    id(GradlePluginId.BASE_GRADLE_PLUGIN)
}

dependencies {
    implementation(LibraryDependency.APPCOMPAT)
    implementation(LibraryDependency.CONSTRAINT)
}