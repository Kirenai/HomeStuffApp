package me.kire.re.homestuffapp.presentation.nourishment.form

data class NourishmentFormState(
    val name: String = "",
    val description: String = "",
    val imageUrl: String? = "",
    val nourishmentType: String = "UNIT",
    val unit: String = "",
    val percentage: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false,
)
