package com.example.progamer.model

import androidx.compose.ui.res.stringResource
import com.example.progamer.R

object GameRepository {

    fun games(): List<Game> {

        return listOf <Game>(
            Game(
                imageRes = R.drawable.mario_odyssey,
                nameRes = R.string.game1,
                descriptionRes = R.string.description1,
                videoUrlRes = R.string.Video1
            ),
            Game(
                imageRes = R.drawable.residentevil4,
                nameRes = R.string.game2,
                descriptionRes = R.string.description2,
                videoUrlRes = R.string.Video2
            ),
            Game(
                imageRes = R.drawable.guardianesgalaxia,
                nameRes = R.string.game3,
                descriptionRes = R.string.description3,
                videoUrlRes = R.string.Video3
            ),
            Game(
                imageRes = R.drawable.forzahorizon5,
                nameRes = R.string.game4,
                descriptionRes = R.string.description4,
                videoUrlRes = R.string.Video4
            ),
            Game(
                imageRes = R.drawable.lugismansion,
                nameRes = R.string.game5,
                descriptionRes = R.string.description5,
                videoUrlRes = R.string.Video5
            ),
            Game(
                imageRes = R.drawable.zeldabotw,
                nameRes = R.string.game6,
                descriptionRes = R.string.description6,
                videoUrlRes = R.string.Video6
            ),
            Game(
                imageRes = R.drawable.supersmashbros,
                nameRes = R.string.game7,
                descriptionRes = R.string.description7,
                videoUrlRes = R.string.Video7
            ),
            Game(
                imageRes = R.drawable.mariokart,
                nameRes = R.string.game8,
                descriptionRes = R.string.description8,
                videoUrlRes = R.string.Video8
            ),
            Game(
                imageRes = R.drawable.starwars,
                nameRes = R.string.game9,
                descriptionRes = R.string.description9,
                videoUrlRes = R.string.Video9
            ),
            Game(
                imageRes = R.drawable.mariowonder,
                nameRes = R.string.game10,
                descriptionRes = R.string.description10,
                videoUrlRes = R.string.Video10
            )
        )
    }

}