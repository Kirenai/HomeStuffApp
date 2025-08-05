package me.kire.re.homestuffapp.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import me.kire.re.homestuffapp.domain.usecases.purchase.GetLast15Prices
import me.kire.re.homestuffapp.domain.usecases.purchase.GetLastTwoPurchases
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    getLastTwoPurchases: GetLastTwoPurchases,
    getLast15Prices: GetLast15Prices,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val productId = savedStateHandle.get<String>("productId")?.toLongOrNull()

    val lastTwoPurchases = getLastTwoPurchases.invoke(productId = productId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val charData = getLast15Prices.invoke(productId = productId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

}