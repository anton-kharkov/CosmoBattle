package net.gelbelninoteam.cosmolotbattle.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CosmoLotImageButton(
    onClick: () -> Unit,
    modifier: Modifier,
    shape: Shape,
    imageId: Int,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
        enabled = enabled
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "",
            modifier = Modifier
                .height(48.dp)
                .width(48.dp)
        )
    }
}