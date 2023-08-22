package com.jgbravo.nutriwise.data.impl.mapers

import com.jgbravo.nutriwise.common.utils.DatePatterns
import com.jgbravo.nutriwise.common.utils.DateTimeUtil.formatDate
import com.jgbravo.nutriwise.data.api.models.MealDataModel
import com.jgbravo.nutriwise.data.api.models.MealPlanDataModel
import com.jgbravo.nutriwise.data.impl.db.models.MealEntity
import com.jgbravo.nutriwise.data.impl.db.models.MealPlanEntity
import io.realm.kotlin.ext.toRealmList
import org.mongodb.kbson.ObjectId

internal fun MealDataModel.mapToEntity() = MealEntity().apply {
    type = this@mapToEntity.type.value
    carbs = this@mapToEntity.carbs
    protein = this@mapToEntity.protein
    fat = this@mapToEntity.fat
}

internal fun MealPlanDataModel.mapToEntity() = MealPlanEntity().apply {
    _id = ObjectId(this@mapToEntity.id)
    person = this@mapToEntity.person
    startDate = this@mapToEntity.startDate.formatDate(DatePatterns.SPANISH_DATE_PATTERN)
    goal = this@mapToEntity.goal
    kcal = this@mapToEntity.kcal
    meals = this@mapToEntity.meals.map { meal -> meal.mapToEntity() }.toRealmList()
    state = this@mapToEntity.state.name
}