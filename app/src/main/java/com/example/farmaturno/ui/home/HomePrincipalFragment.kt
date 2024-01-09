package com.example.farmaturno.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.farmaturno.databinding.FragmentHomePrincipalBinding
import com.example.farmaturno.ui.home.recyclerview.FarmaAdapter
import com.example.farmaturno.ui.home.recyclerview.SuggestionsAdapter
import com.example.farmaturno.ui.home.states.HomeState
import dagger.hilt.android.AndroidEntryPoint
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
        setupRecyclerView()
        initUIState()
        viewModel.getFarmasTurnos()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[HomePrincipalViewModel::class.java]
    }

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