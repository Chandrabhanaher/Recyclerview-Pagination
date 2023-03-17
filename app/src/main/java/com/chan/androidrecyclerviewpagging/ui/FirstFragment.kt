package com.chan.androidrecyclerviewpagging.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chan.androidrecyclerviewpagging.adapter.CarAdapter
import com.chan.androidrecyclerviewpagging.api_module.NetworkModule
import com.chan.androidrecyclerviewpagging.databinding.FragmentFirstBinding
import com.chan.androidrecyclerviewpagging.repository.MainRepository
import com.chan.androidrecyclerviewpagging.view_model.AppViewModelFactory
import com.chan.androidrecyclerviewpagging.view_model.MainViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var viewModel: MainViewModel
    lateinit var vehicleAdapter: CarAdapter
     var pageNum = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, AppViewModelFactory(MainRepository(NetworkModule), requireActivity().application))[MainViewModel::class.java]
        vehicleAdapter = CarAdapter()
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vehicleRecyclerView.setHasFixedSize(true)
        binding.vehicleRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.vehicleRecyclerView.adapter = vehicleAdapter
        binding.vehicleRecyclerView.isNestedScrollingEnabled = false


        viewModel.getAllVCount().observe(viewLifecycleOwner){

            it?.let {
                binding.txtCount.text = "Total Count : ${it.count}"
            }

        }
        viewModel.getVCount()

        viewModel.getAllVehicle().observe(viewLifecycleOwner) {
            binding.progressBarLoading.visibility = View.GONE
            vehicleAdapter.setVehicles(it)
            Log.d("CAR_LIST", "Vehicle List: ${it.size}")
        }
        viewModel.errorMessage.observe(viewLifecycleOwner){
            binding.progressBarLoading.visibility = View.GONE
            Log.d("CAR_LIST", "Error Message: $it")
        }
        viewModel.getVehicles(pageNum)

        binding.nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                pageNum++
                Log.e("CAR_LIST", "$pageNum")
                binding.progressBarLoading.visibility = View.VISIBLE
                viewModel.getVehicles(pageNum)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}