package com.example.animationsdemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId

@Composable
@Preview
@ExperimentalMotionApi
fun MotionLayoutDemo() {

    Column(modifier = Modifier.fillMaxSize()) {
        var progress by remember {
            mutableStateOf(0f)
        }
        Slider(value = progress, onValueChange = { progress = it })

        Spacer(modifier = Modifier.height(16.dp))

        val context = LocalContext.current
        val scene = remember {
            context.resources
                .openRawResource(R.raw.structure)
                .readBytes()
                .decodeToString()
        }

        MotionLayout(
            motionScene = MotionScene(content = scene),
            progress = progress,
            modifier = Modifier.fillMaxSize()
        ) {
            val props by motionProperties(id = "box")
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(size = props.int(name = "radius").dp))
                    .background(Color.DarkGray)
                    .layoutId("box")
            ) {

            }
        }
    }
}