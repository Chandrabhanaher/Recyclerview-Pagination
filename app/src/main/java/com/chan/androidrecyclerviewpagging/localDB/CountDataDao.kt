package com.chan.androidrecyclerviewpagging.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


/**
 * Created by Chandrabhan Haribhau Aher on 17-03-2023.
 * chandrabhan99@gmail.com
 */
@Dao
interface CountDataDao {

    @Query("SELECT * FROM countdata")
    fun getAllCount(): LiveData<CountData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCount(vehicles: CountData)
}