package com.miik1ng.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.miik1ng.login.config.Config
import com.miik1ng.login.messenger.Messenger

class LoginConfig(builder: Builder) {
    private val context: Context
    private val config: Config
    private val listener: OnButtonClickListener<MutableMap<String, String?>>?

    init {
        this.context = builder.context
        this.config = Config()
        config.themeColor = builder.themeColor
        config.backgroundDrawableRes = builder.backgroundDrawableRes
        config.backgroundColor = builder.backgroundColor
        config.logoDrawableRes = builder.logoDrawableRes
        config.fLeadIcon = builder.fLeadIcon
        config.sLeadIcon = builder.sLeadIcon
        config.fValue = builder.vFirst
        config.sValue = builder.vSecond
        config.fPlaceholder = builder.pFirst
        config.sPlaceholder = builder.pSecond
        config.showFCheckBox = builder.showFCheckBox
        config.showSCheckBox = builder.showSCheckBox
        config.fCheckBoxText = builder.cbtFirst
        config.sCheckBoxText = builder.cbtSecond
        config.fChecked = builder.fChecked
        config.sChecked = builder.sChecked
        config.buttonText = builder.btnText
        this.listener = builder.listener
    }

    @Suppress("UNCHECKED_CAST")
    private val iCallbackMessenger: ICallback<MutableList<Any>> = object : ICallback<MutableList<Any>> {
        override fun back(t: MutableList<Any>) {
            val map = t[0] as MutableMap<String, String?>
            if (listener != null) {
                val b: Boolean = listener.onClick(map)
                if (b) {
                    val activity: LoginActivity = t[1] as LoginActivity
                    activity.finish()
                }
            }
        }
    }

    fun start() {
        Messenger.getDefault()
            .register(
                context,
                MESSENGER_LOGINCONFIG_ENENT,
                mutableListOf<Any>().javaClass,
                iCallbackMessenger
            )
        val intent = Intent(context, LoginActivity().javaClass)
        val bundle = Bundle()
        bundle.putSerializable("LOGINCONFIG_KOTLIN_SERIALIZABLE_DATA", config)
        intent.putExtras(bundle)
        context.startActivity(intent)
    }

    class Builder(val context: Context) {
        var themeColor: Long? = null
        var backgroundDrawableRes: Int? = null
        var backgroundColor: Long? = null
        var logoDrawableRes: Int? = null
        var fLeadIcon: Int? = null
        var sLeadIcon: Int? = null
        var vFirst: String? = null
        var vSecond: String? = null
        var pFirst: String? = null
        var pSecond: String? = null
        var showFCheckBox: Boolean? = null
        var showSCheckBox: Boolean? = null
        var cbtFirst: String? = null
        var cbtSecond: String? = null
        var fChecked: Boolean? = null
        var sChecked: Boolean? = null
        var btnText: String? = null
        var listener: OnButtonClickListener<MutableMap<String, String?>>? = null

        fun setThemeColor(@ColorInt themeColor: Long): Builder {
            this.themeColor = themeColor
            return this
        }

        fun setBackgroundDrawableRes(@DrawableRes backgroundDrawableRes: Int): Builder {
            this.backgroundDrawableRes = backgroundDrawableRes
            return this
        }

        fun setBackgroundColor(@ColorInt backgroundColor: Long): Builder {
            this.backgroundColor = backgroundColor
            return this
        }

        fun setLogoDrawableRes(@DrawableRes logoDrawableRes: Int): Builder {
            this.logoDrawableRes = logoDrawableRes
            return this
        }

        fun setFLeadIcon(@DrawableRes fLeadIcon: Int): Builder {
            this.fLeadIcon = fLeadIcon
            return this
        }

        fun setSLeadIcon(@DrawableRes sLeadIcon: Int): Builder {
            this.sLeadIcon = sLeadIcon
            return this
        }

        fun setFValue(fValue: String): Builder {
            this.vFirst = fValue
            return this
        }

        fun setSValue(sValue: String): Builder {
            this.vSecond = sValue
            return this
        }

        fun setFPlaceholder(fPlaceholder: String): Builder {
            this.pFirst = fPlaceholder
            return this
        }

        fun setSPlaceholder(sPlaceholder: String): Builder {
            this.pSecond = sPlaceholder
            return this
        }

        fun isShowFCheckBox(showFCheckBox: Boolean): Builder {
            this.showFCheckBox = showFCheckBox
            return this
        }

        fun isShowSCheckBox(showSCheckBox: Boolean): Builder {
            this.showSCheckBox = showSCheckBox
            return this
        }

        fun setFCheckBoxText(fCheckBoxText: String): Builder {
            this.cbtFirst = fCheckBoxText
            return this
        }

        fun setSCheckBoxText(sCheckBoxText: String): Builder {
            this.cbtSecond = sCheckBoxText
            return this
        }

        fun setFChecked(fChecked: Boolean): Builder {
            this.fChecked = fChecked
            return this
        }

        fun setSChecked(sChecked: Boolean): Builder {
            this.sChecked = sChecked
            return this
        }

        fun setButtonText(buttonText: String): Builder {
            this.btnText = buttonText
            return this
        }

        fun addOnButtonClickListener(listener: OnButtonClickListener<MutableMap<String, String?>>): Builder {
            this.listener = listener
            return this
        }

        fun build(): LoginConfig {
            return LoginConfig(this)
        }
    }

    interface OnButtonClickListener<T> {
        fun onClick(t: T): Boolean
    }

    companion object {
        const val MESSENGER_LOGINCONFIG_ENENT: String = "MESSENGER_LOGINCONFIG_ENENT"
    }
}
