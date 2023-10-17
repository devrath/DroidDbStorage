package com.istudio.code.domain.database.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class ReadingList(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val bookIds: List<String>
) : Parcelable