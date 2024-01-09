package com.example.composecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
                    MainContainer()
                }
            }
        }
    }
}
@Composable
fun MainContainer(modifier: Modifier = Modifier) {
    Column {
        TextContainer()
    }
}


@Composable
fun TextContainer(modifier: Modifier = Modifier) {
    Column {
        Image(
            painter = painterResource(id = R.drawable.bg_compose_background),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = stringResource(id = R.string.title),
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = stringResource(id = R.string.description),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = stringResource(id = R.string.content),
            modifier = Modifier.padding(16.dp).align(Alignment.End),
            textAlign = TextAlign.Justify
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ComposeTutorialPreview() {
    ComposeCodelabTheme {
        MainContainer()
    }
}