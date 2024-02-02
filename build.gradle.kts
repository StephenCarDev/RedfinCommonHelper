buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.hiltAgp)
    }
}

plugins { id("maven-publish") }

group = "com.github.StephenCarDev"
