package com.example.movies.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavigationBar(navController: NavController) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationItem::class.sealedSubclasses.forEach{ itemClass ->
            val item = itemClass.objectInstance!!
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = { navController.navigate(route = item.route) },
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.label) }
            )
        }
    }
}