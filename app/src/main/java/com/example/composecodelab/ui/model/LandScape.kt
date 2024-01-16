package com.example.composecodelab.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * @author Created by chanhypark on 1/15/24
 **/
data class LandScape(
    @DrawableRes val imgRes: Int,
    val title: String,
    @StringRes val description: Int
)