package com.jgbravo.nutriwise.ui.feature.screens.createMealPlan

import com.jgbravo.nutriwise.common.utils.EMPTY_STRING
import com.jgbravo.nutriwise.ui.feature.base.BaseState
import com.jgbravo.nutriwise.ui.feature.models.UiText

data class CreateMealPlanState(
    val personName: String = EMPTY_STRING,
    val personNameError: UiText? = null,
    val startDate: String = EMPTY_STRING,
    val startDateError: UiText? = null,
    val goal: String = EMPTY_STRING,
    val goalError: UiText? = null,
    val kcal: String = EMPTY_STRING,
    val kcalError: UiText? = null,
    val isLoading: Boolean = false,
    val error: UiText? = null,
    val isCalendarOpened: Boolean = false,
    val isBottomSheetOpened: Boolean = false
) : BaseState