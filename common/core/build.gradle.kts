plugins {
    id("kotlin")
}

dependencies {

    api(project(":logger"))
    implementation(libs.coroutines)
    implementation(libs.kotlin.datetime)
}