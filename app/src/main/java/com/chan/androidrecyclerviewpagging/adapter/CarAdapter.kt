package com.chan.androidrecyclerviewpagging.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.chan.androidrecyclerviewpagging.databinding.ItemViewBinding
import com.chan.androidrecyclerviewpagging.model.Vehicles
import java.text.NumberFormat
import java.util.*


/**
 * Created by Chandrabhan Haribhau Aher on 16-03-2023.
 * chandrabhan99@gmail.com
 */
class CarAdapter():  RecyclerView.Adapter<CarViewHolder>(){

    private var vehicles = mutableListOf<Vehicles>()

    @SuppressLint("NotifyDataSetChanged")
    fun setVehicles(vehicles: List<Vehicles>){
        this.vehicles = vehicles.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflater  = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(inflater, parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val vehicle = vehicles[position]
        holder.binding.txtName.text = vehicle.name
        holder.binding.txtBrand.text = vehicle.brand
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
        currencyFormat.maximumFractionDigits = 0
        currencyFormat.currency = Currency.getInstance("INR")

        holder.binding.txtPrice.text = "${currencyFormat.format(vehicle.price)}"
    }

    override fun getItemCount(): Int {
        return vehicles.size
    }
}

class CarViewHolder(val binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root){}