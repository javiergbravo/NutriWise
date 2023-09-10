plugins {
    alias(libs.plugins.androidApplication) apply false
//    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.realm) apply false
    kotlin("android").version(libs.versions.kotlin).apply(false)
    kotlin("multiplatform").version(libs.versions.kotlin).apply(false)
}

tasks.withType(type = org.jetbrains.kotlin.gradle.internal.KaptGenerateStubsTask::class) {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}