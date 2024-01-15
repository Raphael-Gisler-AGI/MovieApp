package com.example.movies.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies.dao.MovieDao
import com.example.movies.entities.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}