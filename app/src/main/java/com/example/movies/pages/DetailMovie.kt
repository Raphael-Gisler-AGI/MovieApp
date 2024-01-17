package com.example.movies.pages

import TMDB
import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movies.R
import com.example.movies.entities.res.Movie
import com.example.movies.entities.res.Result
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
@OptIn(DelicateCoroutinesApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun DetailMovie(navController: NavHostController? = null, id: String? = "1") {
    val api = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TMDB::class.java)
    var movie by remember {
        mutableStateOf<Movie?>(null)
    }
    GlobalScope.launch(Dispatchers.IO) {
        val res = api.getMovie(id!!.toInt(), "01b9f5d604812bcd787cd509a6336c8a")
        movie = res
    }

    movie?.let { movie ->

        LazyRow(
            modifier = Modifier
        ) {
            item {
                Text(text = movie.title, modifier = Modifier)
                Box(
                    modifier = Modifier
                ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/original" + movie.poster_path,
                        contentDescription = "Movie Poster",
                        error = painterResource(id = R.drawable.ic_launcher_background),
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color.White, Color.Transparent
                                    )
                                )
                            )
                    )
                }


            }

        }


    }
}
