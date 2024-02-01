plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.stephen.commonhelper"
    compileSdk = 33

    defaultConfig {
        minSdk = 30

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    val appName = "RedfinCommonHelper"
    val versionName = "1.0.4"
    android.libraryVariants.configureEach {
        val buildType = this.buildType.name
        outputs.all {
            if (this is com.android.build.gradle.internal.api.LibraryVariantOutputImpl) {
                this.outputFileName = "${appName}_${versionName}_${buildType}.aar"
            }
        }
    }
}

dependencies {
    implementation(fileTree("libs").include("*.aar", "*.jar"))
    hilt()
    retrofit()
    viewFrame()
    testImplementation()
    implementation(Dependencies.mqttClient)
    implementation(Dependencies.mmkv)
    implementation(Dependencies.datastore)
}