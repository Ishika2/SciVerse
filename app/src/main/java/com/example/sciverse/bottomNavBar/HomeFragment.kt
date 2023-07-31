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
        val myDataset1 = DatasourceHome().loadModulesForCalculatorView()
        val myDataset2 = DatasourceHome().loadModulesForGenericView()
        val myDataset3 = DatasourceHome().loadModulesForModuleView()
        binding.recyclerView.adapter = AdapterHome(this, myDataset1, myDataset2, myDataset3)
        binding.recyclerView.setHasFixedSize(true)

        val originalDataset: List<allmodules> = DatasourceFull().loadModules()
        val filteredDataset: List<allmodules> = originalDataset

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterDataset(newText, filteredDataset)
                return true
            }
        })
    }

    private fun filterDataset(query: String?, originalDataset: List<allmodules>) {
    }

        override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}