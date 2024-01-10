package com.example.composecodelab

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.composecodelab.ui.theme.ComposeCodelabTheme
import org.junit.Rule
import org.junit.Test
import java.lang.NumberFormatException
import java.text.NumberFormat

/**
 * @author Created by chanhypark on 1/10/24
 **/
class TipUiTests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            ComposeCodelabTheme {
                CalculationContainer()
            }
        }
        composeTestRule.onNodeWithText("Bill Amount").performTextInput("10")
        composeTestRule.onNodeWithText("Tip Percent").performTextInput("20")
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip Amount:$expectedTip")
            .assertExists("No node with this text was found.")
    }
}