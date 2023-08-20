package com.jgbravo.nutriwise.di

import com.jgbravo.nutriwise.ui.dashboard.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { DashboardViewModel(get()) }
}