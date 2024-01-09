package com.example.composecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composecodelab.ui.theme.ComposeCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    DiceImageAndButton()
                }
            }
        }
    }
}

@Composable
fun DiceImageAndButton(modifier: Modifier = Modifier) {
    Box(
        Modifier.fillMaxSize()
    ) {
        var result by remember { mutableStateOf(1) }
        val imgRes = when (result) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        //뒤의 동작에서 result값이 변함에 따라 imgRes도 재정의 된다.
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = imgRes),
                contentDescription = null
            )
            Button(
                onClick = { result = (1..6).random() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Click")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DiceImageAndButtonPreview() {
    ComposeCodelabTheme {
        DiceImageAndButton()
    }
}
