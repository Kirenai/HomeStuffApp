package me.kire.re.homestuffapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Nourishment(
    val title: String,
    val stock: Int,
    val imageUrl: String,
    val expirationDate: String? = null,
) : Parcelable
