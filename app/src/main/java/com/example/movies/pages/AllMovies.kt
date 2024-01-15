package com.example.movies.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AllMovies() {
    LazyColumn( contentPadding = PaddingValues(16.dp), modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        item {
            Text(text = "All the movies in the world")
        }
    }
}