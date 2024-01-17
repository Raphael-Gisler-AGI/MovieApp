package com.example.movies

sealed interface MovieEvent {
    object SaveMovie: MovieEvent
    data class SetName(val name: String): MovieEvent
}