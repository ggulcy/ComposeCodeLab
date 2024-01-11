package com.example.composecodelab

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecodelab.ui.theme.ComposeCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

@Composable
fun ArtSpace(modifier: Modifier = Modifier) {
    var imgIdx by remember { mutableStateOf(0) }
    val painter = painterResource(id = getImageFromIdx(idx = imgIdx))
    val descriptions = getDescription(idx = imgIdx)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(start = 24.dp, end = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtImage(
            painter = painter,
            modifier = Modifier
                .weight(8f)
                .padding(top = 60.dp, bottom = 48.dp)
        )
        DescriptionText(
            descriptions = descriptions,
            modifier = Modifier
                .weight(1.5f)
        )
        ButtonLayout(
            modifier = Modifier.weight(1f),
            prevClick = {
                if(imgIdx >= 0) imgIdx -= 1
                else imgIdx = 4
            },
            nextClick = {
                imgIdx = (imgIdx+1)%5
            }
        )
    }
}
@DrawableRes
fun getImageFromIdx(idx: Int): Int {
    return when (idx) {
        0 -> R.drawable.art_image_1
        1 -> R.drawable.art_image_2
        2 -> R.drawable.art_image_3
        3 -> R.drawable.art_image_4
        else -> R.drawable.art_image_5
    }
}

fun getDescription(idx: Int): Pair<String, String> {
    return when (idx) {
        0 -> Pair("ArtImage Gmarket Android Developer", "by ChanhyPark")
        1 -> Pair("ArtImage Apple", "by USA")
        2 -> Pair("ArtImage Tesla", "by USA")
        3 -> Pair("ArtImage MSFT", "by USA")
        else -> Pair("ArtImage SAMSUNG", "by KOREA")
    }
}

@Composable
fun ArtImage(
    painter: Painter,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shadowElevation = 10.dp
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.padding(32.dp)
        )
    }
}

@Composable
fun DescriptionText(descriptions: Pair<String, String>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(colorResource(id = R.color.light_gray))
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text(
            text = descriptions.first,
            fontSize = 24.sp,
            fontWeight = FontWeight.Light
        )
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                ) {
                    append(descriptions.second)
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 14.sp
                    )
                ) {
                    append("  (2023)")
                }
            }
        )
    }
}

@Composable
fun ButtonLayout(
    prevClick: () -> Unit,
    nextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(modifier = Modifier.weight(1f), onClick = prevClick) {
            Text("Prev")
        }
        Spacer(modifier = Modifier.weight(0.3f))
        Button(modifier = Modifier.weight(1f), onClick = nextClick) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtImage() {
    ComposeCodelabTheme {
        ArtSpace()
    }
}