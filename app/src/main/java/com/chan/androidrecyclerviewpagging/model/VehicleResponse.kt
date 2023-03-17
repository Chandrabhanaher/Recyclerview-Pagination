package com.chan.androidrecyclerviewpagging.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.google.gson.annotations.SerializedName


/**
 * Created by Chandrabhan Haribhau Aher on 17-03-2023.
 * chandrabhan99@gmail.com
 */
data class VehicleResponse(
    @SerializedName("count")
    var count: Int,
    @SerializedName("result")
    var result: List<Vehicles>
)
data class VehicleCount(
    var count: Int
)


