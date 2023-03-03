package com.example.animationsdemo

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

@Composable
fun animateRotationAsState(
    targetValue: Rotation,
    animationSpec: AnimationSpec<Rotation> = spring(),
    visibilityThreshold: Rotation = Rotation(0.01f),
    finishedListener: ((Rotation) -> Unit)? = null
): State<Rotation> {
    return animateValueAsState(
        targetValue,
        RotationConverter,
        animationSpec,
        visibilityThreshold,
        finishedListener
    )
}

object RotationConverter : TwoWayConverter<Rotation, AnimationVector1D> {
    override val convertFromVector: (AnimationVector1D) -> Rotation =
        { vector -> Rotation(vector.value) }
    override val convertToVector: (Rotation) -> AnimationVector1D
        get() = { rotation -> AnimationVector1D(rotation.value) }
}