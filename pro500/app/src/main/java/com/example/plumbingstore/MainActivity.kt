package com.example.plumbingstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.plumbingstore.ui.navigation.AppNavigation
import com.example.plumbingstore.ui.theme.PlumbingStoreTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Главная активность приложения
 * Отвечает за инициализацию навигации и основных компонентов
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Применяем тему приложения
            PlumbingStoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // Инициализируем навигацию
                    val navController = rememberNavController()
                    AppNavigation(navController = navController)
                }
            }
        }
    }
} 