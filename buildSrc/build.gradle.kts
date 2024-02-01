plugins {
    `kotlin-dsl`
}

allprojects {

//    gradle.projectsEvaluated {
//        tasks.withType(JavaCompile::class.java) {
//            val path = rootDir.absolutePath + "/app/libs/framework-aaos.jar"
//            options.compilerArgs.add("-Xbootclasspath/p:$path")
//            val newFileList = mutableListOf(File(path))
//            options.bootstrapClasspath?.files?.let {
//                newFileList.addAll(it)
//            }
//            options.bootstrapClasspath = files(*newFileList.toTypedArray())
//        }
//    }

    repositories {
        google()
        maven("https://maven.aliyun.com/repository/google")
        maven("https://maven.aliyun.com/repository/public")
        maven("https://jitpack.io")
        mavenCentral()
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
    implementation("com.android.tools.build:gradle:8.1.1")
}
