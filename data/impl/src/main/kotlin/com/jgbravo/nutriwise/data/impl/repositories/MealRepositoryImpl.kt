package com.jgbravo.nutriwise.data.impl.repositories

import com.jgbravo.nutriwise.data.api.models.MealPlanDataModel
import com.jgbravo.nutriwise.data.api.repositories.MealRepository
import com.jgbravo.nutriwise.data.impl.db.MealDao
import com.jgbravo.nutriwise.data.impl.mapers.mapToData
import com.jgbravo.nutriwise.data.impl.mapers.mapToEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class MealRepositoryImpl(
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