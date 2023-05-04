package com.example.sciverse.bottomNavBar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.sciverse.Adapter.AdapterBasic
import com.example.sciverse.Adapter.AdapterHome
import com.example.sciverse.R
import com.example.sciverse.data.DatasourceBasic
import com.example.sciverse.data.DatasourceHome
import com.example.sciverse.databinding.FragmentHomeBinding
import com.example.sciverse.databinding.FragmentLibraryBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
    }
}