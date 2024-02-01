import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    const val composeMaterial = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.compose}"

    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    // mqtt
    const val mqttClient = "org.eclipse.paho:org.eclipse.paho.client.mqttv3:${Versions.mqtt}"

    const val baseRecyclerViewAdapterHelper =
        "io.github.cymchad:BaseRecyclerViewAdapterHelper:${Versions.baseRecyclerViewAdapterHelper}"

    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"

    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    const val protobuf = "com.google.protobuf:protobuf-java:${Versions.protobuf}"

    const val mmkv = "com.tencent:mmkv:${Versions.mmkv}"
    const val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore}"

    const val ktxlifecycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ktxlifecycle}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    const val junit = "junit:junit:4.13.2"
    const val testJunit = "androidx.test.ext:junit:1.1.5"
    const val espresso = "androidx.test.espresso:espresso-core:3.5.1"
}

// 通过扩展函数的方式一次性导入库的所有依赖
fun DependencyHandler.room() {
    implementation(Dependencies.roomKtx)
    implementation(Dependencies.roomRuntime)
    kapt(Dependencies.roomCompiler)
}

fun DependencyHandler.testImplementation() {
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.testJunit)
    androidTestImplementation(Dependencies.espresso)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.gsonConverter)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLoggingInterceptor)
    implementation(Dependencies.gson)
    implementation(Dependencies.protobuf)
}

fun DependencyHandler.viewFrame() {
    implementation(Dependencies.appcompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintlayout)
    implementation(Dependencies.ktxlifecycle)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeRuntime)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiToolingPreview)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
}