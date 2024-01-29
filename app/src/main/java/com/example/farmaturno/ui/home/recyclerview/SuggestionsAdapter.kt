package com.example.farmaturno.ui.home.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.farmaturno.R
import com.example.farmaturno.databinding.FragmentHomePrincipalBinding
import com.google.android.libraries.places.api.model.AutocompletePrediction

class SuggestionsAdapter(
    private var suggestions: List<String>,
    var binding: FragmentHomePrincipalBinding
) : RecyclerView.Adapter<SuggestionsAdapter.ViewHolder>() {

    var suggestionsSelected = false

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val suggestionText: TextView = itemView.findViewById(R.id.suggestionText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_suggestion, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val suggestion = suggestions[position]
        holder.suggestionText.text = suggestion

        holder.itemView.setOnClickListener {
            suggestionsSelected = true
            binding.searchView.setQuery(suggestion, false)
            binding.suggestionsRecyclerView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return suggestions.size
    }

    fun updateData(newSuggestions: List<String>?) {
        suggestions = newSuggestions ?: emptyList()
        notifyDataSetChanged()
    }

    fun resetSuggestionSelection(){
        suggestionsSelected = false
    }
}
