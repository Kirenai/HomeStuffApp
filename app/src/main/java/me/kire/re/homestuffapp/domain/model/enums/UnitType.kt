package me.kire.re.homestuffapp.domain.model.enums

enum class UnitType {
    GRAM, KILOGRAM, LITER, MILLILITER, UNIT, PIECE, BOX, PACKET, BOTTLE, CAN, JAR, CUP, TABLESPOON, TEASPOON;
}

fun UnitType.toShortString(): String {
    return when (this) {
        UnitType.GRAM -> "g"
        UnitType.KILOGRAM -> "kg"
        UnitType.LITER -> "l"
        UnitType.MILLILITER -> "ml"
        UnitType.UNIT -> "unit"
        UnitType.PIECE -> "piece"
        UnitType.BOX -> "box"
        UnitType.PACKET -> "packet"
        UnitType.BOTTLE -> "bottle"
        UnitType.CAN -> "can"
        UnitType.JAR -> "jar"
        UnitType.CUP -> "cup"
        UnitType.TABLESPOON -> "tbsp"
        UnitType.TEASPOON -> "tsp"
    }
}