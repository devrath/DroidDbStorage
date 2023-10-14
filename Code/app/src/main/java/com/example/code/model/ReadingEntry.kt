package com.example.code.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class ReadingEntry(
    val id: String = UUID.randomUUID().toString(),
    val comment: String,
    val dateOfEntry: Date
) : Parcelable