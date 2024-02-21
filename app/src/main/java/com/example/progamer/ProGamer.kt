package com.example.progamer


import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.progamer.model.Game
import com.example.progamer.model.GameRepository.games
import com.example.progamer.ui.theme.Shapes

@Composable
fun GamesList(gameList: List<Game>, modifier: Modifier = Modifier, contentPadding: PaddingValues = PaddingValues(0.dp)) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = modifier
        ) {
        itemsIndexed(games()) { index, game ->
            GameCard(
                game = game,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(R.dimen.padding_xxsmall),
                    vertical = dimensionResource(R.dimen.padding_xxsmall)
                ))

        }
    }
}

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
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
    else MaterialTheme.colorScheme.primaryContainer)

    var likesCount by remember { mutableStateOf(0) }
    Card (
        elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.padding_xxsmall)),
        modifier = modifier
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
                    modifier = Modifier
                        .clip(Shapes.small)
                        .size(
                            dimensionResource(R.dimen.image_size)
                        )
                ) {
                    Image(
                        painter = painterResource(game.imageRes),
                        contentDescription = null,
                        alignment = Alignment.TopCenter,
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = modifier.width(dimensionResource(R.dimen.space)))


                    Text(
                        modifier = Modifier.width(dimensionResource(R.dimen.titleSize)),
                        text = stringResource(game.nameRes),
                        style = MaterialTheme.typography.displayLarge,

                        )


                Spacer(modifier = Modifier.weight(1f))
                GameItemButtom(expanded = expanded, onClick = { expanded = !expanded })

            }
            if (expanded){
               GameDescription(
                    game.descriptionRes,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium),

                    )
               )
                //VideoPlayer(videoUri = Uri.parse(stringResource(game.videoUrlRes)))
                youtubePlayer(youtubeVideoId = stringResource(game.videoUrlRes), lifecycleOwner = LocalLifecycleOwner.current)

                Row {
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
                    GameShareButton(
                        youtubeVideoUrl = stringResource(game.videoUrlRes),
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
    @StringRes gameDescription: Int,
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
            text = stringResource(gameDescription),
            style = MaterialTheme.typography.displaySmall
        )

    }
}


@Preview
@Composable
fun ProGamerPreview(){
    ProGamerTheme {

        GameCard(
            game = Game(
                R.drawable.mario_odyssey,
                R.string.game1,
                R.string.description1,
                R.string.Video1
            ),
            modifier = Modifier)


        //GamesList(gameList = games())
    }
}
