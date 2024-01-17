package com.example.movies.pages

import TMDB
import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movies.R
import com.example.movies.entities.res.Movie
import com.example.movies.entities.res.Result
import com.example.movies.entities.res.Videos
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
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
    var videos by remember {
        mutableStateOf<Videos?>(null)
    }
    GlobalScope.launch(Dispatchers.IO) {
        val movRes = api.getMovie(id!!.toInt(), "01b9f5d604812bcd787cd509a6336c8a")
        movie = movRes
        val vidRes = api.getVideos(id!!.toInt(), "01b9f5d604812bcd787cd509a6336c8a")
        videos = vidRes
    }

    movie?.let { movie ->

        Column(
            modifier = Modifier
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                //Text(text = movie.title, modifier = Modifier)
                //Text(text = movie.overview, modifier = Modifier)
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.5F)
                        .padding(5.dp)
                ) {
                    Text(text = movie.title, modifier = Modifier, fontSize = 30.sp)
                    Text(text = movie.overview, modifier = Modifier.padding(top = 20.dp))
                }
                Box {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/original" + movie.poster_path,
                        contentDescription = "Movie Poster",
                        error = painterResource(id = R.drawable.ic_launcher_background),
                        modifier = Modifier
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color.White,
                                        Color.Transparent
                                    )
                                )
                            )
                    )
                }
            }
            videos?.let { videos->
                for (video in videos.results) {
                    if (video.type === "Trailer" && video.site === "YouTube") {
                        YoutubeScreen(videoId = video.key, modifier = Modifier)
                        break
                    }
                }

            }
        }


    }


}
@Composable
fun YoutubeScreen(
    videoId: String,
    modifier: Modifier
) {
    val ctx = LocalContext.current
    AndroidView(factory = {
        var view = YouTubePlayerView(it)
        val fragment = view.addYouTubePlayerListener(
            object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.loadVideo("https://www.youtube.com/watch?v="+videoId, 0f)
                }
            }
        )
        view
    })
}