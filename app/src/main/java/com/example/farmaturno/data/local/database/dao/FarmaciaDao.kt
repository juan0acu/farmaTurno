package com.example.farmaturno.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.farmaturno.data.local.database.entity.FarmaciasTurno

@Dao
interface FarmaciaDao {
    @Query ("Select * FROM farmaciasTurno")
    fun getAllFarmacias():List<FarmaciasTurno>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertAllFarmacias(farmaciasTurno: List<FarmaciasTurno>)

    @Query("DELETE FROM farmaciasTurno")
    fun deleteAll()
}