package net.gelbelninoteam.cosmolotbattle.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = MidToneBlue,
    primaryVariant = LightGrey,
    secondary = BrightOrange,
    background = LightBlue
)

@Composable
fun CosmoLotBattleTheme(
    content: @Composable () -> Unit
) {
    val colors = DarkColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}