package com.jgbravo.nutriwise.ui.feature.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.jgbravo.nutriwise.ui.feature.R
import com.jgbravo.nutriwise.ui.feature.screens.splash.components.AnimationSplashContent
import com.jgbravo.nutriwise.ui.feature.screens.splash.components.DesignSplashScreen

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit,
) {
    val scaleAnimation: Animatable<Float, AnimationVector1D> = remember { Animatable(initialValue = 0f) }

    AnimationSplashContent(
        scaleAnimation = scaleAnimation,
        durationMillisAnimation = 500,
        delayScreen = 1500L,
        onEndTime = onSplashFinished
    )

    DesignSplashScreen(
        imagePainter = painterResource(id = R.drawable.ic_logo_kotlin),
        scaleAnimation = scaleAnimation
    )
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        onSplashFinished = {}
    )
}