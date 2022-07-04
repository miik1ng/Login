package com.miik1ng.login.ui

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miik1ng.login.R
import com.miik1ng.login.utils.getWindowWidthPixels
import com.miik1ng.login.utils.px2dp
import com.miik1ng.test.ui.theme.ThemeColor

@Suppress("NAME_SHADOWING")
@Composable
internal fun LoginPage(
    context: Context,
    @ColorInt themeColor: Long?,
    @DrawableRes backgroundDrawableRes: Int? = null,
    @ColorInt backgroundColor: Long?,
    @DrawableRes logoDrawableRes: Int? = null,
    @DrawableRes fLeadIcon: Int?,
    @DrawableRes sLeadIcon: Int?,
    fValue: String?,
    sValue: String?,
    fPlaceholder: String?,
    sPlaceholder: String?,
    showFCheckBox: Boolean? = null,
    showSCheckBox: Boolean? = null,
    fCheckBoxText: String? = null,
    sCheckBoxText: String? = null,
    fChecked: Boolean? = null,
    sChecked: Boolean? = null,
    buttonText: String?,
    onButtonClick: ((MutableMap<String, String?>) -> Unit)?
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(color = if (backgroundColor != null) Color(backgroundColor) else Color.White)
    ) {
        if (backgroundDrawableRes != null) {
            Image(
                painterResource(backgroundDrawableRes),
                "backgrounddrawableres",
                Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
        val spc = px2dp(context, (getWindowWidthPixels(context) / 6).toFloat()).dp
        var fValue by remember { mutableStateOf(fValue) }
        var sValue by remember { mutableStateOf(sValue) }
        Column(
            Modifier
                .align(Alignment.Center)
                .padding(spc, 0.dp, spc, 0.dp)
        ) {
            if (logoDrawableRes != null) {
                Image(
                    painterResource(id = logoDrawableRes),
                    "logodrawableres",
                    Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }
            Spacer(
                modifier = Modifier
                    .height(40.dp)
            )
            InputBox(
                context = context,
                text = fValue ?: "",
                placeholder = fPlaceholder ?: "",
                leadingIcon = fLeadIcon ?: R.drawable.dxnicm5hbwu,
                leadColor = if (themeColor != null) Color(themeColor) else ThemeColor,
                onChange = { fValue = it }
            )
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )
            InputBox(
                context = context,
                text = sValue ?: "",
                placeholder = sPlaceholder ?: "",
                leadingIcon = sLeadIcon ?: R.drawable.cgfzc3dvcmq,
                leadColor = if (themeColor != null) Color(themeColor) else ThemeColor,
                hiddenValue = true,
                onChange = { sValue = it }
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            var fChecked by remember { mutableStateOf(fChecked) }
            var sChecked by remember { mutableStateOf(sChecked) }

            if ((showFCheckBox == true) || (showSCheckBox == true)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        Modifier
                            .weight(1f)
                    ) {
                        if (showFCheckBox == true) {
                            CheckBoxGroup(
                                checked = fChecked ?: false,
                                text = fCheckBoxText ?: "记住密码",
                                color = CheckboxDefaults.colors(
                                    checkedColor = if (themeColor != null) Color(themeColor) else ThemeColor
                                ),
                                onCheckedChange = {
                                    fChecked = it
                                }
                            )
                        }
                    }
                    Box(
                        Modifier
                            .weight(1f)
                    ) {
                        if (showSCheckBox == true) {
                            CheckBoxGroup(
                                checked = sChecked ?: false,
                                text = sCheckBoxText ?: "自动登录",
                                color = CheckboxDefaults.colors(
                                    checkedColor = if (themeColor != null) Color(themeColor) else ThemeColor
                                ),
                                onCheckedChange = {
                                    sChecked = it
                                }
                            )
                        }
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .height(10.dp)
            )
            Button(
                onClick = {
                    //Toast.makeText(context, "$fValue,$sValue", Toast.LENGTH_SHORT).show()
                    val map: MutableMap<String, String?> = mutableMapOf()
                    map.put("fValue", fValue)
                    map.put("sValue", sValue)
                    if (showFCheckBox == true) {
                        map.put("fChecked", fChecked?.toString())
                    }
                    if (showSCheckBox == true) {
                        map.put("sChecked", sChecked?.toString())
                    }
                    if (onButtonClick != null) {
                        onButtonClick(map)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(10.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = if (themeColor != null) Color(themeColor) else ThemeColor
                )
            ) {
                Text(
                    buttonText ?: "登录",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 15.sp
                    ),
                )
            }
        }
    }
}

@Composable
private fun InputBox(
    context: Context,
    text: String = "",
    placeholder: String = "",
    @DrawableRes leadingIcon: Int? = null,
    hiddenValue: Boolean = false,
    leadColor: Color = Color.Blue,
    onChange: (String) -> Unit = { },
) {
    val radius = 7.dp
    var text by remember { mutableStateOf(text) }
    //val spc = px2dp(context, (getWindowWidthPixels(context) / 10).toFloat()).dp
    val spc = 40.dp
    Row(
        Modifier
            //.padding(10.dp, 5.dp, 10.dp, 5.dp)
            .fillMaxWidth()
            .height(spc)
            .clip(RoundedCornerShape(radius))
            .background(Color.White)
    ) {
        if (leadingIcon != null) {
            Image(
                painterResource(id = leadingIcon),
                "icon",
                Modifier
                    .background(color = leadColor)
                    //.clip(RoundedCornerShape(topStart = radius, bottomStart = radius))
                    .padding(4.dp)
                    .width(spc)
                    .height(spc)
            )
        }
        val focusRequester = remember {
            FocusRequester()
        }
        val focusManager = LocalFocusManager.current
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onChange(text)
            },
            Modifier
                .background(Color.White)
                .padding(10.dp, 7.dp, 10.dp, 7.dp)
                .fillMaxHeight()
                .clickable { focusRequester.requestFocus() }
                .focusRequester(focusRequester)
                .focusable(),
            singleLine = true,
            maxLines = 1,
            //placeholder = placeholder,
            textStyle = TextStyle(
                textAlign = TextAlign.Start,
                fontSize = 15.sp
            ),
            visualTransformation = if (hiddenValue) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardActions = KeyboardActions(onDone = {
                val imm: InputMethodManager =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                val view = (context as Activity).currentFocus
//                if (view == null) view = view!!(context)
                imm.hideSoftInputFromWindow(view!!.windowToken, 0)
                focusManager.clearFocus()
            })
        ) {
            if (text.isEmpty()) {
                Text(placeholder, color = Color(0xffb4b4b4), fontSize = 15.sp)
            }
            it()
        }
    }
}

@Composable
fun CheckBoxGroup(
    checked: Boolean = false,
    text: String = "",
    textStyle: TextStyle = TextStyle(color = Color.White),
    color: CheckboxColors = CheckboxDefaults.colors(),
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        var b by remember {
            mutableStateOf(checked)
        }
        Checkbox(
            checked = b,
            onCheckedChange = {
                b = it
                onCheckedChange(it)
            },
            colors = color
        )
        Text(
            text,
            style = textStyle
        )
    }
}