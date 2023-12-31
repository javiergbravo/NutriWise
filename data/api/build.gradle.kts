plugins {
    id("kotlin")
}

dependencies {

    implementation(project(":common:core"))
    implementation(project(":common:app"))

    implementation(libs.coroutines)
    implementation(libs.kotlin.datetime)
}