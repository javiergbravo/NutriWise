package com.jgbravo.nutriwise.domain.di

import com.jgbravo.nutriwise.domain.usecases.GetAllMealPlans
import com.jgbravo.nutriwise.domain.usecases.GetMealPlan
import org.koin.dsl.module

val domainModule = module {
    single { GetAllMealPlans(get()) }
    single { GetMealPlan(get()) }
}