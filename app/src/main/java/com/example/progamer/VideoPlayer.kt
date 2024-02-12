package com.example.progamer

import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.viewinterop.AndroidView
import com.example.progamer.ui.theme.Shapes

@Composable
fun VideoPlayer(
    videoUri: Uri
)
{
    AndroidView(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .clip(Shapes.small)
            .fillMaxWidth(),
        factory = { context ->
        VideoView(context).apply {
            setVideoURI(videoUri)

            val mediaController = MediaController(context)
            mediaController.setAnchorView(this)

            setMediaController(mediaController)

            setOnPreparedListener{
                start()
            }
        }

    })
}
