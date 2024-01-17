@file:JvmName("NavigationItemKt")

package com.example.movies.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movies.MovieEvent
import com.example.movies.MovieState
import com.example.movies.pages.AllMovies
import com.example.movies.pages.DetailMovie
import com.example.movies.pages.PersonalMovies

@Composable
fun NavigationHost(navController: NavHostController, state: MovieState, onEvent: (MovieEvent) -> Unit) {
    NavHost(navController, startDestination = NavigationItem.PersonalMovies.route) {
        composable(NavigationItem.PersonalMovies.route) {
            PersonalMovies(state, onEvent)
        }
        composable(NavigationItem.AllMovies.route) {
            AllMovies(navController)
        }
        composable("movie/{id}") {backStackEntry ->
            DetailMovie(navController, backStackEntry.arguments?.getString("id"))
        }
    }
}