package com.jgbravo.nutriwise.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.jgbravo.nutriwise.ui.api.theme.NutriWiseTheme
import com.jgbravo.nutriwise.ui.navigation.AppNavigator
import com.jgbravo.nutriwise.ui.navigation.NutriWiseNavigator
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val appNavigator: AppNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NutriWiseTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NutriWiseNavigator(
                        appNavigator = appNavigator,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}