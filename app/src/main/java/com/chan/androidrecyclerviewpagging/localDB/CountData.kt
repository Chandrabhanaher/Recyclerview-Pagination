package com.chan.androidrecyclerviewpagging.localDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Chandrabhan Haribhau Aher on 17-03-2023.
 * chandrabhan99@gmail.com
 */

@Entity
data class CountData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "count")
    var count: Int
)
