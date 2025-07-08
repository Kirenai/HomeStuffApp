package me.kire.re.homestuffapp.presentation.nourishment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import me.kire.re.homestuffapp.domain.model.Nourishment
import me.kire.re.homestuffapp.domain.usecases.GetNourishments
import javax.inject.Inject

@HiltViewModel
class NourishmentViewModel @Inject constructor(
    private val getNourishments: GetNourishments
) : ViewModel() {


    val nourishments: Flow<PagingData<Nourishment>> = getNourishments()
        .cachedIn(viewModelScope)

    fun onEvent() {
        // Handle events here
    }
}