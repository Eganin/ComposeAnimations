package com.example.animationsdemo

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@JvmInline
value class Rotation(
    val value: Float
)

@Composable
fun RotatedIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.Star,
    rotation: Rotation = Rotation(value = 0f)
) {
    Icon(
        modifier = modifier
            .size(100.dp)
            .rotate(rotation.value),
        imageVector = icon,
        contentDescription = null
    )
}

@Composable
fun RotationExample() {
    val initial: Rotation = remember {
        Rotation(0f)
    }
    val target: Rotation = remember {
        Rotation(180f)
    }

    var isActive by remember { mutableStateOf(false) }

    val rotation: Rotation by animateRotationAsState(
        targetValue = if (isActive) target else initial,
        animationSpec = tween(1000)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { isActive = !isActive }) {
            Text(text = "Toggle")
        }
        RotatedIcon(rotation = rotation)
    }
}

@Preview
@Composable
fun PreviewRotatedIcon() {
    RotationExample()
}