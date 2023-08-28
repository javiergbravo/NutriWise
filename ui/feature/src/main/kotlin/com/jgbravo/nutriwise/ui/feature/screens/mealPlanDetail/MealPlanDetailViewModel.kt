package com.jgbravo.nutriwise.ui.feature.screens.mealPlanDetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.jgbravo.nutriwise.domain.base.models.utils.combineWithFlow
import com.jgbravo.nutriwise.domain.usecases.GetMealPlan
import com.jgbravo.nutriwise.ui.feature.R
import com.jgbravo.nutriwise.ui.feature.base.BaseViewModel
import com.jgbravo.nutriwise.ui.feature.models.UiText
import com.jgbravo.nutriwise.ui.feature.screens.mealPlanDetail.MealPlanDetailEvent.OnErrorScreen
import com.jgbravo.nutriwise.ui.feature.screens.mealPlanDetail.MealPlanDetailEvent.OnMealClicked
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MealPlanDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getMealPlan: GetMealPlan
) : BaseViewModel<MealPlanDetailState, MealPlanDetailEvent>() {

    private val mealPlanId: String = checkNotNull(savedStateHandle[MEAL_PLAN_ID])

    override val mutableState = MutableStateFlow(MealPlanDetailState())
    override val state = mutableState.combineWithFlow(
        flowWithResource = getMealPlan.invoke(id = mealPlanId),
        onSuccess = { mealPlan ->
            mutableState.update { lastState ->
                lastState.copy(meals = mealPlan.meals)
            }
        },
        onError = { exception ->
            mutableState.update { lastState ->
                lastState.copy(
                    error = exception.message?.let {
                        UiText.DynamicString(it)
                    } ?: UiText.StringResource(R.string.generic_error)
                )
            }
        }
    ).onEach {
        Log.d("MealPlanDetailViewModel", "[MealPlanDetailState] state=$it")
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MealPlanDetailState()
    )

    override fun onEvent(event: MealPlanDetailEvent) {
        Log.d("MealPlanDetailViewModel", "[MealPlanDetailEvent] onEvent=$event")
        when (event) {
            OnErrorScreen -> mutableState.update { lastState ->
                lastState.copy(error = null)
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