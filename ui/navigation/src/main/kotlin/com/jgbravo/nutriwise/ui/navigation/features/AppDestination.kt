package com.jgbravo.nutriwise.ui.navigation.features

sealed class AppDestination(protected val route: String, vararg params: String) {

    val fullRoute: String = if (params.isEmpty()) {
        route
    } else {
        val builder = StringBuilder(route)
        params.forEach { param -> builder.append("/{$param}") }
        builder.toString()
    }

    sealed class NoArgumentDestination(route: String) : AppDestination(route) {
        operator fun invoke(): String = route
    }

    object DashboardDestination : NoArgumentDestination("dashboard")

    object MealPlanDetailDestination : AppDestination("mealPlanDetail", MEAL_PLAN_ID) {
        operator fun invoke(mealPlanId: String): String = route.replace("{$MEAL_PLAN_ID}", mealPlanId)
    }

    object CreateMealPlanDestination : NoArgumentDestination("createMealPlan")

    companion object Argument {
        const val MEAL_PLAN_ID = "mealPlanId"
    }
}