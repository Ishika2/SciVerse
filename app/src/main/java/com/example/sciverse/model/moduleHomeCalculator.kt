package com.example.sciverse.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class moduleHomeCalculator(
    var theViewType: Int,
    @StringRes val stringResourceId1: Int,
    @StringRes val summary1: Int,
    @StringRes val stringResourceId2: Int,
    @StringRes val summary2: Int)