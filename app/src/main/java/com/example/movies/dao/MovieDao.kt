package com.example.movies.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.movies.entities.Movie

interface MovieDao {
    @Insert
    fun insert(movie: Movie)

    @Query("SELECT name FROM movie")
    fun getAll(): List<Movie>
}