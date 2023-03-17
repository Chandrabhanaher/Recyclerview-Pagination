package com.chan.androidrecyclerviewpagging.api_module

import com.chan.androidrecyclerviewpagging.model.VehicleCount
import com.chan.androidrecyclerviewpagging.model.VehicleResponse
import com.chan.androidrecyclerviewpagging.model.Vehicles
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


/**
 * Created by Chandrabhan Haribhau Aher on 16-03-2023.
 * chandrabhan99@gmail.com
 */
interface ApiInterface {

    @GET("getAllVehicles.php")
    fun getAllVehicles(@Query("page") page: Int): Call<VehicleResponse>

    @GET("vehicleCount.php")
    fun getVehicleCount(): Observable<VehicleCount>


}