package net.gelbelninoteam.cosmolotbattle.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun SpinImageBox(
    animatedScale: Float,
    randomImageList: IntArray
) {
    val shape = RoundedCornerShape(40)

    val imageBoxModifier =
        Modifier
            .height(100.dp)
            .width(100.dp)
            .background(MaterialTheme.colors.background, shape)
            .border(3.dp, MaterialTheme.colors.secondary, shape)
    val imageModifier = Modifier
        .height(90.dp)
        .width(90.dp)
        .graphicsLayer(scaleX = animatedScale, scaleY = animatedScale)

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .height(160.dp)
            .width(400.dp)
            .background(MaterialTheme.colors.primary, shape)
            .border(4.dp, MaterialTheme.colors.secondary, shape)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(400.dp)
        ) {
            Box(modifier = imageBoxModifier, contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = randomImageList[0]),
                    contentDescription = "",
                    modifier = imageModifier
                )
            }
            Box(modifier = imageBoxModifier, contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = randomImageList[1]),
                    contentDescription = "",
                    modifier = imageModifier
                )
            }
            Box(modifier = imageBoxModifier, contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = randomImageList[2]),
                    contentDescription = "",
                    modifier = imageModifier
                )
            }
        }
    }
}