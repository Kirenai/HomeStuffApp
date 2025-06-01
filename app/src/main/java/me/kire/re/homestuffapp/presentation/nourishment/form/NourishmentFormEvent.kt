package me.kire.re.homestuffapp.presentation.nourishment.form

sealed class NourishmentFormEvent {
    data object SaveNourishment : NourishmentFormEvent()
    data class NameChanged(val name: String) : NourishmentFormEvent()
    data class DescriptionChanged(val description: String) : NourishmentFormEvent()
}
