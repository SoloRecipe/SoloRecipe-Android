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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.design_system.component.SoloRecipeAppBar
import com.project.design_system.component.SoloRecipeCommentItem
import com.project.design_system.component.SoloRecipeStepItem
import com.project.design_system.component.SoloRecipeTextField
import com.project.design_system.theme.Body0
import com.project.design_system.theme.Body3
import com.project.design_system.theme.IcEmptyHeart
import com.project.design_system.theme.IcFullHeart
import com.project.design_system.theme.SoloRecipeTheme
import com.project.domain.model.review.request.ReviewRequestModel
import com.project.presentation.R
import com.project.presentation.viewmodel.detail.DetailViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    index: Long,
    detailViewModel: DetailViewModel = hiltViewModel(),
    stepItemCount: Int,
    commentListCount: Int
) {
    LaunchedEffect(Unit) {
        detailViewModel.getRecipeDetail(index)
    }

    Column(modifier = modifier.fillMaxSize()) {
        SoloRecipeAppBar { }
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(SoloRecipeTheme.color.Background)
                .padding(horizontal = 26.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Spacer(modifier = modifier.height(16.dp))
            DetailTitle { detailViewModel.likeRecipe() }
            Spacer(modifier = modifier.height(26.dp))
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                repeat(stepItemCount) {
                    SoloRecipeStepItem(
                        modifier = modifier.fillMaxWidth(),
                        imageUrl = "https://example.com",
                        content = "돈까스용 돼지고기(등심),밀가루,\n" +
                                "빵가루,계란,식용유,허브솔트를 \n" +
                                "준비해주세요"
                    )
                }
            }
            Spacer(modifier = modifier.height(40.dp))
            Body3(text = "댓글")
            Spacer(modifier = modifier.height(20.dp))
            MyComment(index = index) { index, content ->
                detailViewModel.writeReview(
                    recipeIndex = index,
                    body = ReviewRequestModel(content = content)
                )
            }
            Spacer(modifier = modifier.height(30.dp))
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                repeat(commentListCount) {
                    CommentList()
                }
            }
            Spacer(modifier = modifier.height(21.dp))
        }
    }
}

@Composable
fun DetailTitle(
    modifier: Modifier = Modifier,
    onLike: (Boolean) -> Unit
) {
    var liked by remember { mutableStateOf(false) }

    Image(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(8.dp)),
        painter = painterResource(id = R.drawable.ic_logo),
        contentDescription = "title_image",
        contentScale = ContentScale.Crop
    )
    Spacer(modifier = modifier.height(9.dp))
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Body0(
            modifier = modifier.weight(1f),
            text = "10분만에 쉽게 만드는 돈가스돈가스돈가스돈가스돈가스돈가스돈가스돈가스돈가스",
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
fun CommentList(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        SoloRecipeCommentItem(
            imageUrl = "https://example.com",
            title = "테스트",
            content = "감사합니다 레시피 덕분에 돈까스를 성공적으로\n" +
                    "만들 수 있었습니다!!"
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