package com.project.presentation.view.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.design_system.component.SoloRecipeAppBar
import com.project.design_system.component.SoloRecipeCommentItem
import com.project.design_system.component.SoloRecipeStepItem
import com.project.design_system.component.SoloRecipeTextField
import com.project.design_system.theme.Body0
import com.project.design_system.theme.Body3
import com.project.design_system.theme.IcEmptyHeart
import com.project.design_system.theme.IcFullHeart
import com.project.design_system.theme.SoloRecipeTheme
import com.project.domain.model.recipe.response.RecipeDetailResponseModel
import com.project.domain.model.recipe.response.ReviewModel
import com.project.domain.model.review.request.ReviewRequestModel
import com.project.presentation.R
import com.project.presentation.viewmodel.detail.DetailViewModel
import com.project.presentation.viewmodel.util.UiState
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    index: String?,
    isLikedRecipe: Boolean?,
    detailViewModel: DetailViewModel = hiltViewModel(),
    navigateToPrevious: () -> Unit
) {
    val recipeIndex = checkNotNull(index)

    val recipeUiState by detailViewModel.recipeUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        detailViewModel.getRecipeDetail(recipeIndex.toLong())
    }

    when (val state = recipeUiState) {
        UiState.Loading -> {}
        is UiState.Success -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .systemBarsPadding()
            ) {
                SoloRecipeAppBar { navigateToPrevious() }
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(SoloRecipeTheme.color.Background)
                        .padding(horizontal = 26.dp)
                        .verticalScroll(rememberScrollState()),
                ) {
                    Spacer(modifier = modifier.height(16.dp))
                    DetailTitle(
                        recipeDetail = state.data,
                        isLikedRecipe = checkNotNull(isLikedRecipe)
                    ) {
                        detailViewModel.likeRecipe(index.toLong())
                    }
                    Spacer(modifier = modifier.height(26.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        val recipeProcess = state.data?.recipeProcess

                        repeat(recipeProcess?.size ?: 0) {
                            SoloRecipeStepItem(
                                modifier = modifier.fillMaxWidth(),
                                imageUrl = recipeProcess?.get(it)?.image ?: "",
                                content = recipeProcess?.get(it)?.description ?: ""
                            )
                        }
                    }
                    Spacer(modifier = modifier.height(40.dp))
                    Body3(text = "댓글")
                    Spacer(modifier = modifier.height(20.dp))
                    MyComment(index = recipeIndex.toLong()) { index, content ->
                        detailViewModel.writeReview(
                            recipeIndex = recipeIndex.toLong(),
                            body = ReviewRequestModel(content = content)
                        )
                        detailViewModel.getRecipeDetail(index)
                    }
                    Spacer(modifier = modifier.height(30.dp))
                    Column(
                        modifier = modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        repeat(state.data?.reviews?.size ?: 0) {
                            CommentList(review = state.data?.reviews?.get(it))
                        }
                    }
                    Spacer(modifier = modifier.height(21.dp))
                }
            }
        }

        UiState.Unauthorized -> {}
        UiState.NotFound -> {}
        else -> {}
    }
}

@Composable
fun DetailTitle(
    modifier: Modifier = Modifier,
    isLikedRecipe: Boolean,
    recipeDetail: RecipeDetailResponseModel?,
    onLike: (Boolean) -> Unit
) {
    var liked by remember { mutableStateOf(isLikedRecipe) }

    GlideImage(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(8.dp)),
        imageModel = { recipeDetail?.thumbnail },
        imageOptions = ImageOptions(contentScale = ContentScale.Crop)
    )
    Spacer(modifier = modifier.height(9.dp))
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Body0(
            modifier = modifier.weight(1f),
            text = recipeDetail?.name ?: "",
            maxLines = 2
        )
        Spacer(modifier = modifier.width(30.dp))
        if (liked) {
            IcFullHeart(
                modifier = modifier.clickable {
                    liked = !liked
                    onLike(liked)
                },
                contentDescription = "full_heart"
            )
        } else {
            IcEmptyHeart(
                modifier = modifier.clickable {
                    liked = !liked
                    onLike(liked)
                },
                contentDescription = "empty_heart"
            )
        }
    }
}

@Composable
fun MyComment(
    modifier: Modifier = Modifier,
    index: Long,
    writeReview: (recipeIndex: Long, body: String) -> Unit
) {
    var comment by remember { mutableStateOf("") }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .size(30.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "profile"
        )
        Spacer(modifier = modifier.width(17.dp))
        SoloRecipeTextField(
            value = comment,
            hint = "댓글 추가 하기",
            onValueChanged = { comment = it },
            keyboardActions = KeyboardActions(onDone = {
                writeReview(index, comment)
            })
        )
    }
}

@Composable
fun CommentList(
    modifier: Modifier = Modifier,
    review: ReviewModel?
) {
    Column(modifier = modifier.fillMaxWidth()) {
        SoloRecipeCommentItem(
            imageUrl = "",
            title = review?.userName ?: "",
            content = review?.content ?: ""
        )
        Spacer(modifier = modifier.height(5.dp))
        Divider(
            modifier = modifier
                .fillMaxWidth()
                .padding(5.dp),
            thickness = 1.dp,
            color = SoloRecipeTheme.color.Secondary10
        )
    }
}