package com.example.projeto02_lista_de_filmespro.models

import androidx.annotation.DrawableRes
import com.example.projeto02_lista_de_filmespro.R

data class Movie(
    @DrawableRes val picture: Int = R.drawable.one_star,
    val name: String ="",
    val sinopse: String =""
)
