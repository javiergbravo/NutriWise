package com.jgbravo.nutriwise.ui.feature.screens.createMealPlan

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.jgbravo.nutriwise.domain.usecases.CreateMealPlan
import com.jgbravo.nutriwise.domain.usecases.models.NewMealPlan
import com.jgbravo.nutriwise.ui.feature.R
import com.jgbravo.nutriwise.ui.feature.base.BaseViewModel
import com.jgbravo.nutriwise.ui.feature.models.UiText
import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.CreateMealPlanEvent.ClickCreateMealPlan
import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.CreateMealPlanEvent.CreateMealPlanSuccess
import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.CreateMealPlanEvent.OnDismissBottomSheet
import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.CreateMealPlanEvent.OnGoalChanged
import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.CreateMealPlanEvent.OnKcalChanged
import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.CreateMealPlanEvent.OnPersonNameChanged
import com.jgbravo.nutriwise.ui.feature.screens.createMealPlan.CreateMealPlanEvent.OnStartDateChanged
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateMealPlanViewModel(
    private val createMealPlan: CreateMealPlan
) : BaseViewModel<CreateMealPlanState, CreateMealPlanEvent>() {

    override val mutableState: MutableStateFlow<CreateMealPlanState> = MutableStateFlow(CreateMealPlanState())
    override val state: StateFlow<CreateMealPlanState>
        get() = mutableState.asStateFlow()
            .onEach {
                Log.d("CreateMealPlanViewModel", "[CreateMealPlanState] state=$it")
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = CreateMealPlanState()
            )

    override fun onEvent(event: CreateMealPlanEvent) {
        Log.d("CreateMealPlanViewModel", "[CreateMealPlanEvent] onEvent=$event")
        when (event) {
            is OnPersonNameChanged -> mutableState.update { lastState ->
                lastState.copy(
                    personName = event.personName,
                    personNameError = validatePersonName(event.personName)
                )
            }
            is OnStartDateChanged -> mutableState.update { lastState ->
                lastState.copy(
                    startDate = event.startDate,
                    startDateError = validateStartDate(event.startDate)
                )
            }
            is OnGoalChanged -> mutableState.update { lastState ->
                lastState.copy(
                    goal = event.goal,
                    goalError = validateGoal(event.goal)
                )
            }
            is OnKcalChanged -> mutableState.update { lastState ->
                lastState.copy(
                    kcal = event.kcal,
                    kcalError = validateKcal(event.kcal)
                )
            }
            ClickCreateMealPlan -> {
                if (validateAllFields()) {
                    mutableState.update { lastState ->
                        lastState.copy(
                            error = null
                        )
                    }
                    createMealPlan()
                } else {
                    mutableState.update { lastState ->
                        lastState.copy(
                            error = UiText.StringResource(R.string.error_ckeck_invalid_fields_before_continue)
                        )
                    }
                }
            }
            CreateMealPlanSuccess -> {
                mutableState.update { lastState ->
                    lastState.copy(
                        isBottomSheetOpened = true
                    )
                }
            }
            OnDismissBottomSheet -> {
                mutableState.update { lastState ->
                    lastState.copy(
                        isBottomSheetOpened = false
                    )
                }
            }
            else -> Unit
        }
    }

    private fun validatePersonName(personName: String): UiText? = when {
        personName.isEmpty() -> UiText.StringResource(R.string.required_field)
        else -> null
    }

    private fun validateStartDate(startDate: String): UiText? = when {
        startDate.isEmpty() -> UiText.StringResource(R.string.required_field)
        else -> null
    }

    private fun validateGoal(goal: String): UiText? = when {
        goal.isEmpty() -> UiText.StringResource(R.string.required_field)
        else -> null
    }

    private fun validateKcal(kcal: String): UiText? = when {
        kcal.toIntOrNull() == null -> UiText.StringResource(R.string.error_field_is_not_number)
        kcal.toInt() < 0 -> UiText.StringResource(R.string.error_field_is_not_positive)
        kcal.isEmpty() -> UiText.StringResource(R.string.required_field)
        else -> null
    }

    private fun validateAllFields(): Boolean = listOf(
        mutableState.value.personNameError,
        mutableState.value.startDateError,
        mutableState.value.goalError,
        mutableState.value.kcalError
    ).all { it == null } && listOf(
        mutableState.value.personName,
        mutableState.value.startDate,
        mutableState.value.goal,
        mutableState.value.kcal
    ).all { it.isNotEmpty() }

    private fun createMealPlan() {
        val newMealPlan = NewMealPlan(
            person = mutableState.value.personName,
            startDate = mutableState.value.startDate,
            goal = mutableState.value.goal,
            kcal = mutableState.value.kcal.toInt()
        )
        viewModelScope.launch {
            createMealPlan.invoke(newMealPlan)
        }
        onEvent(CreateMealPlanSuccess)
    }
}