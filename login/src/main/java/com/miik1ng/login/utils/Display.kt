package com.miik1ng.login.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

private fun init(context: Context): DisplayMetrics? {
    val windowManager: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    var displayMetrics: DisplayMetrics? = null
    displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics
}

fun getWindowWidthPixels(context: Context): Int {
    return init(context = context)!!.widthPixels
}

fun getWindowHeightPixels(context: Context): Int {
    return init(context = context)!!.heightPixels
}

fun dp2px(context: Context, dp: Float): Int {
    return (dp * init(context)!!.density + 0.5f).toInt()
}

fun px2dp(context: Context, px: Float): Int {
    return (px / init(context)!!.density + 0.5f).toInt()
}