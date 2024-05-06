package com.example.projeto02_lista_de_filmespro.ui.views

import androidx.annotation.DrawableRes
import com.example.projeto02_lista_de_filmespro.R

data class InsertFormUiState(
    @DrawableRes val picture: Int = R.drawable.zero_star, //certo
    val name: String = "",
    val sinopse: String = ""
)
