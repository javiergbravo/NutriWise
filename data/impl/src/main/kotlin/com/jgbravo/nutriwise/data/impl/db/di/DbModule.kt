package com.jgbravo.nutriwise.data.impl.db.di

import com.jgbravo.nutriwise.data.impl.base.RealmApp
import com.jgbravo.nutriwise.data.impl.db.MealDao
import com.jgbravo.nutriwise.data.impl.db.MealDaoImpl
import org.koin.dsl.module

val dbModule = module {
    single { RealmApp.init() }
    single<MealDao> { MealDaoImpl(get()) }
}