package com.example.farmaturno.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.farmaturno.data.local.database.dao.ActualizacionDao
import com.example.farmaturno.data.local.database.dao.FarmaciaDao
import com.example.farmaturno.data.local.database.entity.ActualizacionTablas
import com.example.farmaturno.data.local.database.entity.FarmaciasTurno

@Database(entities = [FarmaciasTurno::class, ActualizacionTablas::class], version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun farmaciaDao(): FarmaciaDao
    abstract fun actualizacionDao(): ActualizacionDao

    companion object {
        // Define la migración
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Cambia el tipo de la columna fechaActualizacion de INTEGER a TEXT
                database.execSQL("CREATE TABLE IF NOT EXISTS `actualizaciónTablas_new` " +
                        "(`id` INTEGER PRIMARY KEY NOT NULL, `fechaActualizacion` TEXT NOT NULL)")

                database.execSQL("INSERT INTO actualizaciónTablas_new (id, fechaActualizacion) SELECT id, fechaActualizacion FROM actualizaciónTablas")

                database.execSQL("DROP TABLE actualizaciónTablas")

                database.execSQL("ALTER TABLE actualizaciónTablas_new RENAME TO actualizaciónTablas")
            }
        }
    }
}