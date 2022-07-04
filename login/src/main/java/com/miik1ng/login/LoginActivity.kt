package com.miik1ng.login

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.miik1ng.login.config.Config
import com.miik1ng.login.messenger.Messenger
import com.miik1ng.login.ui.LoginPage

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val intent: Intent = intent
        val bundle: Bundle? = intent.extras
        val config: Config = bundle?.getSerializable("LOGINCONFIG_KOTLIN_SERIALIZABLE_DATA") as Config

        setContent {
            LoginPage(
                context = this,
                themeColor = config.themeColor,
                backgroundDrawableRes = config.backgroundDrawableRes,
                backgroundColor = config.backgroundColor,
                logoDrawableRes = config.logoDrawableRes,
                fLeadIcon = config.fLeadIcon,
                sLeadIcon = config.sLeadIcon,
                fValue = config.fValue,
                sValue = config.sValue,
                fPlaceholder = config.fPlaceholder,
                sPlaceholder = config.sPlaceholder,
                showFCheckBox = config.showFCheckBox,
                showSCheckBox = config.showSCheckBox,
                fCheckBoxText = config.fCheckBoxText,
                sCheckBoxText = config.sCheckBoxText,
                fChecked = config.fChecked,
                sChecked = config.sChecked,
                buttonText = config.buttonText,
                onButtonClick = {
                    Messenger.getDefault().send(it, LoginConfig.MESSENGER_LOGINCONFIG_ENENT)
                }
            )
        }
    }
}