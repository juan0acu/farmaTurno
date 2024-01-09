package com.example.farmaturno.ui.home.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.farmaturno.R
import com.example.farmaturno.data.network.response.FarmaDataResponse
import com.example.farmaturno.domain.model.FarmaTurnoModel

class FarmaAdapter (var listadoFarmacias: List<FarmaTurnoModel>):RecyclerView.Adapter<FarmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmViewHolder {
        return FarmViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_farm,parent,false)
        )
    }

    override fun getItemCount() = listadoFarmacias.size

    override fun onBindViewHolder(viewHolder: FarmViewHolder, position: Int) {
        viewHolder.bind(listadoFarmacias[position])
    }
}