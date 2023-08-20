package com.jgbravo.nutriwise.di

import com.jgbravo.nutriwise.ui.dashboard.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::DashboardViewModel)
//    viewModel { DashboardViewModel(get()) }
}