package com.example.composecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun Container() {
    Box(
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.quad1))
    ) {
        MainSection(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(bottom = 100.dp)
        )
        InfoSection(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
        )
    }
}


@Composable
fun MainSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "ChanHyeok Park",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Text(
            text = "Android Developer",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun InfoSection(modifier: Modifier = Modifier) {
    val phone = Icons.Filled.Phone
    val share = Icons.Filled.Share
    val mail = Icons.Filled.MailOutline
    Column(
        modifier = modifier
    ) {
        InfoLine(
            phone, "+11 (123) 444 555 666",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        InfoLine(
            share, "@chanhypark",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        InfoLine(
            mail, "chanhypark@velog.io",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun InfoLine(icons: ImageVector, content: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        Icon(imageVector = icons, contentDescription = null, modifier.padding(end = 16.dp))
        Text(text = content)
    }
}

@Preview(showBackground = true)
@Composable
fun ContainerPreview() {
    ComposeCodelabTheme {
        Container()
    }
}

@Preview(showBackground = true)
@Composable
fun MainSectionPreview() {
    ComposeCodelabTheme {
        MainSection()
    }
}

@Preview(showBackground = true)
@Composable
fun InfoSectionPreview() {
    ComposeCodelabTheme {
        InfoSection()
    }
}