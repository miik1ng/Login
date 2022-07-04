package com.miik1ng.test

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.miik1ng.login.ICallback
import com.miik1ng.login.LoginConfig

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainPage(
                onClick = {
                    LoginConfig.Builder(this@MainActivity)
                        .setThemeColor(0xFF4393EF)
                        .setBackgroundColor(0xFF4393EF)
                        .setBackgroundDrawableRes(R.drawable.background)
                        .setLogoDrawableRes(R.drawable.logo)
                        .setFLeadIcon(R.drawable.username)
                        .setSLeadIcon(R.drawable.password)
                        .setFValue("mmm")
                        .setSValue("1234")
                        .setFPlaceholder("请输入用户名")
                        .setSPlaceholder("请输入密码")
                        .setButtonText("登录")
                        .isShowSCheckBox(true)
                        .isShowFCheckBox(true)
                        .setFChecked(true)
                        .setSChecked(false)
                        .addCallBack(object : ICallback<MutableMap<String, String?>> {
                            override fun back(t: MutableMap<String, String?>) {
                                val s: String = "${t["fValue"]},${t["sValue"]}"
                                Toast.makeText(this@MainActivity, s, Toast.LENGTH_SHORT).show()
                            }
                        })
                        .build()
                        .start()
                }
            )
        }
    }
}

@Composable
fun MainPage(onClick: () -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.Yellow)
    ) {
        Button(
            onClick = onClick,
            Modifier
                .align(Alignment.Center)
        ) {
            Text(
                text = "跳转",
                fontSize = 17.sp,
                color = Color.Green,
            )
        }
    }
}