package com.project.presentation.view.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.project.design_system.component.SoloRecipeAppBar
import com.project.design_system.component.SoloRecipeItem
import com.project.design_system.theme.IcPencil
import com.project.design_system.theme.IcProfile
import com.project.design_system.theme.IcSearch
import com.project.design_system.theme.SoloRecipeTheme
import com.project.design_system.theme.Subtitle2
import com.project.presentation.R
import kotlinx.coroutines.launch

private const val RECOMMEND = 0
private const val ALL = 1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                containerColor = SoloRecipeTheme.color.Primary10,
                shape = CircleShape,
                onClick = { }
            ) {
                IcPencil(
                    tint = SoloRecipeTheme.color.White,
                    contentDescription = "write_recipe"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SoloRecipeAppBar(
                startIcon = {
                    Image(
                        modifier = modifier.size(30.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_logo),
                        contentDescription = "logo"
                    )
                },
                endIcons = {
                    IcSearch(
                        modifier = modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { }
                        ),
                        contentDescription = "search"
                    )
                    Spacer(modifier = modifier.width(28.dp))
                    IcProfile(
                        modifier = modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { }
                        ),
                        contentDescription = "profile"
                    )
                },
                onStartIconClick = { }
            )
            Spacer(modifier = modifier.height(10.dp))
            TabBar(currentPage = pagerState.currentPage) {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(it)
                }
            }
            Spacer(modifier = modifier.height(12.dp))
            HorizontalPager(
                modifier = modifier.padding(horizontal = 26.dp),
                state = pagerState,
                pageCount = 2,
                pageSpacing = 26.dp
            ) { page ->
                RecipeList {
                    if (page == RECOMMEND) {
                        Column {
                            SoloRecipeItem(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        onClick = { }
                                    ),
                                imageUrl = "",
                                recipeName = "첫번째 아이템!"
                            )
                            Spacer(modifier = modifier.height(20.dp))
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun TabBar(
    modifier: Modifier = Modifier,
    currentPage: Int,
    tabItemClick: (Int) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Subtitle2(
            modifier = modifier
                .background(
                    color = if (currentPage == RECOMMEND) SoloRecipeTheme.color.Primary20 else Color.Transparent,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(
                    horizontal = 18.dp,
                    vertical = 5.dp
                ),
            text = "추천",
            onClick = { tabItemClick(RECOMMEND) }
        )
        Spacer(modifier = modifier.width(15.dp))
        Subtitle2(
            modifier = modifier
                .background(
                    color = if (currentPage == ALL) SoloRecipeTheme.color.Primary20 else Color.Transparent,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(
                    horizontal = 18.dp,
                    vertical = 5.dp
                ),
            text = "전체",
            onClick = { tabItemClick(ALL) },
        )
    }
}

@Composable
fun RecipeList(
    modifier: Modifier = Modifier,
    spanItem: (@Composable () -> Unit)? = null
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxWidth(),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(26.dp),
        verticalArrangement = Arrangement.spacedBy(17.dp),
        contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        spanItem?.let { spanItem ->
            item(span = { GridItemSpan(2) }) {
                spanItem()
            }
        }
        items(10) {
            SoloRecipeItem(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { }
                    ),
                imageUrl = "https://example.com",
                recipeName = "돈가스"
            )
        }
    }
}