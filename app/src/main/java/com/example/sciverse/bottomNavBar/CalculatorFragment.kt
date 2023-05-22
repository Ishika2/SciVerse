package com.example.sciverse.bottomNavBar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sciverse.Adapter.AdapterCalculator
import com.example.sciverse.R
import com.example.sciverse.data.DatasourceCalculator
import com.example.sciverse.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment() {
    private lateinit var _binding: FragmentCalculatorBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myDataset = DatasourceCalculator().loadModules()
        binding.recyclerView.adapter = AdapterCalculator(this, myDataset)
        binding.recyclerView.setHasFixedSize(true)
    }
}