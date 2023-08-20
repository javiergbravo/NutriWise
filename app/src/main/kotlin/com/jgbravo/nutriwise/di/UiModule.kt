package com.jgbravo.nutriwise.di

import com.jgbravo.nutriwise.ui.mealPlanDetail.MealPlanDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { MealPlanDetailViewModel(get()) }
}