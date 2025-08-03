package me.kire.re.homestuffapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import me.kire.re.homestuffapp.domain.model.enums.UnitType

@Parcelize
data class Product(
    val productId: String = "",
    val name: String,
//    val title: String? = null,
    val stock: Int? = null,
    val imageUrl: String? = null,
    val description: String,
    val isAvailable: Boolean = true,
    val expirationDate: String? = null,
    val amountPerUnit: Float? = null,
    val unit: UnitType = UnitType.UNIT,
    val categoryId: Long,
) : Parcelable {
    fun hasImageUrl(): Boolean {
        return !imageUrl.isNullOrEmpty()
    }
}
