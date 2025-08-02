package me.kire.re.homestuffapp.data.dao.converter

import androidx.room.TypeConverter
import me.kire.re.homestuffapp.domain.model.enums.UnitType

class UnitTypeConvertor {
    @TypeConverter
    fun fromUnitType(value: UnitType): String = value.name

    @TypeConverter
    fun toUnitType(value: String): UnitType = UnitType.valueOf(value = value)
}