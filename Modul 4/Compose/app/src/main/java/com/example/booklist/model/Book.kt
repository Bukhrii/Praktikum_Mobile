package com.example.booklist.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Book(
    @StringRes val titleResourceId: Int,
    @StringRes val yearResourceId: Int,
    @StringRes val aboutResourceId: Int,
    @StringRes val webUrlResourceId: Int,
    @DrawableRes val imageResourceId: Int
)
