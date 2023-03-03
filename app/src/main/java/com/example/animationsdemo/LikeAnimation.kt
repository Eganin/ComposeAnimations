package com.example.animationsdemo

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LikeAnimation(
    isActive: Boolean,
    modifier: Modifier = Modifier,
    animationFinished: () -> Unit = {}
) {
    val size = remember {
        Animatable(
            initialValue = 0.dp,
            typeConverter = Dp.VectorConverter
        )
    }

    val position = remember {
        Animatable(
            initialValue = 0.dp,
            typeConverter = Dp.VectorConverter
        )
    }

    LaunchedEffect(key1 = isActive) {
        delay(1000L)
        if (isActive) {
            size.animateTo(
                targetValue = 300.dp,
                animationSpec = tween(500)
            )

            val springSpec: SpringSpec<Dp> = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessMediumLow
            )

            repeat(3) {
                size.animateTo(250.dp, springSpec)
                size.animateTo(300.dp, springSpec)
            }

            coroutineScope {
                launch {
                    size.animateTo(0.dp, tween(800))
                }
                launch {
                    position.animateTo((-500).dp, tween(800))
                }
            }

            size.snapTo(0.dp)
            position.snapTo(0.dp)

            animationFinished()
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            modifier = Modifier
                .size(size.value)
                .offset(y = position.value),
            tint = Color.Red
        )
    }
}