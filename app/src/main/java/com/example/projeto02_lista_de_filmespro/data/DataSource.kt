package com.example.projeto02_lista_de_filmespro.data

import com.example.projeto02_lista_de_filmespro.models.Movie
import com.example.projeto02_lista_de_filmespro.R

fun createMoviesList(): List<Movie> {

    val pictures = listOf(
        R.drawable.zero_star,
        R.drawable.one_star,
        R.drawable.two_star,
        R.drawable.three_star,
        R.drawable.four_star,
        R.drawable.five_star
    )

    val characters = listOf(
        "Yoda" to "Saga Star Wars",
        "Luke Skywalker" to "Ascensão de um Jedi",
        "Darth Vader" to "O Império Contra-Ataca",
        "Princess Leia" to "Uma Nova Esperança",
        "Han Solo" to "O Retorno de Jedi",
        "Harry Potter" to "O Prisioneiro de Azkaban"
    )

    val movies = characters.mapIndexed { index, (name, sinopse) ->
        Movie(picture = pictures[index % pictures.size], name = name, sinopse = sinopse)
    }
    return movies
}