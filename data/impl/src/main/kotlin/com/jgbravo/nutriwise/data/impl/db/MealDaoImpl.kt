package com.jgbravo.nutriwise.data.impl.db

import android.util.Log
import com.jgbravo.nutriwise.data.impl.db.models.MealPlanEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MealDaoImpl(
    private val realm: Realm
) : MealDao {

    override fun fetchAllMealPlans(): Flow<List<MealPlanEntity>> {
        return realm.query<MealPlanEntity>().asFlow().map { it.list }
    }

    override fun fetchMealPlan(id: String): Flow<MealPlanEntity> {
        return realm.query<MealPlanEntity>(query = "_id == $0", id).asFlow().map { it.list.first() }
    }

    override suspend fun insertMealPlan(mealPlan: MealPlanEntity) {
        realm.write { copyToRealm(mealPlan) }
    }

    override suspend fun updateMealPlan(mealPlan: MealPlanEntity) {
        realm.write {
            val queriedMealPlan = query<MealPlanEntity>(query = "_id == $0", mealPlan._id).first().find()
            queriedMealPlan?.let {
                it.person = mealPlan.person
                it.startDate = mealPlan.startDate
                it.goal = mealPlan.goal
                it.kcal = mealPlan.kcal
                it.meals = mealPlan.meals
                it.state = mealPlan.state
                it.timestamp = mealPlan.timestamp
            }
        }
    }

    override suspend fun deleteMealPlan(id: String) {
        realm.write {
            val queriedMealPlan = query<MealPlanEntity>(query = "_id == $0", id).first().find()
            try {
                queriedMealPlan?.let { delete(it) }
            } catch (e: IllegalArgumentException) {
                Log.e("MealRepositoryImpl", "Invalid object with _id=$id to delete: ${e.message}")
            }
        }
    }
}