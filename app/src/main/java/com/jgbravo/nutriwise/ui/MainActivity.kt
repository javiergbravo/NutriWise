package com.jgbravo.nutriwise.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jgbravo.nutriwise.app.navigation.NutriWiseNavigator
import com.jgbravo.nutriwise.app.theme.NutriWiseTheme
import com.jgbravo.nutriwise.ui.dashboard.DashboardScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutriWiseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NutriWiseNavigator()
                }
            }
        }
    }
}