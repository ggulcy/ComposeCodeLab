package com.example.composecodelab

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.NumberFormat

/**
 * @author Created by chanhypark on 1/10/24
 **/
class TestTipCalculatorTest {
    @Test
    fun calculateTip_20PercentNoRoundup() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount, tipPercent, false)
        assertEquals(expectedTip, actualTip)
    }
}