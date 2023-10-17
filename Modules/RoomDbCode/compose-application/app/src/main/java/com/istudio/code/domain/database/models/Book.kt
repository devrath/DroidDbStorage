package com.istudio.code.domain.database.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class Book(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String,
    val genreId: String
) : Parcelable