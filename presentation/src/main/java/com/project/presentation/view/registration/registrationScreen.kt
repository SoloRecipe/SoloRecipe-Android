package com.project.presentation.view.registration

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.design_system.component.SoloRecipeAppBar
import com.project.design_system.component.SoloRecipeButton
import com.project.design_system.theme.Body2
import com.project.design_system.theme.Body3
import com.project.design_system.theme.IcCamera
import com.project.design_system.theme.SoloRecipeColor
import com.project.design_system.theme.SoloRecipeTypography

@Composable
fun registrationScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SoloRecipeColor.White)
    ) {
        SoloRecipeAppBar { }
        Spacer(modifier = modifier.height(16.dp))
        thumbnail()
        Spacer(modifier = modifier.height(9.dp))
        thumbnailTitle()
        Spacer(modifier = modifier.height(30.dp))
        SoloRecipeRegiItem()
        Spacer(modifier = modifier.height(10.dp))
        RecipeAddButton()
        Spacer(modifier = modifier.weight(1f))
        RecipeRegisterButton()
        Spacer(modifier = modifier.height(30.dp))
    }
}

@Composable
fun thumbnail(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(horizontal = 26.dp)
            .background(
                color =  SoloRecipeColor.Secondary10,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        IcCamera(
            modifier = modifier
                .size(40.dp, 32.dp)
                .align(Center),
            contentDescription = "camera"
        )
    }
}

@Composable
fun SoloRecipeRegiItem(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
    ) {
        items(2) {
            SoloRecipeTestStepItem()
            Spacer(modifier = modifier.height(16.dp))
        }
    }
}

@Composable
fun SoloRecipeTestStepItem(
    modifier: Modifier = Modifier,
) {
    var content by remember { mutableStateOf("") }

    Row(modifier = modifier.height(70.dp)) {
        Box(
            modifier = modifier
                .width(80.dp)
                .fillMaxHeight()
                .background(
                    color =  SoloRecipeColor.Secondary10,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            IcCamera(
                modifier = modifier
                    .size(20.dp, 16.dp)
                    .align(Center),
                contentDescription = "camera"
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = SoloRecipeColor.Secondary10
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(10.dp)
        ) {
            testTextField(
                value = content,
                hint = "레시피를 입력해주세요",
                textStyle = SoloRecipeTypography.body4,
                onValueChanged = { content = it }
            )
        }
    }
}

@Composable
fun thumbnailTitle(modifier: Modifier = Modifier) {
    var title by remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
    ) {
        testTextField(
            value = title,
            hint = "제목을 입력해주세요",
            textStyle = SoloRecipeTypography.body2,
            onValueChanged = { title = it }
        )
    }
}

@Composable
fun testTextField(
    modifier: Modifier = Modifier,
    value: String,
    enabled: Boolean = true,
    hint: String,
    textColor: Color = SoloRecipeColor.Black,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    textStyle: TextStyle,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChanged: (String) -> Unit
) {
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    BasicTextField(
        value = value,
        enabled = enabled,
        singleLine = singleLine,
        maxLines = maxLines,
        textStyle = mergedTextStyle,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        cursorBrush = SolidColor(SoloRecipeColor.Primary10),
        onValueChange = onValueChanged,
        decorationBox = { innerTextField ->
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    if (value.isEmpty()) {
                        Body2(
                            text = hint,
                            textColor = SoloRecipeColor.Secondary10,
                            maxLines = maxLines
                        )
                    }
                    innerTextField()
                }
                Spacer(modifier = modifier.weight(1f))
            }
        }
    )
}

@Composable
fun RecipeAddButton(modifier: Modifier = Modifier) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
            .border(
                width = 1.dp,
                color = SoloRecipeColor.Primary10,
                shape = RoundedCornerShape(8.dp)
            ),
        contentPadding = PaddingValues(vertical = 9.dp),
        colors = ButtonDefaults.buttonColors(SoloRecipeColor.White),
        onClick = { }
    ) {
        Body3(
            text = "추가하기",
            textColor = SoloRecipeColor.Primary10,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun RecipeRegisterButton(modifier: Modifier = Modifier) {
    SoloRecipeButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp),
        text = "등록하기",
        containerColor = SoloRecipeColor.Primary10
    ) {}
}

@Preview
@Composable
fun preview() {
    registrationScreen()
}