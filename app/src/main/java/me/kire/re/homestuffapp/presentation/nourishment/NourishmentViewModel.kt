package me.kire.re.homestuffapp.presentation.nourishment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import me.kire.re.homestuffapp.domain.usecases.GetNourishments
import javax.inject.Inject

@HiltViewModel
class NourishmentViewModel @Inject constructor(
    private val getNourishments: GetNourishments
) : ViewModel() {


    val nourishments = getNourishments()
        .cachedIn(viewModelScope)

    fun onEvent() {
        // Handle events here
    }
}