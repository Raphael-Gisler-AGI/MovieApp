package com.example.movies.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val route: String, val icon: ImageVector, val label: String) {
    object PersonalMovies : NavigationItem("PersonalMovies", Icons.Default.Person, "Personal")
    object AllMovies : NavigationItem("AllMovies", Icons.Default.Menu, "All")
}