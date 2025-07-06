package me.kire.re.homestuffapp.presentation.shopping

import me.kire.re.homestuffapp.domain.model.Shopping

sealed class ShoppingEvent {
    data class AddItem(val item: Shopping) : ShoppingEvent()
    data class EditItem(val item: Shopping) : ShoppingEvent()
    data class DeleteItem(val item: Shopping) : ShoppingEvent()
    data object ClearItems : ShoppingEvent()
    data object MarkAsPurchased : ShoppingEvent()
}