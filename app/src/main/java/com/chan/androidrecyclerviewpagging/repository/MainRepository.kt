package com.chan.androidrecyclerviewpagging.repository

import com.chan.androidrecyclerviewpagging.api_module.NetworkModule


/**
 * Created by Chandrabhan Haribhau Aher on 16-03-2023.
 * chandrabhan99@gmail.com
 */
class MainRepository constructor(private val retrofitService: NetworkModule) {


    fun getAllVeVehicles(page: Int) = retrofitService.getClient.getAllVehicles(page)

    fun getVCount() = retrofitService.getClient.getVehicleCount()


}