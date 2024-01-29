package com.example.farmaturno.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.farmaturno.data.local.database.entity.ActualizacionTablas

@Dao
interface ActualizacionDao {
    @Query("SELECT * FROM actualizaciónTablas WHERE id = 1")
    fun getActualizar():ActualizacionTablas

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun updateActualización(actualizacionTablas: ActualizacionTablas)
}