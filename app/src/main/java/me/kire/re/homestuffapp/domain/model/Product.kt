package me.kire.re.homestuffapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val productId: String,
    val name: String,
    val title: String? = null,
    val stock: Int? = null,
    val imageUrl: String,
    val description: String,
    val isAvailable: Boolean,
    val expirationDate: String? = null,
    val type: NourishmentType? = null,
) : Parcelable
