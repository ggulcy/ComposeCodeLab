package com.example.composecodelab.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * @author Created by chanhypark on 1/11/24
 **/

data class Affirmation(
    @StringRes val stringRes: Int,
    @DrawableRes val imageRes: Int
)