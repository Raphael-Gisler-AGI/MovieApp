package com.example.movies.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
//<<<<<<< HEAD
import coil.compose.AsyncImage
import com.example.movies.R
//=======
import androidx.compose.ui.unit.sp
import com.example.movies.MovieEvent
import com.example.movies.MovieState
//>>>>>>> 97769682b9c3239c17c044fbb7db71cd7d594000

@Composable
fun PersonalMovies(state: MovieState, onEvent: (MovieEvent) -> Unit) {
    LazyColumn( contentPadding = PaddingValues(16.dp), modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        item {
            OutlinedTextField(
                value = state.name,
                onValueChange = { onEvent(MovieEvent.SetName(it)) },
                label = { Text("Movie Name") }
            )
            Button(onClick = {
                onEvent(MovieEvent.SaveMovie)
            }) {
                Text("Add Movie")
            }
        }
        items(state.movies) {movie ->
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedCard(
                        modifier = Modifier.fillMaxWidth().padding(20.dp)
                    ) {
                        Text(text = movie.name, fontSize = 20.sp)
                    }
                }
            }
        }
    }
}
