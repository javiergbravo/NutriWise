package com.jgbravo.nutriwise.data.impl.db

import android.util.Log
import com.jgbravo.nutriwise.data.impl.base.RealmQuery
import com.jgbravo.nutriwise.data.impl.db.models.MealPlanEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class MealDaoImpl(
    private val realm: Realm
) : MealDao {

    override fun fetchAllMealPlans(): Flow<List<MealPlanEntity>> {
        return realm.query<MealPlanEntity>().find().asFlow().distinctUntilChanged().map { it.list.toList() }
            .onEach {
                Log.d("MealDaoImpl", "fetchAllMealPlans: $it")
            }
    }

    override fun fetchMealPlan(id: String): Flow<MealPlanEntity?> {
        return realm.query<MealPlanEntity>(query = RealmQuery.EQUALS_ID, id).asFlow().map { it.list.firstOrNull() }
    }

    override suspend fun insertMealPlan(mealPlan: MealPlanEntity) {
        realm.writeBlocking { copyToRealm(mealPlan) }
    }

    override suspend fun updateMealPlan(mealPlan: MealPlanEntity) {
        realm.write {
            val queriedMealPlan = query<MealPlanEntity>(query = RealmQuery.EQUALS_ID, mealPlan.id).first().find()
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
            val queriedMealPlan = query<MealPlanEntity>(query = RealmQuery.EQUALS_ID, id).first().find()
            try {
                queriedMealPlan?.let { delete(it) }
            } catch (e: IllegalArgumentException) {
                Log.e("MealRepositoryImpl", "Invalid object with id=$id to delete: ${e.message}")
            }
        }
    }
}