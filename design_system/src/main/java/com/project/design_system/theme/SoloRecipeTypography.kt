package com.project.design_system.theme

import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.project.design_system.R

val suit = FontFamily(
    Font(R.font.suit_bold, FontWeight.Bold),
    Font(R.font.suit_regular, FontWeight.Normal)
)

object SoloRecipeTypography {
    @Stable
    val h0 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
    )
    @Stable
    val subtitle0 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    )
    @Stable
    val subtitle1 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
    )
    @Stable
    val subtitle2 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    )
    @Stable
    val body0 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    )
    @Stable
    val body1 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp
    )
    @Stable
    val body2 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    @Stable
    val body3 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
    @Stable
    val body4 = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
}

@Composable
fun H0(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = SoloRecipeColor.Black,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    textOverflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.clickable(
            onClick = onClick ?: { },
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ),
        text = text,
        style = SoloRecipeTypography.h0,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Subtitle0(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = SoloRecipeColor.Black,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    textOverflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.clickable(
            onClick = onClick ?: { },
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ),
        text = text,
        style = SoloRecipeTypography.subtitle0,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Subtitle1(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = SoloRecipeColor.Black,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    textOverflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.clickable(
            onClick = onClick ?: { },
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ),
        text = text,
        style = SoloRecipeTypography.subtitle1,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Subtitle2(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = SoloRecipeColor.Black,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    textOverflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.clickable(
            onClick = onClick ?: { },
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ),
        text = text,
        style = SoloRecipeTypography.subtitle2,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Body0(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = SoloRecipeColor.Black,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    textOverflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.clickable(
            onClick = onClick ?: { },
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ),
        text = text,
        style = SoloRecipeTypography.body0,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Body1(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = SoloRecipeColor.Black,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    textOverflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.clickable(
            onClick = onClick ?: { },
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ),
        text = text,
        style = SoloRecipeTypography.body1,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Body2(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = SoloRecipeColor.Black,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    textOverflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.clickable(
            onClick = onClick ?: { },
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ),
        text = text,
        style = SoloRecipeTypography.body2,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Body3(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = SoloRecipeColor.Black,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    textOverflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.clickable(
            onClick = onClick ?: { },
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ),
        text = text,
        style = SoloRecipeTypography.body3,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Body4(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = SoloRecipeColor.Black,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    textOverflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    Text(
        modifier = modifier.clickable(
            onClick = onClick ?: { },
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        ),
        text = text,
        style = SoloRecipeTypography.body4,
        color = textColor,
        textAlign = textAlign,
        textDecoration = textDecoration,
        overflow = textOverflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

internal val LocalTypography = staticCompositionLocalOf { SoloRecipeTypography }