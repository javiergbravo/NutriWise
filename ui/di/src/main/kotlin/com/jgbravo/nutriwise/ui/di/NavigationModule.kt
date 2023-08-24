package com.jgbravo.nutriwise.ui.di

import com.jgbravo.nutriwise.ui.navigation.AppNavigator
import com.jgbravo.nutriwise.ui.navigation.features.dashboard.DashboardFeatApi
import com.jgbravo.nutriwise.ui.navigation.features.dashboard.DashboardFeatImpl
import com.jgbravo.nutriwise.ui.navigation.features.mealPlanDetail.MealPlanDetailFeatApi
import com.jgbravo.nutriwise.ui.navigation.features.mealPlanDetail.MealPlanDetailFeatImpl
import org.koin.dsl.module

val navigationModule = module {
    single<DashboardFeatApi> { DashboardFeatImpl() }
    single<MealPlanDetailFeatApi> { MealPlanDetailFeatImpl() }

    single {
        AppNavigator(
            dashboardNav = get(),
            mealPlanDetailNav = get()
        )
    }
}