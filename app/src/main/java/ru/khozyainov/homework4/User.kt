package ru.khozyainov.homework4

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val photoUrl: String
) : Parcelable
