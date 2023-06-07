package com.project.presentation.view.registration

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.design_system.component.SoloRecipeAppBar
import com.project.design_system.component.SoloRecipeButton
import com.project.design_system.theme.Body2
import com.project.design_system.theme.Body3
import com.project.design_system.theme.IcCamera
import com.project.design_system.theme.SoloRecipeColor
import com.project.design_system.theme.SoloRecipeTypography
import com.project.domain.model.recipe.RecipeRequestModel
import com.project.domain.model.recipe.RecipesRequestModel
import com.project.presentation.viewmodel.registration.RegistrationViewModel

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    items: Int,
    registrationViewModel: RegistrationViewModel = hiltViewModel()
) {
    val list: List<RecipeRequestModel> = listOf()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SoloRecipeColor.White)
    ) {
        SoloRecipeAppBar { }
        Column(modifier = modifier.verticalScroll(rememberScrollState())) {
            Spacer(modifier = modifier.height(16.dp))
            Thumbnail()
            Spacer(modifier = modifier.height(9.dp))
            ThumbnailTitle()
            Spacer(modifier = modifier.height(30.dp))
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                repeat(items) {
                    StepItem()
                }
            }
            Spacer(modifier = modifier.height(25.dp))
            RecipeAddButton(
                name = "",
                thumbnail = "",
                recipeProcess = list,
                onClick = registrationViewModel::createRecipe
            )
            Spacer(modifier = modifier.height(50.dp))
            RecipeRegisterButton()
            Spacer(modifier = modifier.height(30.dp))
        }
    }
}

@Composable
fun Thumbnail(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(horizontal = 26.dp)
            .background(
                color = SoloRecipeColor.Secondary10,
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
fun StepItem(
    modifier: Modifier = Modifier,
) {
    var content by remember { mutableStateOf("") }

    Row(modifier = modifier.height(70.dp)) {
        Box(
            modifier = modifier
                .width(80.dp)
                .fillMaxHeight()
                .background(
                    color = SoloRecipeColor.Secondary10,
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
            TextField(
                value = content,
                hint = "레시피를 입력해주세요",
                textStyle = SoloRecipeTypography.body4,
                onValueChanged = { content = it }
            )
        }
    }
}

@Composable
fun ThumbnailTitle(modifier: Modifier = Modifier) {
    var title by remember { mutableStateOf("") }

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp),
        value = title,
        hint = "제목을 입력해주세요",
        textStyle = SoloRecipeTypography.body2,
        onValueChanged = { title = it }
    )
}

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    textColor: Color = SoloRecipeColor.Black,
    textStyle: TextStyle,
    onValueChanged: (String) -> Unit
) {
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    BasicTextField(
        value = value,
        textStyle = mergedTextStyle,
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
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Composable
fun RecipeAddButton(
    modifier: Modifier = Modifier,
    name: String,
    thumbnail: String,
    recipeProcess: List<RecipeRequestModel>,
    onClick: (RecipesRequestModel) -> Unit
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp),
        colors = ButtonDefaults.buttonColors(SoloRecipeColor.White),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, SoloRecipeColor.Primary10),
        onClick = {
            onClick(
                RecipesRequestModel(
                    name = name,
                    thumbnail = thumbnail,
                    recipeProcess = recipeProcess
                )
            )
        }
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