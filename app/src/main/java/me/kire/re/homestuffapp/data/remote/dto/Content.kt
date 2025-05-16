package me.kire.re.homestuffapp.data.remote.dto

import me.kire.re.homestuffapp.domain.model.Nourishment

data class Content(
    val nourishmentId: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val isAvailable: Boolean,
    val type: Type,
) {
    fun toNourishment(): Nourishment {
        return Nourishment(
            nourishmentId = this.nourishmentId,
            name = this.name,
            imageUrl = this.imageUrl,
            description = this.description,
            isAvailable = this.isAvailable,
            type = this.type.toNourishmentType(),
        )
    }
}
