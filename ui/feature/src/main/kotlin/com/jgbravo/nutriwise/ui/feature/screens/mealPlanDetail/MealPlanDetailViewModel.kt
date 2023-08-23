package com.jgbravo.nutriwise.ui.feature.screens.mealPlanDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.jgbravo.nutriwise.domain.base.models.wrappers.Resource.Error
import com.jgbravo.nutriwise.domain.base.models.wrappers.Resource.Success
import com.jgbravo.nutriwise.domain.usecases.GetMealPlan
import com.jgbravo.nutriwise.domain.usecases.models.MealPlan
import com.jgbravo.nutriwise.ui.feature.base.BaseViewModel
import com.jgbravo.nutriwise.ui.feature.screens.mealPlanDetail.MealPlanDetailEvent.OnErrorScreen
import com.jgbravo.nutriwise.ui.feature.screens.mealPlanDetail.MealPlanDetailEvent.OnMealClicked
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MealPlanDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getMealPlan: GetMealPlan
) : BaseViewModel<MealPlanDetailState, MealPlanDetailEvent>() {

    private val mealPlanId: String = checkNotNull(savedStateHandle[MEAL_PLAN_ID])

    override val mutableState = MutableStateFlow(MealPlanDetailState())
    override val state = combine(mutableState, getMealPlan.invoke(id = mealPlanId)) { state, mealPlanResource ->
        when (mealPlanResource) {
            is Error -> state.copy(error = mealPlanResource.exception.message)
            is Success -> {
                if (mealPlanResource.data == null) {
                    state.copy(error = "No data")
                } else {
                    state.copy(meals = (mealPlanResource.data as MealPlan).meals)
                }
            }
        }
        state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MealPlanDetailState())

    override fun onEvent(event: MealPlanDetailEvent) {
        when (event) {
            OnErrorScreen -> mutableState.update {
                it.copy(error = null)
            }
            is OnMealClicked -> Unit
        }
    }

    companion object {
        const val MEAL_PLAN_ID = "mealPlanId"
    }

    /*private fun getMealsFromRepository(): Flow<List<Meal>> = flowOf(
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
    )*/
}