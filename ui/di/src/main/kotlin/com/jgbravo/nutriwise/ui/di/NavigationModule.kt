package com.jgbravo.nutriwise.ui.di

import com.jgbravo.nutriwise.ui.navigation.AppNavigator
import com.jgbravo.nutriwise.ui.navigation.features.createMealPlan.CreateMealPlanFeatApi
import com.jgbravo.nutriwise.ui.navigation.features.createMealPlan.CreateMealPlanFeatImpl
import com.jgbravo.nutriwise.ui.navigation.features.dashboard.DashboardFeatApi
import com.jgbravo.nutriwise.ui.navigation.features.dashboard.DashboardFeatImpl
import com.jgbravo.nutriwise.ui.navigation.features.mealPlanDetail.MealPlanDetailFeatApi
import com.jgbravo.nutriwise.ui.navigation.features.mealPlanDetail.MealPlanDetailFeatImpl
import com.jgbravo.nutriwise.ui.navigation.features.splash.SplashFeatApi
import com.jgbravo.nutriwise.ui.navigation.features.splash.SplashFeatImpl
import org.koin.dsl.module

val navigationModule = module {
    single<SplashFeatApi> { SplashFeatImpl() }
    single<DashboardFeatApi> { DashboardFeatImpl() }
    single<CreateMealPlanFeatApi> { CreateMealPlanFeatImpl() }
    single<MealPlanDetailFeatApi> { MealPlanDetailFeatImpl() }

    single {
        AppNavigator(
            splashNav = get(),
            dashboardNav = get(),
            createMealPlanNav = get(),
            mealPlanDetailNav = get()
        )
    }
}