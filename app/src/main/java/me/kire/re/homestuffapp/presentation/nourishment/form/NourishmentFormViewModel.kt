package me.kire.re.homestuffapp.presentation.nourishment.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.kire.re.homestuffapp.presentation.nourishment.form.NourishmentFormEvent.DescriptionChanged
import me.kire.re.homestuffapp.presentation.nourishment.form.NourishmentFormEvent.NameChanged
import me.kire.re.homestuffapp.presentation.nourishment.form.NourishmentFormEvent.NourishmentTypeChanged
import me.kire.re.homestuffapp.presentation.nourishment.form.NourishmentFormEvent.SaveNourishment
import me.kire.re.homestuffapp.util.toCreateNourishmentRequest
import javax.inject.Inject

@HiltViewModel
class NourishmentFormViewModel @Inject constructor(
    private val saveNourishment: me.kire.re.homestuffapp.domain.usecases.SaveNourishment
) : ViewModel() {

    private val _state: MutableStateFlow<NourishmentFormState> =
        MutableStateFlow(NourishmentFormState())
    val state: MutableStateFlow<NourishmentFormState> = _state

    fun onEvent(event: NourishmentFormEvent) {
        when (event) {
            is SaveNourishment -> saveNourishment()
            is NameChanged -> updateName(event.name)
            is DescriptionChanged -> updateDescription(event.description)
            is NourishmentTypeChanged -> updateNourishmentType(event.nourishmentType, event.value)
        }
    }

    private fun updateName(name: String) {
        _state.update { it.copy(name = name) }
    }

    private fun updateDescription(description: String) {
        _state.update { it.copy(description = description) }
    }

    private fun updateNourishmentType(nourishmentType: String, value: String) {
        _state.update {
            it.copy(
                nourishmentType = nourishmentType,
                unit = if (nourishmentType == "UNIT") value else it.unit,
                percentage = if (nourishmentType == "PERCENTAGE") value else it.percentage
            )
        }
    }

    private fun saveNourishment() {
        val request = toCreateNourishmentRequest(state.value)
        viewModelScope.launch {
            saveNourishment.invoke(request)
            println(_state.value)
            println("Nourishment saved: ${_state.value.name}, ${_state.value.description}")
        }
    }
}