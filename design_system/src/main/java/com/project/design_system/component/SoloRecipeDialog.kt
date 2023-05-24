package com.project.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.project.design_system.theme.Body3
import com.project.design_system.theme.SoloRecipeColor
import com.project.design_system.theme.Subtitle0

@Composable
fun SoloRecipeDialog(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onDismiss: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            modifier = modifier
                .background(
                    color = SoloRecipeColor.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 18.dp, vertical = 24.dp)
        ) {
            Subtitle0(text = title)
            Spacer(modifier = modifier.height(8.dp))
            Body3(text = description)
            Spacer(modifier = modifier.height(24.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                content()
            }
        }
    }
}