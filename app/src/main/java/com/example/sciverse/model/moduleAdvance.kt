package com.example.sciverse.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class moduleAdvance (
    @StringRes val stringResourceId: Int,
    @StringRes val summaryResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    var isExpandable: Boolean = false
    )