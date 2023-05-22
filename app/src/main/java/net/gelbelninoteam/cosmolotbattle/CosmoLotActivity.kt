package net.gelbelninoteam.cosmolotbattle

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.gelbelninoteam.cosmolotbattle.ui.components.CosmoLotImageButton
import net.gelbelninoteam.cosmolotbattle.ui.components.InfoBoard
import net.gelbelninoteam.cosmolotbattle.ui.components.SpinImageBox
import net.gelbelninoteam.cosmolotbattle.ui.theme.CosmoLotBattleTheme

class CosmoLotActivity : ComponentActivity() {

    private val buttonModifier = Modifier
        .width(80.dp)
        .height(80.dp)
    private val buttonShape = RoundedCornerShape(50)

    private var score = 1000
    private var bit = 50

    private lateinit var sharedPreferences: SharedPreferences

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        @Suppress("DEPRECATION")
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        sharedPreferences =
            getSharedPreferences(getString(R.string.score_pref), Context.MODE_PRIVATE)
        score = sharedPreferences.getInt(getString(R.string.score_key), 1000)

        setContent {
            CosmoLotBattleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val imageList = arrayListOf(
                        R.drawable.planet_blue,
                        R.drawable.planet_earth,
                        R.drawable.planet_ice,
                        R.drawable.planet_red,
                        R.drawable.planet_purple,
                    )

                    val randomImageList by remember {
                        mutableStateOf(
                            intArrayOf(
                                R.drawable.planet_earth,
                                R.drawable.planet_earth,
                                R.drawable.planet_earth
                            )
                        )
                    }

                    var startAnimation by remember { mutableStateOf(false) }

                    val animatedScale by animateFloatAsState(
                        targetValue = if (startAnimation) 0f else 1f,
                        animationSpec = tween(durationMillis = 500)
                    )

                    var mutableScore by remember { mutableStateOf(score) }
                    var mutableBit by remember { mutableStateOf(bit) }
                    var mutableWin by remember { mutableStateOf("0") }
                    var spinStart by remember { mutableStateOf(true) }
                    var index: Int
                    var win: Int

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            InfoBoard(
                                mainText = stringResource(id = R.string.score),
                                textInfo = mutableScore.toString()
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(60.dp)
                                    .background(
                                        MaterialTheme.colors.primary,
                                        RoundedCornerShape(30)
                                    )
                                    .border(
                                        3.dp,
                                        MaterialTheme.colors.secondary,
                                        RoundedCornerShape(30)
                                    )
                            ) {
                                Box(
                                    contentAlignment = Alignment.BottomCenter, modifier = Modifier
                                        .width(100.dp)
                                        .height(30.dp)
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.bit),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Box(
                                    contentAlignment = Alignment.Center, modifier = Modifier
                                        .width(100.dp)
                                        .height(60.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .height(3.dp)
                                            .width(100.dp)
                                            .background(MaterialTheme.colors.secondary)
                                    )
                                }
                                Box(
                                    contentAlignment = Alignment.BottomCenter,
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(55.dp)
                                ) {
                                    Text(text = "$mutableBit", fontSize = 17.sp)
                                }
                            }
                            SpinImageBox(
                                animatedScale = animatedScale,
                                randomImageList = randomImageList
                            )
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.width(400.dp)
                            ) {
                                CosmoLotImageButton(
                                    onClick = {
                                        if (mutableBit > 50) {
                                            mutableBit -= 50
                                            mutableScore += 50
                                            bit = mutableBit
                                            score = mutableScore
                                        }
                                    },
                                    modifier = buttonModifier,
                                    shape = buttonShape,
                                    imageId = R.drawable.ic_minus,
                                    enabled = spinStart
                                )
                                Button(
                                    onClick = {
                                        spinStart = false

                                        GlobalScope.launch {
                                            repeat(4) {
                                                index = 0
                                                startAnimation = true
                                                delay(500)

                                                do {
                                                    randomImageList[index] = imageList.random()
                                                    index++
                                                } while (index != 3)

                                                startAnimation = false
                                                delay(500)
                                            }

                                            spinStart = true

                                            win = if (
                                                randomImageList[0] == randomImageList[1] &&
                                                randomImageList[1] == randomImageList[2]
                                            ) {
                                                mutableBit * 5
                                            } else if (
                                                randomImageList[0] == randomImageList[1] ||
                                                randomImageList[1] == randomImageList[2]
                                            ) {
                                                mutableBit * 2
                                            } else {
                                                0
                                            }

                                            if (win != 0) {
                                                mutableScore += win
                                                mutableWin = "+ $win"
                                                score = mutableScore
                                            } else {
                                                if (mutableScore - mutableBit <= 0) {
                                                    if (mutableScore == 0) {
                                                        mutableBit = 50
                                                    } else {
                                                        mutableScore = 0
                                                    }
                                                } else {
                                                    mutableScore -= mutableBit
                                                }
                                                mutableWin = "- $mutableBit"

                                                bit = mutableBit
                                                score = mutableScore
                                            }
                                        }
                                    },
                                    modifier = buttonModifier,
                                    shape = buttonShape,
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = MaterialTheme.colors.secondary
                                    ),
                                    enabled = spinStart
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.spin),
                                        fontSize = 19.sp,
                                        color = MaterialTheme.colors.primary
                                    )
                                }
                                CosmoLotImageButton(
                                    onClick = {
                                        if (mutableScore >= 50) {
                                            mutableBit += 50
                                            mutableScore -= 50
                                            bit = mutableBit
                                            score = mutableScore
                                        }
                                    },
                                    modifier = buttonModifier,
                                    shape = buttonShape,
                                    imageId = R.drawable.ic_plus,
                                    enabled = spinStart
                                )
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            InfoBoard(
                                mainText = stringResource(id = R.string.last_sum),
                                textInfo = mutableWin
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()

        if (bit > 50) {
            score += bit - 50
        }

        sharedPreferences.edit {
            putInt(getString(R.string.score_key), score)
        }
    }
}