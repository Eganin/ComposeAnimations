package com.example.animationsdemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId

@Composable
@Preview
@ExperimentalMotionApi
fun MotionLayoutYoutube() {

    Column(modifier = Modifier.fillMaxSize()) {
        var progress by remember {
            mutableStateOf(0f)
        }
        Slider(value = progress, onValueChange = { progress = it })

        Spacer(modifier = Modifier.height(16.dp))

        val context = LocalContext.current
        val scene = remember {
            context.resources
                .openRawResource(R.raw.structure_youtube)
                .readBytes()
                .decodeToString()
        }

        MotionLayout(
            motionScene = MotionScene(content = scene),
            progress = progress,
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .layoutId("video")
            )

            Box(
                modifier = Modifier
                    .background(Color.Yellow)
                    .layoutId("right_content")
                    .padding(8.dp)
            ) {
                Text(
                    text = "Some name",
                    fontSize = 12.sp
                )
            }

            Box(
                modifier = Modifier
                    .background(Color.Gray)
                    .layoutId("bottom_content")
                    .padding(16.dp)
            ) {
                Text(
                    text = "Some name",
                    fontSize = 12.sp
                )
            }
        }
    }
}