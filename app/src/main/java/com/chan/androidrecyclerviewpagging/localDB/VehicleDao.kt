package com.chan.androidrecyclerviewpagging.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chan.androidrecyclerviewpagging.model.Vehicles


/**
 * Created by Chandrabhan Haribhau Aher on 17-03-2023.
 * chandrabhan99@gmail.com
 */
@Dao
interface VehicleDao {

    @Query("SELECT * FROM VEHICLES")
    fun getAllVehicle(): LiveData<List<Vehicles>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVehicles(vehicles: List<Vehicles>)
}