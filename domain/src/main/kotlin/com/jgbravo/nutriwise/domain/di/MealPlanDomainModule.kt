package com.jgbravo.nutriwise.domain.di

import com.jgbravo.nutriwise.domain.usecases.CreateMealPlan
import com.jgbravo.nutriwise.domain.usecases.GetAllMealPlans
import com.jgbravo.nutriwise.domain.usecases.GetMealPlan
import org.koin.dsl.module

val domainModule = module {
    factory { GetAllMealPlans(mealRepository = get()) }
    factory { GetMealPlan(mealRepository = get()) }
    factory { CreateMealPlan(mealRepository = get()) }
}