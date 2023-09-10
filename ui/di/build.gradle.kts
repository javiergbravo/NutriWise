plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.jgbravo.nutriwise.ui.di"
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

    implementation(project(":ui:api"))
    implementation(project(":ui:feature"))
    implementation(project(":ui:navigation"))
    implementation(project(":domain"))

    implementation(libs.koin.android)
}