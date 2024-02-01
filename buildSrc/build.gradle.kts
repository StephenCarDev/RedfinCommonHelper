plugins {
    `kotlin-dsl`
}

allprojects {

    repositories {
        google()
        maven("https://maven.aliyun.com/repository/google")
        maven("https://maven.aliyun.com/repository/public")
        maven("https://jitpack.io")
        mavenCentral()
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
    implementation("com.android.tools.build:gradle:7.3.1")
}
