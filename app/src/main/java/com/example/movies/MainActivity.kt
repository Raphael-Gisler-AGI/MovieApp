package com.example.movies

import android.annotation.SuppressLint
import com.example.movies.ui.theme.MoviesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import androidx.room.Room

import com.example.movies.database.AppDatabase
import com.example.movies.navigation.NavigationBar
import com.example.movies.navigation.NavigationHost

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "movies.db"
        ).build()
    }
    private val viewModel by viewModels<MovieViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MovieViewModel(db.movieDao) as T
                }
            }
        }
    )
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme {
                val navController = rememberNavController()
                Scaffold(bottomBar = { NavigationBar(navController) }, modifier = Modifier.padding(16.dp)) {
                    val state: MovieState by viewModel.state.collectAsState()
                    NavigationHost(navController, state, viewModel::onEvent)
                }
            }
        }
    }
}
