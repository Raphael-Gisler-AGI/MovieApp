package com.example.movies.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movies.entities.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert
    suspend fun insert(movie: Movie)

    @Query("SELECT id, name FROM movie")
    fun getAll(): Flow<List<Movie>>
}