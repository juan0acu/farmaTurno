package com.example.farmaturno.ui.home.recyclerview

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.farmaturno.data.network.response.FarmaDataResponse
import com.example.farmaturno.databinding.ItemListFarmBinding
import com.example.farmaturno.domain.model.FarmaTurnoModel

class FarmViewHolder (view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemListFarmBinding.bind(view)

    fun bind(listadoFarmacias: FarmaTurnoModel){
        binding.txtNameFarm.text = listadoFarmacias.farmacia_name
        binding.txtLocalDirection.text = listadoFarmacias.farmacia_direccion
        binding.timeOpenFarm.text = listadoFarmacias.farmacia_apertura
        binding.timeCloseFarm.text = listadoFarmacias.farmacia_cierre
        binding.btLlamar.setOnClickListener {
            listadoFarmacias.farmacia_telefono?.let { number ->
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:$number")
                it.context.startActivity(intent)
            }
        }
    }
}