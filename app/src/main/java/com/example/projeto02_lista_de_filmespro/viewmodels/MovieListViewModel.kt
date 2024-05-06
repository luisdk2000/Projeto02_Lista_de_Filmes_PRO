package com.example.projeto02_lista_de_filmespro.viewmodels

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.projeto02_lista_de_filmespro.R
import com.example.projeto02_lista_de_filmespro.data.createMoviesList
import com.example.projeto02_lista_de_filmespro.models.Movie
import com.example.projeto02_lista_de_filmespro.ui.views.AppScreens
import com.example.projeto02_lista_de_filmespro.ui.views.AppUiState
import com.example.projeto02_lista_de_filmespro.ui.views.MovieListUiState
import com.example.projeto02_lista_de_filmespro.ui.views.InsertFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MovieListViewModel : ViewModel(){
    private  val _movieListUiState: MutableStateFlow<MovieListUiState> =
        MutableStateFlow(MovieListUiState(createMoviesList()))
    val movieListUiState: StateFlow<MovieListUiState> =
        _movieListUiState.asStateFlow()

    private val _appUiState: MutableStateFlow<AppUiState> =
        MutableStateFlow(AppUiState())
    val appUiState: StateFlow<AppUiState> =
        _appUiState.asStateFlow()

    private  val _insertFormUiState: MutableStateFlow<InsertFormUiState> =
        MutableStateFlow(InsertFormUiState())
    val insertFormUiState: StateFlow<InsertFormUiState> =
        _insertFormUiState.asStateFlow()

    private var movieToEdit: Movie = Movie()
    private var editMovie: Boolean = false

    fun navigate(navController: NavController){
        if (_appUiState.value.title == R.string.movie_list){

            _appUiState.update { currentState -> currentState.copy(
                title = R.string.insert_movie,
                fabIcon = R.drawable.add,
                iconContentDescription = R.string.confirm
            )
            }
            navController.navigate(AppScreens.InsertMovie.name)
        }else{
            val movies: MutableList<Movie> =
                _movieListUiState.value.movieList.toMutableList()
            if(editMovie){
                val pos = movies.indexOf(movieToEdit)
                movies.removeAt(pos)
                movies.add(pos, Movie(
                    _insertFormUiState.value.picture,
                    _insertFormUiState.value.name,
                    _insertFormUiState.value.sinopse
                ))
                movieToEdit = Movie()
                editMovie = false
            }else{
                movies.add(
                    Movie(
                        picture = _insertFormUiState.value.picture,
                        name = _insertFormUiState.value.name,
                        sinopse = _insertFormUiState.value.sinopse

                    )
                )

            }
            _movieListUiState.update { currentState ->
                currentState.copy(
                    movieList = movies.toList()
                )
            }
            _insertFormUiState.update {
                InsertFormUiState()
            }
            _appUiState.update { currentState -> AppUiState() }
            navController.navigate(AppScreens.MovieList.name){
                popUpTo(AppScreens.MovieList.name){
                    inclusive = true
                }
            }
        }
    }
    fun navigateBack(navController: NavController){
        _appUiState.update {
                currentState -> AppUiState()
        }
        navController.popBackStack()
    }
    fun deleteMovie(movie: Movie){
        val movies: MutableList<Movie> = _movieListUiState.value.movieList.toMutableList()
        movies.remove(movie)
        _movieListUiState.value = _movieListUiState.value.copy(
            movieList = movies.toList()
        )
    }
    fun onEditMovie(movie: Movie, navController: NavController){
        editMovie = true
        movieToEdit = movie
        _insertFormUiState.update { currentStates ->
            currentStates.copy(
                picture = movieToEdit.picture,
                name = movieToEdit.name,
                sinopse = movieToEdit.sinopse
            )
        }
        _appUiState.update { currentStatus ->
            currentStatus.copy(
                title =R.string.edit_movie,
                fabIcon = R.drawable.edit,
                iconContentDescription = R.string.confirm
            )
        }
        navController.navigate(route = AppScreens.InsertMovie.name)
    }
    fun onPictureChange(@DrawableRes picture: Int){
        _insertFormUiState.update { currentStates ->
            currentStates.copy(
                picture = picture
            )
        }
    }
    fun onNameChange(name: String){
        _insertFormUiState.update { currentStates ->
            currentStates.copy(
                name = name
            )
        }
    }
    fun onSinopseChange(sinopse: String){
        _insertFormUiState.update { currentStates ->
            currentStates.copy(
                sinopse = sinopse
            )
        }
    }
}