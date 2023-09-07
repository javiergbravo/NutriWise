package com.jgbravo.nutriwise.ui.navigation.features.splash

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.jgbravo.nutriwise.ui.feature.screens.splash.SplashScreen
import com.jgbravo.nutriwise.ui.navigation.base.destinationComposable
import com.jgbravo.nutriwise.ui.navigation.features.AppDestination.DashboardDestination
import com.jgbravo.nutriwise.ui.navigation.features.AppDestination.SplashDestination

class SplashFeatImpl : SplashFeatApi {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.destinationComposable(destination = SplashDestination) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(route = DashboardDestination())
                }
            )
        }
    }
}