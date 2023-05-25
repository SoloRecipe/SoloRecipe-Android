package com.project.presentation.view.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.design_system.component.SoloRecipeButton
import com.project.design_system.component.SoloRecipeTextField
import com.project.design_system.theme.Body3
import com.project.design_system.theme.Body4
import com.project.design_system.theme.H0
import com.project.design_system.theme.IcBack
import com.project.design_system.theme.IcEyeClose
import com.project.design_system.theme.IcEyeOpen
import com.project.design_system.theme.SoloRecipeTheme
import com.project.design_system.theme.Subtitle1

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var isAgreed by remember { mutableStateOf(false) }
    var isPasswordShowed by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 26.dp)
    ) {
        Spacer(modifier = modifier.height(33.dp))
        SignUpHeader { }
        Spacer(modifier = modifier.height(52.dp))
        SignUpField(
            label = "아이디",
            value = id,
            hint = "아이디를 입력해 주세요.",
            onValueChanged = { id = it }
        )
        Spacer(modifier = modifier.height(32.dp))
        SignUpField(
            label = "비밀번호",
            value = password,
            hint = "비밀번호를 입력해 주세요.",
            visualTransformation = if (isPasswordShowed) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                if (isPasswordShowed) {
                    IcEyeOpen(
                        modifier = modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { isPasswordShowed = !isPasswordShowed }
                        ),
                        contentDescription = "open"
                    )
                } else {
                    IcEyeClose(
                        modifier = modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { isPasswordShowed = !isPasswordShowed }
                        ),
                        contentDescription = "open"
                    )
                }

            },
            onValueChanged = { password = it }
        )
        Spacer(modifier = modifier.height(32.dp))
        SignUpField(
            label = "비밀번호 확인",
            value = confirmPassword,
            hint = "비밀번호를 재 입력해 주세요.",
            visualTransformation = PasswordVisualTransformation(),
            onValueChanged = { confirmPassword = it }
        )
        Spacer(modifier = modifier.height(32.dp))
        SignUpField(
            label = "닉네임",
            value = nickname,
            hint = "닉네임을 입력해 주세요.",
            onValueChanged = { nickname = it }
        )
        Spacer(modifier = modifier.height(37.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            val buttonColor = if (isAgreed) SoloRecipeTheme.color.Primary10 else SoloRecipeTheme.color.Secondary10

            Box(
                modifier = modifier
                    .size(20.dp)
                    .background(buttonColor)
                    .clickable { isAgreed = !isAgreed }
            )
            Spacer(modifier = modifier.width(20.dp))
            Body3(text = "[필수] 개인정보 3자 제공 동의")
            Spacer(modifier = modifier.height(10.dp))
        }
        Spacer(modifier = modifier.height(10.dp))
        Body4(
            modifier = modifier.padding(start = 40.dp),
            text = "자세히보기"
        )
        Spacer(modifier = modifier.height(50.dp))
        SoloRecipeButton(
            modifier = modifier.fillMaxWidth(),
            text = "가입하기",
            containerColor = SoloRecipeTheme.color.Primary10
        ) {

        }
    }
}

@Composable
fun SignUpField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    hint: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChanged: (String) -> Unit
) {
    Subtitle1(text = label)
    Spacer(modifier = modifier.height(3.dp))
    SoloRecipeTextField(
        value = value,
        hint = hint,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        onValueChanged = onValueChanged
    )
}

@Composable
fun SignUpHeader(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit
) {
    Box(modifier = modifier.fillMaxWidth()) {
        IcBack(
            modifier = modifier
                .align(Alignment.CenterStart)
                .clickable { onBackPressed() },
            contentDescription = "back",
        )
        H0(
            modifier = modifier.align(Alignment.Center),
            text = "회원가입"
        )
    }
}

@Preview
@Composable
fun PreviewSignUpScreen() {
    SignUpScreen()
}