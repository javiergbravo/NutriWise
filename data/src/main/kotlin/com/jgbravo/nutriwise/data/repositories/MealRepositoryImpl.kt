package com.jgbravo.nutriwise.data.repositories

import com.jgbravo.nutriwise.data.db.MealDao
import com.jgbravo.nutriwise.data.mapers.mapToData
import com.jgbravo.nutriwise.data.mapers.mapToEntity
import com.jgbravo.nutriwise.data.models.MealPlanDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class MealRepositoryImpl(
    private val dao: MealDao
) : MealRepository {
    override fun getAllMealPlans(): Flow<List<MealPlanDataModel>> = dao.fetchAllMealPlans()
        .map { planList -> planList.map { it.mapToData() } }
        .flowOn(Dispatchers.IO)

    override fun getMealPlan(id: String): Flow<MealPlanDataModel> = dao.fetchMealPlan(id)
        .map { it.mapToData() }
        .flowOn(Dispatchers.IO)

    override suspend fun insertMealPlan(mealPlan: MealPlanDataModel) = dao.insertMealPlan(mealPlan.mapToEntity())

    override suspend fun updateMealPlan(mealPlan: MealPlanDataModel) = dao.updateMealPlan(mealPlan.mapToEntity())

    override suspend fun deleteMealPlan(id: String) = dao.deleteMealPlan(id)
}