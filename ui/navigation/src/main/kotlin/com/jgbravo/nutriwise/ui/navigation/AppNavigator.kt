package com.jgbravo.nutriwise.ui.navigation

import com.jgbravo.nutriwise.ui.navigation.features.dashboard.DashboardFeatApi
import com.jgbravo.nutriwise.ui.navigation.features.mealPlanDetail.MealPlanDetailFeatApi

class AppNavigator(
    val dashboardNav: DashboardFeatApi,
    val mealPlanDetailNav: MealPlanDetailFeatApi
)