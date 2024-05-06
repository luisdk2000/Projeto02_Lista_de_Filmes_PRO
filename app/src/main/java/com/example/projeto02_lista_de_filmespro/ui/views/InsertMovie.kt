package com.example.projeto02_lista_de_filmespro.ui.views

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projeto02_lista_de_filmespro.R
import com.example.projeto02_lista_de_filmespro.viewmodels.MovieListViewModel

@Composable
fun InsertMovie(
    viewModel: MovieListViewModel,
    navController: NavController,

    ) {
    BackHandler {
        viewModel.navigateBack(navController)
    }
    val uiState by viewModel.insertFormUiState.collectAsState()
    insertForm(
        picture = uiState.picture,
        name = uiState.name,
        sinopse = uiState.sinopse,
        onUpdatePicture = viewModel::onPictureChange,
        onUpdateName = viewModel::onNameChange,
        onUpdateSinopse = viewModel::onSinopseChange
    )
}

@Composable
fun insertForm(
    @DrawableRes picture: Int,
    name: String,
    sinopse: String,
    onUpdatePicture: (Int) -> Unit,
    onUpdateName: (String) -> Unit,
    onUpdateSinopse: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val imageList = listOf(
        R.drawable.zero_star,
        R.drawable.one_star,
        R.drawable.two_star,
        R.drawable.three_star,
        R.drawable.four_star,
        R.drawable.five_star
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    )
    {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier.fillMaxWidth()
        ) {


            items(imageList) { image ->
                Box(
                    modifier = modifier
                        .size(100.dp)
                        .padding(8.dp)
                        .background(if (image == picture) Color.LightGray else Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Image(painter = painterResource(id = image),
                        contentDescription = null,
                        modifier = modifier
                            .size(200.dp)
                            .clickable {
                                onUpdatePicture(image)
                            }
                    )
                }
            }
        }
        Spacer(modifier = modifier.height(8.dp))
        TextField(
            value = name,
            onValueChange = onUpdateName,
            label = { Text(text = "Nome") },
            singleLine = true,
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.height(8.dp))
        TextField(
            value = sinopse,
            onValueChange =onUpdateSinopse,
            label = { Text(text = "Sinopse") },
            singleLine = false,
            minLines = 1, maxLines = 3,
            modifier = modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InsertFormpreview() {
    insertForm(R.drawable.edit,"","",{},{},{})
}