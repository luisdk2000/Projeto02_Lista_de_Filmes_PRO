package com.example.projeto02_lista_de_filmespro.ui.views

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.projeto02_lista_de_filmespro.R

data class AppUiState(
    @StringRes val title: Int = R.string.movie_list,
    @DrawableRes val fabIcon: Int = R.drawable.add,
    @StringRes val iconContentDescription: Int = R.string.insert_new_movie,
)
