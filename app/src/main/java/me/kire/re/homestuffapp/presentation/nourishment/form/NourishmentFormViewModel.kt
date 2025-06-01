package me.kire.re.homestuffapp.presentation.nourishment.form

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import me.kire.re.homestuffapp.presentation.nourishment.form.NourishmentFormEvent.DescriptionChanged
import me.kire.re.homestuffapp.presentation.nourishment.form.NourishmentFormEvent.NameChanged
import me.kire.re.homestuffapp.presentation.nourishment.form.NourishmentFormEvent.SaveNourishment
import javax.inject.Inject

@HiltViewModel
class NourishmentFormViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableStateFlow<NourishmentFromState> =
        MutableStateFlow(NourishmentFromState())
    val state: MutableStateFlow<NourishmentFromState> = _state

    fun onEvent(event: NourishmentFormEvent) {
        when (event) {
            is SaveNourishment -> saveNourishment()
            is NameChanged -> updateName(event.name)
            is DescriptionChanged -> updateDescription(event.description)
        }
    }

    private fun updateName(name: String) {
        _state.update { it.copy(name = name) }
    }

    private fun updateDescription(description: String) {
        _state.update { it.copy(description = description) }
    }

    private fun saveNourishment() {
        // Logic to save nourishment would go here
        println("Nourishment saved: ${_state.value.name}, ${_state.value.description}")
    }
}