package com.example.sciverse.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class allmodules (
    @StringRes val stringResourceId: Int,
    @StringRes val summary: Int,
    @DrawableRes val imageResourceId: Int,
    var isExpandable: Boolean = false
)