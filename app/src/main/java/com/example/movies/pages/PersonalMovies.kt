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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movies.R

@Composable
fun PersonalMovies() {
    LazyColumn( contentPadding = PaddingValues(16.dp), modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        item {
            Text(text = "hehehehaaaaaa")
        }
    }
}