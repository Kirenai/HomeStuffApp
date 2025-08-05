package me.kire.re.homestuffapp.presentation.shopping

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.kire.re.homestuffapp.domain.model.Shopping
import me.kire.re.homestuffapp.domain.usecases.purchase.InsertAllPurchases
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val insertAllPurchases: InsertAllPurchases
) : ViewModel() {
    var shoppingList by mutableStateOf(listOf<Shopping>())
        private set

    fun onEvent(event: ShoppingEvent) {
        when (event) {
            is ShoppingEvent.AddItem -> addItem(event.item)
            is ShoppingEvent.MarkAsPurchased -> {
                viewModelScope.launch {
                    markAsPurchased()
                }
            }

            is ShoppingEvent.ClearItems -> TODO()
            is ShoppingEvent.DeleteItem -> deleteItem(event.item)
            is ShoppingEvent.EditItem -> editItem(event.item)
        }
    }

    private suspend fun markAsPurchased() {
        val currentList = shoppingList
        insertAllPurchases(shoppingList = currentList)
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

    private fun editItem(item: Shopping) {
        shoppingList = shoppingList.map { if (it.shoppingId == item.shoppingId) item else it }
    }
}