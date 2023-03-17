package com.chan.androidrecyclerviewpagging.view_model

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chan.androidrecyclerviewpagging.localDB.CountData
import com.chan.androidrecyclerviewpagging.localDB.LocalRoomDatabase

import com.chan.androidrecyclerviewpagging.model.VehicleResponse
import com.chan.androidrecyclerviewpagging.model.Vehicles
import com.chan.androidrecyclerviewpagging.repository.MainRepository
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Chandrabhan Haribhau Aher on 16-03-2023.
 * chandrabhan99@gmail.com
 */
class MainViewModel(application: Application, private val repository: MainRepository): ViewModel() {

    private val vehicleDao = LocalRoomDatabase.getRoomInstance(application).vehicleDao()
    private val countDao = LocalRoomDatabase.getRoomInstance(application).vehicleCountDao()

    private var localVehicleList: LiveData<List<Vehicles>>?= null
    private var localCount: LiveData<CountData>?= null

    val errorMessage = MutableLiveData<String>()

    fun getAllVehicle():LiveData<List<Vehicles>>{
        localVehicleList = vehicleDao.getAllVehicle()
        return localVehicleList as LiveData<List<Vehicles>>
    }

    fun getAllVCount(): LiveData<CountData>{
        localCount = countDao.getAllCount()
        return localCount as LiveData<CountData>
    }

    fun getVehicles(page: Int){
        repository.getAllVeVehicles(page)
            .enqueue(object : Callback<VehicleResponse>{
                override fun onResponse(call: Call<VehicleResponse>, response: Response<VehicleResponse>) {
                    getVCount()
                    response.body()?.let {
                        vehicleDao.insertVehicles(it.result)
                        Log.e("VIEW_MODEL", "${it.result.size}")
                    }
                }
                override fun onFailure(call: Call<VehicleResponse>, t: Throwable) {
                    Log.d("VIEW_MODEL", t.message.toString())
                    errorMessage.postValue(t.message)
                }
            })
    }

    @SuppressLint("CheckResult")
    fun getVCount() {
        repository.getVCount().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                response?.let {
                    countDao.insertCount(CountData(1, "vehicle count", it.count))
                }
            },{ t -> Log.e("VIEW_MODEL", "api error ${t.message}") })
    }

}