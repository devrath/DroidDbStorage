package com.istudio.code.domain.database.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class Genre(
    val id: String = UUID.randomUUID().toString(),
    val name: String
) : Parcelable