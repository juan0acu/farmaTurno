package com.example.farmaturno.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.farmaturno.databinding.FragmentHomePrincipalBinding
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse
import com.example.farmaturno.ui.home.recyclerview.FarmaAdapter
import com.example.farmaturno.ui.home.recyclerview.SuggestionsAdapter
import com.example.farmaturno.ui.home.states.HomeState
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomePrincipalFragment : Fragment() {

    lateinit var binding: FragmentHomePrincipalBinding
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
       // init_observables()
        setupRecyclerView()
        //listar_recycler()
       // init_listener()
        initUIState()
        viewModel.getFarmasTurnos()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[HomePrincipalViewModel::class.java]
    }

    /*private fun listar_recycler(){
        binding.progressBar.isVisible = true
        viewModel.initialLocalTurnos()
    }*/

    private fun setupRecyclerView(){
        adapter = FarmaAdapter(emptyList())
        binding.rvFarma.adapter = adapter
        binding.rvFarma.layoutManager = LinearLayoutManager(requireContext())

        //Sugerencias
        val suggestionsAdapter = SuggestionsAdapter(emptyList(),binding)
        // Obtener una referencia al RecyclerView
        val suggestionsRecyclerView: RecyclerView = binding.suggestionsRecyclerView
        // Configurar el adaptador del RecyclerView
        binding.suggestionsRecyclerView.adapter = suggestionsAdapter
        suggestionsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    /*private fun init_observables(){
        viewModel.farmaciasLiveData.observe(viewLifecycleOwner) { farmacias ->
            binding.progressBar.isVisible = false
            adapter.listadoFarmacias = farmacias
            adapter.notifyDataSetChanged()
        }

        viewModel.suggestionsLiveData.observe(viewLifecycleOwner) { suggestions ->
            // Actualizar el adaptador del RecyclerView con las nuevas sugerencias
            val suggestionsAdapter = binding.suggestionsRecyclerView.adapter as SuggestionsAdapter
            suggestionsAdapter.updateData(suggestions)
        }
    }
*/
    /*private fun init_listener(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    // Actualizar la localidad buscada en el ViewModel
                    viewModel.setSearchedLocalidad(query)
                    return true
                }
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
                                    binding.suggestionsRecyclerView.visibility = View.VISIBLE
                                }
                            } else {
                                val exception: ApiException? =
                                    autocompletePredictions.exception as ApiException?
                                exception?.printStackTrace()
                            }
                        }
                }else {
                    // Ocultar el RecyclerView si no hay texto en el SearchView
                    binding.suggestionsRecyclerView.visibility = View.GONE
                }
                return true
            }
        })
    }*/




    private fun initUIState() {
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect {
                    when(it){
                        HomeState.Loading -> loadingState()
                        is HomeState.Error -> errorState()
                        is HomeState.Success -> successState(it)
                    }
                }
            }
        }
    }

    private fun successState(state: HomeState.Success) {
        binding.progressBar.isVisible = false
        adapter.listadoFarmacias = state.listFarma!!
        adapter.notifyDataSetChanged()
    }

    private fun loadingState() {
        binding.progressBar.isVisible = true
    }

    private fun errorState() {
        binding.progressBar.isVisible = false
    }

}