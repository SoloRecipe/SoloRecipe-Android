package com.project.presentation.view.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.design_system.component.SoloRecipeAppBar
import com.project.design_system.component.SoloRecipeItem
import com.project.design_system.theme.Body2
import com.project.design_system.theme.Body4
import com.project.design_system.theme.IcSearch
import com.project.design_system.theme.SoloRecipeColor
import com.project.design_system.theme.SoloRecipeTypography
import com.project.domain.model.recipe.response.RecipeResponseModel
import com.project.presentation.viewmodel.main.MainViewModel
import com.project.presentation.viewmodel.util.UiState

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel(),
    keyword: String = "",
    navigateToPrevious: () -> Unit,
    navigateToDetail: (Long) -> Unit,
) {
    var text by remember { mutableStateOf(keyword) }
    val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SoloRecipeColor.White)
            .systemBarsPadding()
    ) {
        SoloRecipeAppBar { navigateToPrevious() }
        Spacer(modifier = modifier.height(20.dp))
        SearchBar(
            keyword = keyword
        ) {
            text = it
            mainViewModel.searchRecipe(it)
        }

        when (val state = uiState) {
            is UiState.Success -> {
                Spacer(modifier = modifier.height(20.dp))
                RecipeList(recipes = state.data!!) {
                    navigateToDetail(it)
                }
            }

            UiState.Loading -> {}
            UiState.BadRequest -> {}
            UiState.Unauthorized -> {}
            else -> {}
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    keyword: String,
    onSearch: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(45.dp)
            .padding(horizontal = 26.dp)
            .background(
                color = SoloRecipeColor.Secondary10,
                shape = RoundedCornerShape(8.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = modifier.width(10.dp))
        IcSearch(
            contentDescription = "search"
        )
        Spacer(modifier = modifier.width(15.dp))
        TextField(
            modifier = modifier
                .fillMaxWidth(),
            value = keyword,
            hint = "검색어를 입력해주세요",
            textStyle = SoloRecipeTypography.body4,
            onValueChanged = { onSearch(it) }
        )
    }
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

    var text by remember { mutableStateOf(value) }

    BasicTextField(
        value = text,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(SoloRecipeColor.Primary10),
        onValueChange = {
            text = it
            onValueChanged(it)
        },
        keyboardActions = KeyboardActions(onSearch = { onValueChanged(text) }),
        decorationBox = { innerTextField ->
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    if (text.isEmpty()) {
                        Body2(
                            text = hint,
                            textColor = SoloRecipeColor.Black,
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Composable
fun RecipeList(
    modifier: Modifier = Modifier,
    recipes: List<RecipeResponseModel>,
    navigateToDetail: (idx: Long) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxWidth(),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(26.dp),
        verticalArrangement = Arrangement.spacedBy(17.dp),
        contentPadding = PaddingValues(vertical = 10.dp, horizontal = 26.dp)
    ) {
        items(recipes.size) {
            SoloRecipeItem(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { navigateToDetail(recipes[it].idx) }
                    ),
                imageUrl = recipes[it].thumbnail,
                content = { Body4(text = recipes[it].name) }
            )
        }
    }
}