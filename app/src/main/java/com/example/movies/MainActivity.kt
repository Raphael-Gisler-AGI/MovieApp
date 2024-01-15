package com.example.movies

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.movies.database.AppDatabase
import com.example.movies.navigation.NavigationHost
import com.example.movies.navigation.NavigationBar
import com.example.movies.ui.theme.MoviesTheme

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "movies.db"
        ).build()
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme {
                val navController = rememberNavController()
                Scaffold(bottomBar = { NavigationBar(navController) }) {
                    NavigationHost(navController)
                }
            }
        }
    }
}
