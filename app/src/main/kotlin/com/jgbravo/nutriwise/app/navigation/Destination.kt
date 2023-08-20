package com.jgbravo.nutriwise.app.navigation

sealed class Destination(protected val route: String, vararg params: String) {

    val fullRoute: String = if (params.isEmpty()) {
        route
    } else {
        val builder = StringBuilder(route)
        params.forEach { param -> builder.append("/{$param}") }
        builder.toString()
    }

    sealed class NoArgumentDestination(route: String) : Destination(route) {
        operator fun invoke(): String = route
    }

    // Screens
    object DashboardDestination : NoArgumentDestination("dashboard")
}