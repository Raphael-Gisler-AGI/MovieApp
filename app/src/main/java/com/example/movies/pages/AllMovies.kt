package com.example.movies.pages

import android.util.Log
import API
import android.annotation.SuppressLint

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movies.entities.Comment

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory


const val URL = "https://jsonplaceholder.typicode.com"
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun AllMovies() {
    val api = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(API::class.java)
    var test by remember {
        mutableStateOf(listOf<Comment>())
    }
    GlobalScope.launch(Dispatchers.IO) {
        val comments = api.getComments().await()
        test = comments
    }


    LazyColumn( contentPadding = PaddingValues(16.dp), modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {


        items(test) {comment ->
            Text(text = comment.name)

        }
    }
}