package com.example.progamer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.progamer.ui.theme.ProGamerTheme
import com.example.progamer.data.Datasource
import com.example.progamer.model.Game

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProGamerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProGamerApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProGamerApp() {
    val imagen = painterResource(R.drawable.fondoprogamerapp_2_0)
    var listaGames by remember {
        mutableStateOf(listOf<Game>())
    }
    Scaffold(
        //cabecera
        topBar = {
            TopAppBar()
        },
        //cuerpo

        content = {
            Box() {
                Image(
                    painter = imagen,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()

                )
                LazyColumn(
                    contentPadding = it,
                    //modifier = modifier
                ) {
                    Datasource().getGames { listaGames = it }
                    itemsIndexed(listaGames) { index, game ->
                        GameCard(
                            game = game,
                            modifier = Modifier.padding(
                                horizontal = dimensionResource(R.dimen.padding_xxsmall),
                                vertical = dimensionResource(R.dimen.padding_xxsmall)
                            )
                        )
                    }
                }

            }
        })
}









@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.mario),
                    contentDescription = null)
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.bodyLarge)

            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ProPreview() {
    ProGamerTheme {
        ProGamerApp()
    }
}