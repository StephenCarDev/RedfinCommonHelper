pluginManagement {
    repositories {
        google()
        maven("https://maven.aliyun.com/repository/google")
        maven ("https://maven.aliyun.com/repository/public")
        maven ("https://maven.aliyun.com/repository/gradle-plugin")
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven ("https://maven.aliyun.com/repository/google")
        maven ("https://maven.aliyun.com/repository/public")
        maven ("https://jitpack.io")
        mavenCentral()
    }
}

rootProject.name = "RedfinCommonHelper"
include(":app")
include(":commonhelper")
