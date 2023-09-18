package com.jgbravo.nutriwise.data.impl.repositories

import com.jgbravo.logger.Logger
import com.jgbravo.nutriwise.data.api.models.MealPlanDataModel
import com.jgbravo.nutriwise.data.api.models.NewMealPlanDataModel
import com.jgbravo.nutriwise.data.api.repositories.MealRepository
import com.jgbravo.nutriwise.data.impl.db.MealDao
import com.jgbravo.nutriwise.data.impl.mapers.mapToData
import com.jgbravo.nutriwise.data.impl.mapers.mapToEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class MealRepositoryImpl(
    private val dao: MealDao
) : MealRepository {
    override fun getAllMealPlans(): Flow<List<MealPlanDataModel>> = dao.fetchAllMealPlans()
        .distinctUntilChanged()
        .onEach {
            Logger.d("MealRepositoryImpl", "getAllMealPlans: $it")
        }
        .map { planList -> planList.map { it.mapToData() } }
        .flowOn(Dispatchers.IO)

    override fun getMealPlan(id: String): Flow<MealPlanDataModel?> = dao.fetchMealPlan(id)
        .onEach {
            Logger.d("MealRepositoryImpl", "getMealPlan: $it")
        }
        .distinctUntilChanged()
        .map { it?.mapToData() }
        .flowOn(Dispatchers.IO)

    override suspend fun insertMealPlan(mealPlan: NewMealPlanDataModel) {
        val newMealPlan = mealPlan.mapToEntity()
        dao.insertMealPlan(newMealPlan)
    }

    override suspend fun updateMealPlan(mealPlan: MealPlanDataModel) = dao.updateMealPlan(mealPlan.mapToEntity())

    override suspend fun deleteMealPlan(id: String) = dao.deleteMealPlan(id)
}