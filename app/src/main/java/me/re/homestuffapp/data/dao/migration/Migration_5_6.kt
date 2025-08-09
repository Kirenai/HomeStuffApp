package me.re.homestuffapp.data.dao.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_5_6 = object : Migration(startVersion = 5, endVersion = 6) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(sql = "ALTER TABLE products ADD COLUMN isAvailable INTEGER NOT NULL DEFAULT 1")
        db.execSQL(sql = "ALTER TABLE products ADD COLUMN amountPerUnit REAL NOT NULL DEFAULT 1.0")
        db.execSQL(sql = "ALTER TABLE products ADD COLUMN unit TEXT NOT NULL DEFAULT 'UNIT'")
    }
}