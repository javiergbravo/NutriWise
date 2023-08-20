package com.jgbravo.data.di

import com.jgbravo.data.repository.MealRepository
import com.jgbravo.data.repository.MealRepositoryImpl
import com.jgbravo.data.repository.models.MealEntity
import com.jgbravo.data.repository.models.MealPlanEntity
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
    single<MealRepository> { MealRepositoryImpl(get()) }
}

val dataEntities = setOf(
    MealPlanEntity::class,
    MealEntity::class
)