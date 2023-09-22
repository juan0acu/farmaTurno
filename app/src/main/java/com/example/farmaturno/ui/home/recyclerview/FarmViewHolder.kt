package com.example.farmaturno.ui.home.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.farmaturno.data.model.FarmaDataResponse
import com.example.farmaturno.databinding.ItemListFarmBinding

class FarmViewHolder (view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemListFarmBinding.bind(view)

    fun bind(listadoFarmacias: FarmaDataResponse){
        binding.txtNameFarm.text = listadoFarmacias.farmacia_name
        binding.txtLocalDirection.text = listadoFarmacias.farmacia_direccion
        binding.timeOpenFarm.text = listadoFarmacias.farmacia_apertura
        binding.timeCloseFarm.text = listadoFarmacias.farmacia_cierre
    }
}