@file:JvmName("NavigationItemKt")

package com.example.movies.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movies.pages.AllMovies
import com.example.movies.pages.PersonalMovies

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.PersonalMovies.route) {
        composable(NavigationItem.PersonalMovies.route) {
            PersonalMovies()
        }
        composable(NavigationItem.AllMovies.route) {
            AllMovies()
        }
    }
}