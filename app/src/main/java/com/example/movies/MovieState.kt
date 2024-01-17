package com.example.movies

import com.example.movies.entities.Movie

data class MovieState(
    val movies: List<Movie> = emptyList(),
    val name: String = ""
)