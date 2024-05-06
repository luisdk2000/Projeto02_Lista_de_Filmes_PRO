package com.example.projeto02_lista_de_filmespro.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.projeto02_lista_de_filmespro.R
import com.example.projeto02_lista_de_filmespro.models.Movie
import com.example.projeto02_lista_de_filmespro.ui.theme.Projeto02_Lista_de_FilmesPROTheme
import com.example.projeto02_lista_de_filmespro.viewmodels.MovieListViewModel

@Composable
fun MovieList(viewModel: MovieListViewModel,
                navController: NavController,
                modifier: Modifier = Modifier
){
    val uiState by viewModel.movieListUiState.collectAsState()
    LazyColumn {
        items(uiState.movieList){movie ->
            MovieCard(movie = movie,
                onDelete = viewModel::deleteMovie,
                onEditMovie = {
                    viewModel.onEditMovie(
                        movie = movie,
                        navController = navController,
                    )
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier,
    onDelete: (Movie) -> Unit,
    onEditMovie: () -> Unit
){
    Card (
        modifier = modifier
            .fillMaxSize()
            .padding(2.dp)
            .clickable {
                onEditMovie()
            }
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxSize()
        ){
            Image(modifier = modifier
                .size(50.dp)
                .padding(start = 4.dp, end = 4.dp),
                painter = painterResource(id = movie.picture) ,
                contentDescription = null)
            Column {
                Text(text = movie.name, fontWeight =  FontWeight.Bold, fontSize = 22.sp)
                Text(modifier =modifier.width(260.dp),
                    text = movie.sinopse,
                    fontStyle = FontStyle.Italic,
                    fontSize = 12.sp,
                    color = Color.Black,
                    maxLines = 2,
                )
            }
            Spacer(modifier = modifier.weight(1F))
            Image(modifier = modifier
                .padding(end = 8.dp)
                .clickable { onDelete(movie) },
                painter = painterResource(id = R.drawable.delete), contentDescription = "deletar" )
        }

    }
}
