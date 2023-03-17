package com.chan.androidrecyclerviewpagging.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chan.androidrecyclerviewpagging.repository.MainRepository


/**
 * Created by Chandrabhan Haribhau Aher on 16-03-2023.
 * chandrabhan99@gmail.com
 */
@Suppress("UNCHECKED_CAST")
class AppViewModelFactory constructor(
    private val repository: MainRepository,
    private val application: Application) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

//      return multiple ViewModel class

        return when(modelClass){
            MainViewModel::class.java ->{
                MainViewModel(application,repository)
            }else ->{ throw  IllegalArgumentException("Unknown class $modelClass")}

        } as T
    }
}