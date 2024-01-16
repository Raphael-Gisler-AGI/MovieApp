package com.example.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.dao.MovieDao
import com.example.movies.entities.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MovieViewModel(
    private val dao: MovieDao
) : ViewModel() {
    private val _movies = dao.getAll()
    private val _state = MutableStateFlow(MovieState())

    val state = combine(_state, _movies) { state, movies ->
        state.copy(
            movies = movies
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MovieState())
    fun onEvent(event: MovieEvent) {
        when(event) {
            is MovieEvent.SaveMovie -> {
                val name = state.value.name
                val movie = Movie(
                    name = name
                )
                viewModelScope.launch {
                    dao.insert(movie)
                }
            }
            is MovieEvent.SetName -> {
                _state.update { it.copy(
                    name = event.name
                )}
            }
        }
    }
}