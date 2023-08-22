plugins {
    id("kotlin")
}

dependencies {

    implementation(project(":common"))

    implementation(libs.coroutines)
    implementation(libs.kotlin.datetime)
}