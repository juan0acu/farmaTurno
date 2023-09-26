package com.example.farmaturno.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmaturno.databinding.FragmentHomePrincipalBinding
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse
import com.example.farmaturno.ui.home.recyclerview.FarmaAdapter
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomePrincipalFragment : Fragment() {

    private lateinit var binding: FragmentHomePrincipalBinding
    private lateinit var viewModel: HomePrincipalViewModel
    private lateinit var adapter: FarmaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePrincipalBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        init_observables()
        setupRecyclerView()
        listar_recycler()
        init_listener()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[HomePrincipalViewModel::class.java]
    }

    private fun listar_recycler(){
        binding.progressBar.isVisible = true
        viewModel.initialLocalTurnos()
    }

    private fun setupRecyclerView(){
        adapter = FarmaAdapter(emptyList())
        binding.rvFarma.adapter = adapter
        binding.rvFarma.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun init_observables(){
        viewModel.farmaciasLiveData.observe(viewLifecycleOwner) { farmacias ->
            binding.progressBar.isVisible = false
            adapter.listadoFarmacias = farmacias
            adapter.notifyDataSetChanged()
        }
    }

    private fun init_listener(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    // Realizar una solicitud de autocompletado usando el ViewModel
                    viewModel.getAutocompletePredictions(newText)
                        .addOnCompleteListener { autocompletePredictions ->
                            if (autocompletePredictions.isSuccessful) {
                                val predictions: FindAutocompletePredictionsResponse? =
                                    autocompletePredictions.result
                                if (predictions != null) {
                                    // Aquí puedes mostrar las sugerencias en un ListView o RecyclerView
                                }
                            } else {
                                val exception: ApiException? =
                                    autocompletePredictions.exception as ApiException?
                                exception?.printStackTrace()
                            }
                        }
                }
                return true
            }
        })
    }

}