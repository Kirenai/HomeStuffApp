package me.kire.re.homestuffapp.data.dao.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_4_5 = object : Migration(startVersion = 4, endVersion = 5) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(sql = "ALTER TABLE products ADD COLUMN imageUrl TEXT DEFAULT NULL")
    }
}