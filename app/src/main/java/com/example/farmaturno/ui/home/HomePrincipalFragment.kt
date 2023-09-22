package com.example.farmaturno.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmaturno.databinding.FragmentHomePrincipalBinding
import com.example.farmaturno.ui.home.recyclerview.FarmaAdapter
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

}