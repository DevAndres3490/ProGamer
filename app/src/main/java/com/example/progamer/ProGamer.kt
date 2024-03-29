package com.example.progamer


import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.progamer.ui.theme.ProGamerTheme
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.contentColorFor


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.example.progamer.data.Datasource
import com.example.progamer.model.Game
import com.example.progamer.ui.theme.Shapes
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlin.math.log

@Composable
fun GameCard(
    game: Game,
    modifier: Modifier
){

    val context = LocalContext.current
    var liked by remember {
        mutableStateOf(true)
    }
    var expanded by remember {
        mutableStateOf(false)
    }

    val gradientColors = listOf(
        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 1f),
        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f)
    )

    val gradient = Brush.verticalGradient(colors = gradientColors)

    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f)
        else MaterialTheme.colorScheme.primaryContainer, label = ""
    )

    var likesCount by remember { mutableStateOf(0) }
    Card (
        elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.padding_xxsmall)),
        modifier = modifier.background(brush = gradient),
        colors = CardDefaults
            .cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
            )
    )
    {
        Column(
            modifier = modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .background(color = color)

        ) {

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium))
                    .sizeIn(minHeight = 72.dp)

            ) {
                Box(

                ) {
                    AsyncImage(
                        model = game.imageRes,
                        contentDescription = null,
                        alignment = Alignment.TopCenter,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(Shapes.small)
                            .size(
                                dimensionResource(R.dimen.image_size)
                            )
                    )
                }
                Spacer(modifier = modifier.width(dimensionResource(R.dimen.space)))

                Text(
                    modifier = Modifier.width(dimensionResource(R.dimen.titleSize)),
                    text = game.nameRes,
                    style = MaterialTheme.typography.displayLarge,

                    )

                Spacer(modifier = Modifier.weight(1f))
                GameItemButtom(expanded = expanded, onClick = { expanded = !expanded })

            }
            if (expanded){
                GameDescription(gameDescription = game.descriptionRes,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium),

                        )
                )
                //VideoPlayer(videoUri = Uri.parse(stringResource(game.videoUrlRes)))
                youtubePlayer(youtubeVideoId = game.videoUrlRes, lifecycleOwner = LocalLifecycleOwner.current)
//fila gamelikebutton, shareButton
                Row() {
                    GameLikeButtom(
                        like = liked,
                        onClick = {
                            likesCount++
                            Toast.makeText(context, "Likes: $likesCount", Toast.LENGTH_SHORT).show()
                        }
                    )
                    Text(
                        text = "Likes: $likesCount",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = modifier.padding(top = 20.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    GameShareButton(
                        youtubeVideoUrl = game.videoUrlRes,
                        context = LocalContext.current
                    )
                }

            }

        }

    }
}



@Composable
private fun GameItemButtom(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){

    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary,

            )

    }

}

@Composable
private fun GameLikeButtom(
    like: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    //context: Context
){
    IconButton(
        onClick = onClick,
        modifier = modifier.animateContentSize(
            animationSpec =  spring(dampingRatio = Spring.DampingRatioHighBouncy, stiffness = Spring.StiffnessVeryLow)
        ),

        ) {
        Icon(
            imageVector = if(like) Icons.Filled.FavoriteBorder else Icons.Filled.Favorite,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun GameShareButton(
    youtubeVideoUrl: String,
    modifier: Modifier = Modifier,
    context: Context
){

    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, youtubeVideoUrl)
        type = "text/plain"

    }
    IconButton(
        onClick = {
            startActivity(context, shareIntent, null)
            Toast.makeText(context, "Copiado al portapapeles", Toast.LENGTH_SHORT).show()
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )


    }

}

@Composable
fun GameDescription(
    //game: Game,
    gameDescription: String,
    modifier: Modifier = Modifier

){
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.review),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = gameDescription,
            style = MaterialTheme.typography.displaySmall
        )

    }
}

