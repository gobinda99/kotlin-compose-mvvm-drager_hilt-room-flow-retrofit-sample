package com.gobinda.sample.utils

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun SimmerList(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    contentAfterLoading: @Composable () -> Unit,
) {
    if(isLoading){
        LazyColumn(){
            items(20) {
                SimmerItem(modifier)
            }
        }
    } else {
        contentAfterLoading.invoke()
    }
}


@Composable
fun SimmerItem(modifier: Modifier = Modifier) {
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(all = 10.dp), verticalAlignment = Alignment.Top) {

        Spacer(modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(10.dp))
            .shimmerEffect()
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(verticalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth(fraction = 0.5f)
                .shimmerEffect()
            )

            Spacer(modifier = Modifier.height(10.dp)) //creates an empty space between
            Spacer(modifier = Modifier
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth(fraction = 0.7f)
                .shimmerEffect()
            )

            Spacer(modifier = Modifier.height(10.dp)) //creates an empty space between
            Spacer(modifier = Modifier
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth(fraction = 0.9f)
                .shimmerEffect()  )
        }
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(delayMillis = 1000)
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color.LightGray.copy(alpha = 0.9f), //darker grey (90% opacity)
                Color.LightGray.copy(alpha = 0.3f), //lighter grey (30% opacity)
                Color.LightGray.copy(alpha = 0.9f)
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}