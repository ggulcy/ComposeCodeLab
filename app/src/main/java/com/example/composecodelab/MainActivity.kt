package com.example.composecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    MainContainer()
                }
            }
        }
    }
}

@Composable
fun MainContainer(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            //1,2분할 영역
            SectionText(
                title = stringResource(id = R.string.quad1_title),
                content = stringResource(id = R.string.quad1_content),
                color = colorResource(id = R.color.quad1),
                modifier = Modifier.weight(1f)
            )
            SectionText(
                title = stringResource(id = R.string.quad2_title),
                content = stringResource(id = R.string.quad2_content),
                color = colorResource(id = R.color.quad2),
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            //3,4분할 영역
            SectionText(
                title = stringResource(id = R.string.quad3_title),
                content = stringResource(id = R.string.quad3_content),
                color = colorResource(id = R.color.quad3),
                modifier = Modifier.weight(3f)
            )
            SectionText(
                title = stringResource(id = R.string.quad4_title),
                content = stringResource(id = R.string.quad4_content),
                color = colorResource(id = R.color.quad4),
                modifier = Modifier.weight(3f)
            )
        }
    }
}

@Composable
fun SectionText(title: String, content: String, color: Color, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(color)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Justify
        )
        Text(
            text = content,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Justify
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MainContainerPreview() {
    ComposeCodelabTheme {
        MainContainer()
    }
}

@Preview(showBackground = true)
@Composable
fun SectionPreview() {
    ComposeCodelabTheme {
        SectionText(
            title = "제목",
            content = "내용 내용 내용 내용 내용 내용 내용 내용 ",
            color = colorResource(id = R.color.quad1)
        )
    }
}
