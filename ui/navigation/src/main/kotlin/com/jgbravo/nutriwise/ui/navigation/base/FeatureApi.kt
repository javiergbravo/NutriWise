package com.jgbravo.nutriwise.ui.navigation.base

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.jgbravo.nutriwise.ui.navigation.features.Destination

interface FeatureApi {

    val destination: Destination

    fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController)
}