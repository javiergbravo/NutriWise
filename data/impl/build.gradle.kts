plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.realm)
}

android {
    namespace = "com.jgbravo.nutriwise.data.impl"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation(project(":common:core"))
    implementation(project(":common:app"))
    implementation(project(":data:api"))
    implementation(project(":logger"))

    implementation(libs.koin.android)
    implementation(libs.coroutines)
    implementation(libs.realm)
    implementation(libs.kotlin.datetime)

    testImplementation(libs.junit)
}