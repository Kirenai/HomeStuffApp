package me.kire.re.homestuffapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NourishmentType(
    val nourishmentType: String,
    val unit: Int?,
    val percentage: Double?,
): Parcelable
