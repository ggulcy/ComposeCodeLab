package com.example.composecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
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
    var tipInput by remember { mutableStateOf("") }
    var roundTip by remember { mutableStateOf(false) }

    var tip = calculateTip(
        amount = amountInput.toDoubleOrNull(),
        tipPercent = tipInput.toDoubleOrNull(),
        roundTip
    )

    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = 40.dp)
                .verticalScroll(rememberScrollState())
                .safeDrawingPadding()
                .align(Alignment.CenterStart)
                .fillMaxWidth()
        ) {
            Text(
                text = "Calculate Title"
            )
            InputField(
                inputValue = amountInput,
                onValueChange = {
                    amountInput = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                icon = Icons.Filled.List,
                label = "Bill Amount"
            )
            InputField(
                inputValue = tipInput,
                onValueChange = {
                    tipInput = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                icon = Icons.Filled.Done,
                label = "Tip Percent"
            )
            RoundSwitch(roundTip, onCheckedChanged = {
                roundTip = it
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

@Composable
fun RoundSwitch(
    flag: Boolean,
    onCheckedChanged: (Boolean) -> Unit
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Calculate Tip",
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Switch(
            checked = flag,
            onCheckedChange = onCheckedChanged
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    inputValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions,
    icon: ImageVector,
    label: String
) {
    TextField(
        value = inputValue,
        onValueChange = onValueChange,
        modifier = modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .fillMaxWidth(),
        keyboardOptions = keyboardOptions,
        singleLine = true,
        label = {
            Row {
                Icon(
                    imageVector = icon, contentDescription = null,
                    modifier.padding(end = 8.dp)
                )
                Text(text = label)
            }
        }
    )
}

@VisibleForTesting
internal fun calculateTip(amount: Double?, tipPercent: Double?, isRoundTip: Boolean): String {
    amount?.let { amount ->
        tipPercent?.let { tipPercent ->
            var tip = tipPercent / 100 * amount
            if (isRoundTip) tip = kotlin.math.ceil(tip)
            return NumberFormat.getCurrencyInstance().format(tip)
        }
    }
    return ""
}

@Preview(showBackground = true)
@Composable
fun CalculationContainerPreview() {
    ComposeCodelabTheme {
        CalculationContainer()
    }
}
