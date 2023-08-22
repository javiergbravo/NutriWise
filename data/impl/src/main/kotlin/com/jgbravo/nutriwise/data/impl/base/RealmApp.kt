package com.jgbravo.nutriwise.data.impl.base

import com.jgbravo.nutriwise.data.impl.db.models.MealEntity
import com.jgbravo.nutriwise.data.impl.db.models.MealPlanEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

object RealmApp {

    private val dataEntities = setOf(
        MealPlanEntity::class,
        MealEntity::class
    )

    fun init() = Realm.open(
        configuration = RealmConfiguration.Builder(
            schema = dataEntities
        ).compactOnLaunch().build()
    )
}