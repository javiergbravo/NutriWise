pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "NutriWise"
include(":app")
include(":data")
include(":domain")
include(":common:app")
include(":common:core")
include(":data:impl")
include(":data:api")
include(":data:di")
include(":ui")
include(":ui:feature")
include(":ui:api")
include(":ui:di")
include(":ui:navigation")
