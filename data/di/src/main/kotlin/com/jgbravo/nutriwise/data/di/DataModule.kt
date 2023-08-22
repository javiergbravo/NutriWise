package com.jgbravo.nutriwise.data.di

import com.jgbravo.nutriwise.data.api.repositories.MealRepository
import com.jgbravo.nutriwise.data.impl.db.di.dbModule
import com.jgbravo.nutriwise.data.impl.repositories.MealRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MealRepository> { MealRepositoryImpl(get()) }
}

val dataModule = listOf(
    dbModule,
    repositoryModule
)
