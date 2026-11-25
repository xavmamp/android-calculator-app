package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.myapp.calculator.CalculatorScreen
import com.example.myapp.calculator.CalculatorTheme
import com.example.myapp.login.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                var isLoggedIn by rememberSaveable { mutableStateOf(false) }

                Crossfade(
                    targetState = isLoggedIn,
                    animationSpec = tween(durationMillis = 400),
                    label = "ScreenTransition"
                ) { loggedIn ->
                    if (loggedIn) {
                        CalculatorScreen(onLogout = { isLoggedIn = false })
                    } else {
                        LoginScreen(onLoginSuccess = { isLoggedIn = true })
                    }
                }
            }
        }
    }
}
