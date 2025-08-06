package me.kire.re.homestuffapp.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import me.kire.re.homestuffapp.domain.usecases.purchase.GetLast15Prices
import me.kire.re.homestuffapp.domain.usecases.purchase.GetLastTwoPurchases
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale
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

    val months = charData
        .map { list ->
            list.map { data ->
                Month.of(data.month.toInt()).getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
            }.distinct().sorted()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

}