package com.example.progamer.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.progamer.R

val Honk = FontFamily(
    Font(R.font.honk_regular)
)
val Dosis = FontFamily(
    Font(R.font.dosis_bold),
    Font(R.font.dosis_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Honk,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    //para el titulo de cada juego
    displayLarge = TextStyle(
        fontFamily = Dosis,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,

    ),
    displayMedium = TextStyle(
        fontFamily = Dosis,
        fontWeight = FontWeight.Normal,
        fontSize = 25.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Dosis,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    )
)