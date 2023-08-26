package com.jgbravo.nutriwise.ui.di

import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.CreateMealPlanViewModel
import com.jgbravo.nutriwise.ui.feature.screens.dashboard.DashboardViewModel
import com.jgbravo.nutriwise.ui.feature.screens.mealPlanDetail.MealPlanDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DashboardViewModel(getAllMealPlans = get()) }
    viewModel { MealPlanDetailViewModel(savedStateHandle = get(), getMealPlan = get()) }
    viewModel { CreateMealPlanViewModel(createMealPlan = get()) }
}