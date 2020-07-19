plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    maven
    `maven-publish`
}

android{
    compileSdkVersion(BuildVersions.compileSdkVersion)
    buildToolsVersion = BuildVersions.buildToolsVersion

    defaultConfig {
        minSdkVersion(BuildVersions.minSdkVersion)
        targetSdkVersion(BuildVersions.targetSdkVersion)
        versionCode = BuildVersions.versionCode
        versionName = BuildVersions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFile("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies{
    implementation(Deps.kotlin.stdlib.core)
    implementation(Deps.kotlin.coroutines.core)
    implementation(Deps.kotlin.coroutines.android)


    implementation(Deps.android.stdLib.appCompat)
    implementation(Deps.android.stdLib.constraintLayout)

    implementation(project(":progressview"))

    testImplementation(Deps.android.test.junit)
    testImplementation(Deps.android.test.ext)
    testImplementation(Deps.android.test.espresso)
}