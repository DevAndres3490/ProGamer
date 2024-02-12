package com.example.progamer.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Game(
    @DrawableRes val imageRes: Int,
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    @StringRes val videoUrlRes: Int
)

