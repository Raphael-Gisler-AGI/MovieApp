package com.example.movies.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    val name: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)