package com.stephen.commonhelper.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Environment
import android.os.storage.StorageManager
import android.view.Gravity
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import kotlin.math.absoluteValue


// 跳转应用
fun jumpToAnotherApp(context: Context, packageName: String, activityName: String? = null) {
    context.run {
        // 无MainActivity可能会报错
        try {
            startActivity(
                if (activityName != null)
                    Intent().setComponent(ComponentName(packageName, activityName))
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                else
                    packageManager.getLaunchIntentForPackage(packageName)
                        ?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
        } catch (e: Exception) {
            e.message?.let { errorLog(it) }
        }
    }
}

/**
 * view animation
 * 默认从无到有，0f to 1f
 */
fun View.fadeInAnimation(duration: Int, startAlpha: Float = 0f, endAplha: Float = 1f) {
    val delta = endAplha - startAlpha
    MainScope().launch {
        alpha = startAlpha
        repeat(duration) {
            delay(1L)
            alpha = startAlpha + delta * (it.toFloat() / duration)
        }
    }
}

/**
 * 初始化窗口参数
 */
fun getLayouutParams(
    context: Context,
    windowType: Int,
    isAutoCenter: Boolean,
    startCoordinateX: Int = 800,
    startCoordinateY: Int = 200
) =
    WindowManager.LayoutParams().apply {
        //设置可以显示在状态栏上
        flags = (WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL)
        type = windowType
        //设置窗口长宽数据
        width = WindowManager.LayoutParams.MATCH_PARENT
        height = WindowManager.LayoutParams.MATCH_PARENT
        gravity = Gravity.START or Gravity.TOP
        //悬浮窗的开始位置，因为设置的是从左上角开始，所以屏幕左上角是x=0;y=0
        val dm = context.resources.displayMetrics
        val screenWidth = dm.widthPixels
        val screenHeight = dm.heightPixels
        x = if (isAutoCenter) (screenWidth - width) / 2 else startCoordinateX
        y = if (isAutoCenter) (screenHeight - height) / 2 else startCoordinateY
        format = PixelFormat.TRANSLUCENT
    }

fun getUSBDir(context: Context): String? {
    infoLog()
    val storageManager = context.getSystemService(StorageManager::class.java)
    storageManager.storageVolumes.forEach {
        // u盘一般识别为isRemovable的设备，但Pixel5上为isEmulated，待考察
        if (it.isEmulated) {
            // u盘路径
            if (Environment.MEDIA_MOUNTED == it.state) {
                val file = it.directory
                return file?.absolutePath
            }
        }
    }
    return null
}

@SuppressLint("SimpleDateFormat")
fun getCrruentTime(): String {
    val dateFormat = SimpleDateFormat("HH:mm")
    val crruenttime: String = dateFormat.format(System.currentTimeMillis())
    debugLog("当前时间：$crruenttime")
    return crruenttime
}

/**
 * 全屏，遮住Dock与状态栏
 */
fun Activity.setFullScreenMode() {
    val layoutParams = window.attributes
    layoutParams.layoutInDisplayCutoutMode =
        WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
    window.attributes = layoutParams
    window.insetsController?.apply {
        systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        hide(WindowInsets.Type.systemBars())
//        hide(WindowInsets.Type.statusBars())
//        hide(WindowInsets.Type.navigationBars())
    }
}

fun executeTerminal(command: String) {
    infoLog("command: $command")
    try {
        Runtime.getRuntime().exec(command)
    } catch (e: Exception) {
        e.message?.let { error(it) }
    }
}