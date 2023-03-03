package com.example.animationsdemo

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun AnimationScreen() {

    MovieDetailsView()
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieDetailsView(modifier: Modifier = Modifier) {

    var isLiked by remember {
        mutableStateOf(false)
    }


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .background(Color(0xFFF3F4F5))
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TopBar(isLiked){
                isLiked=!isLiked
            }
            val isVisible = remember {
                MutableTransitionState(false).also {
                    it.targetState = true
                }
            }
            val duration = 1500
            AnimatedVisibility(
                visibleState = isVisible,
                enter = EnterTransition.None
            ) {
                Column {
                    MovieDetails(
                        modifier = Modifier.animateEnterExit(
                            enter = fadeIn(tween(duration)) + slideInVertically(tween(duration))
                        )
                    )
                    FilmInfoSection(
                        modifier = Modifier
                            .animateEnterExit(
                                enter = fadeIn(tween(duration)) + slideInHorizontally(
                                    tween(duration)
                                )
                            )
                    )
                }
            }
        }
        LikeAnimation(isActive = isLiked)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieDetails(modifier: Modifier = Modifier) {

    var isToggled by remember {
        mutableStateOf(false)
    }

    val duration = 1500
    val transition = updateTransition(targetState = isToggled, label = "")

    val width by transition.animateDp(
        label = "Width animation",
        transitionSpec = { tween(duration) }
    ) { toggled ->
        if (toggled) LocalConfiguration.current.screenWidthDp.dp else 100.dp
    }

    val alpha by transition.animateFloat(
        label = "Alpha animation",
        transitionSpec = { tween(duration) }
    ) { toggled ->
        if (toggled) 0f else 1f
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            AsyncImage(
                model = "https://sun6-20.userapi.com/s/v1/if1/M7nBcSI5mgzreJ98QQuk4BoYIldk5HLJLmOy1w0w6rC5kuOftfa7PVT_vcNcWzu9pLpE4JcL.jpg?size=960x1350&quality=96&crop=0,0,960,1350&ava=1",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(width)
                    .clip(RoundedCornerShape(6.dp))
                    .aspectRatio(2f / 3f)
                    .clickable { isToggled = !isToggled }
                    .background(
                        shape = RoundedCornerShape(6.dp),
                        color = Color(0xFFe5e5e5)
                    )
            )
            FilmInfoSection(
                modifier = Modifier.alpha(alpha)
            )
        }

        transition.AnimatedVisibility(
            visible = { it },
            enter = fadeIn(tween(duration)),
            exit = fadeOut(tween(duration))
        ) {
            FilmInfoSection()
        }
    }
}

@Composable
fun FilmInfoSection(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = spacedBy(4.dp),
        modifier = modifier
            .padding(
                start = 16.dp,
                top = 4.dp
            )
    ) {
        Text(
            text = "Avengers",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = "2013", fontSize = 16.sp)
        Text(
            text = "Action,War,Hero",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun TopBar(isLiked: Boolean, onClickLike: () -> Unit) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        elevation = 0.dp
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = onClickLike) {
            Icon(
                imageVector = Icons.Default.Favorite, contentDescription = null,
                tint = if (isLiked) Color.Red else Color.LightGray
            )
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }
    }
}