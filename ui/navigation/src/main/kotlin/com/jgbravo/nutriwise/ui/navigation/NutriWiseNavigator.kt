package com.jgbravo.nutriwise.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.jgbravo.nutriwise.ui.navigation.base.DestinationNavHost
import com.jgbravo.nutriwise.ui.navigation.base.registerFeature
import com.jgbravo.nutriwise.ui.navigation.features.AppDestination.SplashDestination


@Composable
fun NutriWiseNavigator(
    appNavigator: AppNavigator,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    DestinationNavHost(
        navController = navController,
        startDestination = SplashDestination
    ) {

        registerFeature(
            featureApi = appNavigator.splashNav,
            navController = navController,
            modifier = modifier
        )

        registerFeature(
            featureApi = appNavigator.dashboardNav,
            navController = navController,
            modifier = modifier
        )

        registerFeature(
            featureApi = appNavigator.createMealPlanNav,
            navController = navController,
            modifier = modifier
        )

        registerFeature(
            featureApi = appNavigator.mealPlanDetailNav,
            navController = navController,
            modifier = modifier
        )
    }
}