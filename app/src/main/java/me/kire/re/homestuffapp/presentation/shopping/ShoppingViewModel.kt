package me.kire.re.homestuffapp.presentation.shopping

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import me.kire.re.homestuffapp.domain.model.Shopping
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor() : ViewModel() {
    var shoppingList by mutableStateOf(listOf<Shopping>())
        private set

    fun onEvent(event: ShoppingEvent) {
        when (event) {
            is ShoppingEvent.AddItem -> addItem(event.item)
            is ShoppingEvent.MarkAsPurchased -> markAsPurchased()
            is ShoppingEvent.ClearItems -> TODO()
            is ShoppingEvent.DeleteItem -> deleteItem(event.item)
            is ShoppingEvent.EditItem -> TODO()
        }
    }

    private fun markAsPurchased() {
        shoppingList = emptyList()
    }

    private fun addItem(item: Shopping) {
        if (!shoppingList.contains(item)) {
            shoppingList = shoppingList + item
        }
    }

    private fun deleteItem(item: Shopping) {
        shoppingList = shoppingList.filter { it != item }
    }
}