package com.jgbravo.nutriwise.ui.dashboard

import androidx.lifecycle.viewModelScope
import com.jgbravo.data.repository.MealRepository
import com.jgbravo.nutriwise.base.presentation.BaseViewModel
import com.jgbravo.nutriwise.ui.dashboard.DashboardEvent.OnErrorScreen
import com.jgbravo.nutriwise.ui.dashboard.DashboardEvent.OnMealClicked
import com.jgbravo.nutriwise.ui.dashboard.models.Meal
import com.jgbravo.nutriwise.ui.dashboard.models.MealType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class DashboardViewModel(
    private val mealRepository: MealRepository
) : BaseViewModel<DashboardState, DashboardEvent>() {

    override val mutableState = MutableStateFlow(DashboardState())
    override val state = combine(mutableState, getMealsFromRepository()) { state, meals ->
        if (state.meals != meals) {
            state.copy(
                meals = meals
            )
        } else {
            state
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DashboardState())

    override fun onEvent(event: DashboardEvent) {
        when (event) {
            OnErrorScreen -> mutableState.update {
                it.copy(error = null)
            }
            is OnMealClicked -> Unit
        }
    }

    private fun getMealsFromRepository(): Flow<List<Meal>> = flowOf(
        listOf(
            Meal(
                mealType = MealType.BREAKFAST,
                carbs = 20,
                protein = 10,
                fat = 5
            ),
            Meal(
                mealType = MealType.MORNING_SNACK,
                carbs = 20,
                protein = 10,
                fat = 5
            ),
            Meal(
                mealType = MealType.LUNCH,
                carbs = 20,
                protein = 10,
                fat = 5
            ),
            Meal(
                mealType = MealType.AFTERNOON_SNACK,
                carbs = 20,
                protein = 10,
                fat = 5
            ),
            Meal(
                mealType = MealType.DINNER,
                carbs = 20,
                protein = 10,
                fat = 5
            )
        )
    )
}