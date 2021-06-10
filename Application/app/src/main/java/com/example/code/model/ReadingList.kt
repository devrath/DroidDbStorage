package com.example.code.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity
class ReadingList(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String
//    val bookIds: List<String>
) : Parcelable