package com.example.sciverse.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class moduleCalculator(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)