package com.jgbravo.nutriwise.data.di

import com.jgbravo.nutriwise.data.db.MealDao
import com.jgbravo.nutriwise.data.db.MealDaoImpl
import com.jgbravo.nutriwise.data.db.models.MealEntity
import com.jgbravo.nutriwise.data.db.models.MealPlanEntity
import com.jgbravo.nutriwise.data.repositories.MealRepository
import com.jgbravo.nutriwise.data.repositories.MealRepositoryImpl
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.dsl.module

val dataModule = module {
    single {
        Realm.open(
            configuration = RealmConfiguration.Builder(
                schema = dataEntities
            ).compactOnLaunch().build()
        )
    }
    single<MealDao> { MealDaoImpl(get()) }
    single<MealRepository> { MealRepositoryImpl(get()) }
}

val dataEntities = setOf(
    MealPlanEntity::class,
    MealEntity::class
)