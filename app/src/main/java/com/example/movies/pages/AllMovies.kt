package com.example.movies.pages

import TMDB
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movies.R
import com.example.movies.entities.res.Result
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val URL = "https://api.themoviedb.org/3/"
@OptIn(DelicateCoroutinesApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun AllMovies(navController: NavController) {
    fun route(id: Int) {
        Log.d("Tag", "$id")
        navController.navigate("movie/$id")
    }
    val api = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TMDB::class.java)
    var movies by remember {
        mutableStateOf(listOf<Result>())
    }
    GlobalScope.launch(Dispatchers.IO) {
        val res = api.getPopularMovies(1, "01b9f5d604812bcd787cd509a6336c8a")
        movies = res.results
    }
    LazyVerticalGrid(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 40.dp),
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(movies) { movie ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { route(movie.id) }
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500" + movie.poster_path,
                    contentDescription = "Movie Poster",
                    error = painterResource(id = R.drawable.ic_launcher_background)
                )
                Text(
                    text = movie.title,
                )
            }

        }
    }
}

