package com.chan.androidrecyclerviewpagging.localDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.chan.androidrecyclerviewpagging.model.Vehicles


/**
 * Created by Chandrabhan Haribhau Aher on 17-03-2023.
 * chandrabhan99@gmail.com
 */
@Database(entities = [Vehicles::class, CountData::class], version = 1, exportSchema = false)
abstract class LocalRoomDatabase : RoomDatabase() {

    abstract fun vehicleDao(): VehicleDao
    abstract fun vehicleCountDao(): CountDataDao

    companion object {

        @Volatile
        private var INSTANCE: LocalRoomDatabase? = null

        fun getRoomInstance(context: Context): LocalRoomDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    LocalRoomDatabase::class.java,
                    "vehicleDB")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance

            }
        }
    }
}