package com.project.presentation.view.auth.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.project.design_system.component.SoloRecipeButton
import com.project.design_system.component.SoloRecipeTextField
import com.project.design_system.theme.Body3
import com.project.design_system.theme.H0
import com.project.design_system.theme.SoloRecipeTheme
import com.project.design_system.theme.Subtitle1
import com.project.presentation.R

@Composable
fun SignInScreen(modifier: Modifier = Modifier) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 26.dp),
    ) {
        Spacer(modifier = modifier.height(120.dp))
        Image(
            modifier = modifier.align(Alignment.CenterHorizontally),
            imageVector = ImageVector.vectorResource(R.drawable.ic_logo),
            contentDescription = "logo"
        )
        Spacer(modifier = modifier.height(35.dp))
        H0(
            modifier = modifier.align(Alignment.CenterHorizontally),
            text = "혼시피"
        )
        Spacer(modifier = modifier.weight(1f))
        Subtitle1(text = "아이디")
        Spacer(modifier = modifier.height(5.dp))
        SoloRecipeTextField(
            value = id,
            hint = "아이디를 입력해 주세요.",
            onValueChanged = { id = it }
        )
        Spacer(modifier = modifier.height(32.dp))
        Subtitle1(text = "비밀번호")
        Spacer(modifier = modifier.height(5.dp))
        SoloRecipeTextField(
            value = password,
            hint = "비밀번호를 입력해 주세요",
            onValueChanged = { password = it }
        )
        Spacer(modifier = modifier.height(45.dp))
        SoloRecipeButton(
            modifier = modifier.fillMaxWidth(),
            text = "로그인",
            containerColor = SoloRecipeTheme.color.Primary10
        ) {

        }
        Spacer(modifier = modifier.height(32.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Body3(text = "계정이 없으신가요?")
            Spacer(modifier = modifier.width(7.dp))
            Body3(
                modifier = modifier.clickable { },
                text = "회원가입",
                textColor = SoloRecipeTheme.color.Primary10,
            )
        }
        Spacer(modifier = modifier.height(53.dp))
    }
}