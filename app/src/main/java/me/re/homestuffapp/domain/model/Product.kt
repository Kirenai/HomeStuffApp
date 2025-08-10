package me.re.homestuffapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import me.re.homestuffapp.domain.model.enums.UnitType

@Parcelize
data class Product(
    val productId: Long? = null,
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

    fun concatenateAmountAndUnit(): String {
        return if (amountPerUnit != null) {
            when (unit) {
                UnitType.PIECE -> "$amountPerUnit piece${if (amountPerUnit > 1) "s" else ""}"
                UnitType.KILOGRAM -> "$amountPerUnit kg"
                UnitType.LITER -> "$amountPerUnit l"
                UnitType.MILLILITER -> "$amountPerUnit ml"
                UnitType.BOTTLE -> "$amountPerUnit bottle${if (amountPerUnit > 1) "s" else ""}"
                UnitType.CAN -> "$amountPerUnit can${if (amountPerUnit > 1) "s" else ""}"
                UnitType.UNIT -> "${amountPerUnit.toInt()} unit${if (amountPerUnit > 1) "s" else ""}"
                UnitType.GRAM -> TODO()
                UnitType.BOX -> TODO()
                UnitType.PACKET -> TODO()
                UnitType.JAR -> TODO()
                UnitType.CUP -> TODO()
                UnitType.TABLESPOON -> TODO()
                UnitType.TEASPOON -> TODO()
            }
        } else {
            ""
        }
    }
}
