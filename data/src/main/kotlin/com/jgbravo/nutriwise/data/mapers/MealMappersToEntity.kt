package com.jgbravo.nutriwise.data.mapers

import com.jgbravo.nutriwise.data.db.models.MealEntity
import com.jgbravo.nutriwise.data.db.models.MealPlanEntity
import com.jgbravo.nutriwise.data.models.MealDataModel
import com.jgbravo.nutriwise.data.models.MealPlanDataModel
import io.realm.kotlin.ext.toRealmList
import org.mongodb.kbson.ObjectId

internal fun MealDataModel.mapToEntity() = MealEntity().apply {
    type = this@mapToEntity.type
    carbs = this@mapToEntity.carbs
    protein = this@mapToEntity.protein
    fat = this@mapToEntity.fat
}

internal fun MealPlanDataModel.mapToEntity() = MealPlanEntity().apply {
    _id = ObjectId(this@mapToEntity.id)
    person = this@mapToEntity.person
    startDate = this@mapToEntity.startDate
    goal = this@mapToEntity.goal
    kcal = this@mapToEntity.kcal
    meals = this@mapToEntity.meals.map { meal -> meal.mapToEntity() }.toRealmList()
    state = this@mapToEntity.state.name
}