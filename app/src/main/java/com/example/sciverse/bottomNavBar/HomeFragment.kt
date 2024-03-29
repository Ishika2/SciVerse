package com.example.sciverse.bottomNavBar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.example.sciverse.Adapter.AdapterHome
import com.example.sciverse.data.DatasourceFull
import com.example.sciverse.data.DatasourceHome
import com.example.sciverse.databinding.FragmentHomeBinding
import com.example.sciverse.model.allmodules
import java.util.Locale

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myDataset = DatasourceFull().loadModules()
        binding.recyclerView.adapter = AdapterHome(this, myDataset)
        binding.recyclerView.setHasFixedSize(true)
    }

        override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}