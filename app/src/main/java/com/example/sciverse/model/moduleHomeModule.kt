package com.example.sciverse.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class moduleHomeModule(
    var theViewType: Int,
    @StringRes val stringResourceId1: Int,
    @DrawableRes val imageResourceId1: Int,
    @StringRes val stringResourceId2: Int,
    @DrawableRes val imageResourceId2: Int,
    @StringRes val stringResourceId3: Int,
    @DrawableRes val imageResourceId3: Int,
    @StringRes val stringResourceId4: Int,
    @DrawableRes val imageResourceId4: Int
)