package com.stephen.commonhelper.utils

import android.content.ComponentName
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Environment
import android.os.storage.StorageManager
import android.util.TypedValue
import android.view.Gravity
import android.view.WindowManager
import android.widget.Toast
import com.stephen.commonhelper.base.appContext


// 跳转应用
fun jumpToAnotherApp(packageName: String, activityName: String? = null) {
    appContext.run {
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
 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
 */
fun Float.dp2px() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this,
    appContext.resources.displayMetrics
)

fun showSimpleToast(text: String) {
    Toast.makeText(appContext, text, Toast.LENGTH_SHORT).show()
}

/**
 * 初始化窗口参数
 */
fun getLayouutParams(
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
        val dm = appContext.resources.displayMetrics
        val screenWidth = dm.widthPixels
        val screenHeight = dm.heightPixels
        x = if (isAutoCenter) (screenWidth - width) / 2 else startCoordinateX
        y = if (isAutoCenter) (screenHeight - height) / 2 else startCoordinateY
        format = PixelFormat.TRANSLUCENT
    }

fun getUSBDir(): String? {
    infoLog()
    val storageManager = appContext.getSystemService(StorageManager::class.java)
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

fun executeTerminal(command: String) {
    infoLog("command: $command")
    try {
        Runtime.getRuntime().exec(command)
    } catch (e: Exception) {
        e.message?.let { error(it) }
    }
}