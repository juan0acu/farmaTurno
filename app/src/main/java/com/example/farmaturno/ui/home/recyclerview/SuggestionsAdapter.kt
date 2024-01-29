package com.example.farmaturno.ui.home.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.farmaturno.R
import com.example.farmaturno.databinding.FragmentHomePrincipalBinding
import com.google.android.libraries.places.api.model.AutocompletePrediction

class SuggestionsAdapter(
    private var suggestions: List<AutocompletePrediction>,
    var binding: FragmentHomePrincipalBinding
) : RecyclerView.Adapter<SuggestionsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val suggestionText: TextView = itemView.findViewById(R.id.suggestionText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_suggestion, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val suggestion = suggestions[position]
        holder.suggestionText.text = suggestion.getFullText(null)

        // Manejar la interacción con la sugerencia aquí, por ejemplo, al hacer clic en una sugerencia.
        holder.itemView.setOnClickListener {
            // Acción cuando se hace clic en una sugerencia
            // Puedes enviar la sugerencia seleccionada de vuelta al SearchView, por ejemplo.

            val fullText = suggestion.getFullText(null)
            // Establecer el texto de la sugerencia en el searchView
            binding.searchView.setQuery(fullText, false)
            binding.suggestionsRecyclerView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return suggestions.size
    }

    fun updateData(newSuggestions: List<AutocompletePrediction>?) {
        suggestions = newSuggestions ?: emptyList()
        notifyDataSetChanged()
    }
}
