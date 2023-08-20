package com.jgbravo.nutriwise.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.jgbravo.nutriwise.app.navigation.NutriWiseNavigator
import com.jgbravo.nutriwise.app.theme.NutriWiseTheme

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