package com.example.composecodelab

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecodelab.ui.theme.ComposeCodelabTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    CalculationContainer()
                }
            }
        }
    }
}

@Composable
fun CalculationContainer(modifier: Modifier = Modifier) {
    var amountInput by remember { mutableStateOf("") }
    val tip = calculateTip(amountInput.toDoubleOrNull() ?: 0.0)
    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = 40.dp)
                .verticalScroll(rememberScrollState())
                .safeDrawingPadding()
                .align(Alignment.CenterStart)
        ) {
            Text(
                text = "Calculate Title"
            )
            EditNumberField(amountInput, {
                amountInput = it
            })
            Text(
                text = "Tip Amount:$tip",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    amountInput: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = amountInput,
        onValueChange = onValueChange,
        modifier = modifier.padding(top = 8.dp, bottom = 8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        label = { Text(text = "Bill Amount") }
    )
}

fun calculateTip(amount: Double, tipPercent: Double = 15.0): String {
    return NumberFormat.getCurrencyInstance().format(tipPercent / 100 * amount)
}

@Preview(showBackground = true)
@Composable
fun CalculationContainerPreview() {
    ComposeCodelabTheme {
        CalculationContainer()
    }
}
