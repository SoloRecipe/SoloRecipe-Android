package com.project.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.project.design_system.theme.IcBack
import com.project.design_system.theme.SoloRecipeColor

private val AppBarHeight = 50.dp

@Composable
fun SoloRecipeAppBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = SoloRecipeColor.White,
    startIcon: (@Composable () -> Unit)? = null,
    endIcons: (@Composable RowScope.() -> Unit)? = null,
    onStartIconClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .background(color = backgroundColor)
            .fillMaxWidth()
            .height(AppBarHeight)
            .drawBehind {
                drawLine(
                    color = SoloRecipeColor.Secondary10,
                    start = Offset(
                        x = 0f,
                        y = size.height
                    ),
                    end = Offset(
                        x = size.width,
                        y = size.height
                    ),
                    strokeWidth = 1.dp.toPx()
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier
                .fillMaxHeight()
                .padding(start = 16.dp)
                .clickable { onStartIconClick() },
            contentAlignment = Alignment.Center
        ) {
            startIcon?.let { startIcon ->
                startIcon()
            } ?: IcBack(contentDescription = "back")
        }
        Spacer(modifier = modifier.weight(1f))
        endIcons?.let { endIcons ->
            Row(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                endIcons()
            }
        }
    }
}