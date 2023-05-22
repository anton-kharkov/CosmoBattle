package net.gelbelninoteam.cosmolotbattle

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.gelbelninoteam.cosmolotbattle.ui.components.CosmoLotImageButton
import net.gelbelninoteam.cosmolotbattle.ui.theme.CosmoLotBattleTheme
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {

    private val buttonModifier = Modifier
        .width(100.dp)
        .height(100.dp)
    private val buttonShape = RoundedCornerShape(50)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        @Suppress("DEPRECATION")
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContent {
            CosmoLotBattleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CosmoLotImageButton(
                            onClick = {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        CosmoLotActivity::class.java
                                    )
                                )
                            },
                            modifier = buttonModifier,
                            shape = buttonShape,
                            imageId = R.drawable.ic_play
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        CosmoLotImageButton(
                            onClick = {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        PrivacyPolicyActivity::class.java
                                    )
                                )
                            },
                            modifier = buttonModifier,
                            shape = buttonShape,
                            imageId = R.drawable.ic_policy
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        CosmoLotImageButton(
                            onClick = {
                                finish()
                                exitProcess(0)
                            },
                            modifier = buttonModifier,
                            shape = buttonShape,
                            imageId = R.drawable.ic_exit
                        )
                    }
                }
            }
        }
    }
}