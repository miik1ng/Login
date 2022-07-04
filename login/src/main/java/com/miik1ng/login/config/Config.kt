package com.miik1ng.login.config

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.miik1ng.login.ICallback
import java.io.Serializable

internal class Config : Serializable {
    @ColorInt var themeColor: Long? = null
    @DrawableRes var backgroundDrawableRes: Int? = null
    @ColorInt var backgroundColor: Long? = null
    @DrawableRes var logoDrawableRes: Int? = null
    @DrawableRes var fLeadIcon: Int? = null
    @DrawableRes var sLeadIcon: Int? = null
    var fValue: String? = null
    var sValue: String? = null
    var fPlaceholder: String? = null
    var sPlaceholder: String? = null
    var showFCheckBox: Boolean? = null
    var showSCheckBox: Boolean? = null
    var fCheckBoxText: String? = null
    var sCheckBoxText: String? = null
    var fChecked: Boolean? = null
    var sChecked: Boolean? = null
    var buttonText: String? = null
}