package com.example.projeto02_lista_de_filmespro.ui.views

import com.example.projeto02_lista_de_filmespro.models.Movie

data class MovieListUiState(
    val movieList: List<Movie> = listOf()
)