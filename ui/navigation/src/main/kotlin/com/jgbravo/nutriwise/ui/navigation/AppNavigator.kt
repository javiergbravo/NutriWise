package com.jgbravo.nutriwise.ui.navigation

import com.jgbravo.nutriwise.ui.navigation.features.createMealPlan.CreateMealPlanFeatApi
import com.jgbravo.nutriwise.ui.navigation.features.dashboard.DashboardFeatApi
import com.jgbravo.nutriwise.ui.navigation.features.mealPlanDetail.MealPlanDetailFeatApi
import com.jgbravo.nutriwise.ui.navigation.features.splash.SplashFeatApi

class AppNavigator(
    val splashNav: SplashFeatApi,
    val dashboardNav: DashboardFeatApi,
    val createMealPlanNav: CreateMealPlanFeatApi,
    val mealPlanDetailNav: MealPlanDetailFeatApi,
)