package com.example.composecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                    GreetingText(message = "HappyBirthDay Sam", from = "from Code Pilot")
                }
            }
        }
    }
}


@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column (
        //GreetingText Element를 vertical을 Center로 정해주고 (부모 세로기준 가운데 위치하도록)
        verticalArrangement = Arrangement.Center,
        //GreetingText와 parent view간의 padding간격을 설정해준다
        modifier = modifier.padding(40.dp)
    ){
        Text(
            text = message,
            fontSize = 50.sp,
            lineHeight = 50.sp,
            //텍스트 Element의 정렬을 Center로 맞춰준다
            textAlign = TextAlign.Center
        )
        Text(
            text = from,
            fontSize = 20.sp,
            lineHeight = 20.sp,
            //padding과 End정렬 적용
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.End)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BirthDayCardPreview() {
    ComposeCodelabTheme {
        GreetingText(message = "Happy BirthDay", "From CodePilot")
    }
}