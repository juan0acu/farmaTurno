package com.example.farmaturno

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.farmaturno.databinding.FragmentHomePrincipalBinding


class HomePrincipalFragment : Fragment() {

    private lateinit var binding: FragmentHomePrincipalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_principal, container, false)
    }

}