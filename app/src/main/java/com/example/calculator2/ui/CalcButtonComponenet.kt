package com.example.calculator2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator2.ui.theme.ButtonPink
import com.example.calculator2.ui.theme.ButtonShadowColorBottom
import com.example.calculator2.ui.theme.ButtonShadowColorTop
import com.example.calculator2.ui.theme.Calculator2Theme

@Composable
internal fun CalcButtonComponent(
    modifier: Modifier = Modifier,
    color: Color,
    symbol: String,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(color)
            .clickable { onClick() }
            .then(modifier)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize(fraction = 0.8f)
                .shadow(
                    color = ButtonShadowColorTop,
                    offsetX = (-4).dp,
                    offsetY = (-4).dp,
                    blurRadius = 8.dp,
                )
                .shadow(
                    color = ButtonShadowColorBottom,
                    offsetX = (4).dp,
                    offsetY = (4).dp,
                    blurRadius = 8.dp,
                )
                .clip(MaterialTheme.shapes.medium)
                .background(color)

        ) {
            Text(text = symbol )
        }
    }
}
@Preview
@Composable
fun CalcButtonComponenetPreview() {
    Calculator2Theme {
        CalcButtonComponent(
            modifier = Modifier.size(100.dp),
            color = ButtonPink,
            symbol = "1"
        ) {

        }
    }
}