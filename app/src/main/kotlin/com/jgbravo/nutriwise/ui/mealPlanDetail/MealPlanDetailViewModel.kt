package com.jgbravo.nutriwise.ui.mealPlanDetail

import androidx.lifecycle.viewModelScope
import com.jgbravo.nutriwise.base.presentation.BaseViewModel
import com.jgbravo.nutriwise.common.app.models.MealType.AFTERNOON_SNACK
import com.jgbravo.nutriwise.common.app.models.MealType.BREAKFAST
import com.jgbravo.nutriwise.common.app.models.MealType.DINNER
import com.jgbravo.nutriwise.common.app.models.MealType.LUNCH
import com.jgbravo.nutriwise.common.app.models.MealType.MORNING_SNACK
import com.jgbravo.nutriwise.data.repository.MealRepository
import com.jgbravo.nutriwise.domain.usecases.models.Meal
import com.jgbravo.nutriwise.ui.mealPlanDetail.MealPlanDetailEvent.OnErrorScreen
import com.jgbravo.nutriwise.ui.mealPlanDetail.MealPlanDetailEvent.OnMealClicked
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MealPlanDetailViewModel(
    private val mealRepository: MealRepository
) : BaseViewModel<MealPlanDetailState, MealPlanDetailEvent>() {

    override val mutableState = MutableStateFlow(MealPlanDetailState())
    override val state = combine(mutableState, getMealsFromRepository()) { state, meals ->
        if (state.meals != meals) {
            state.copy(
                meals = meals
            )
        } else {
            state
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MealPlanDetailState())

    override fun onEvent(event: MealPlanDetailEvent) {
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
                mealType = BREAKFAST,
                carbs = 20,
                protein = 10,
                fat = 5
            ),
            Meal(
                mealType = MORNING_SNACK,
                carbs = 20,
                protein = 10,
                fat = 5
            ),
            Meal(
                mealType = LUNCH,
                carbs = 20,
                protein = 10,
                fat = 5
            ),
            Meal(
                mealType = AFTERNOON_SNACK,
                carbs = 20,
                protein = 10,
                fat = 5
            ),
            Meal(
                mealType = DINNER,
                carbs = 20,
                protein = 10,
                fat = 5
            )
        )
    )
}