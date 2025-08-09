package me.re.homestuffapp.data.remote.dto

import me.re.homestuffapp.domain.model.NourishmentType

data class Type(
    val nourishmentType: String,
    val unit: Int?,
    val percentage: Double?,
) {
    fun toNourishmentType(): NourishmentType {
        return NourishmentType(
            nourishmentType = this.nourishmentType,
            unit = this.unit,
            percentage = this.percentage
        )
    }
}