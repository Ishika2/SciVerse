package com.example.sciverse.User

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sciverse.Adapter.AdapterAdvance
import com.example.sciverse.Adapter.AdapterBasic
import com.example.sciverse.data.DatasourceAdvance
import com.example.sciverse.databinding.FragmentAdvanceUserBinding

class AdvanceUserFragment : Fragment() {
    private lateinit var _binding: FragmentAdvanceUserBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAdvanceUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myDataset = DatasourceAdvance().loadModules()
        binding.recyclerView.adapter = AdapterAdvance(this, myDataset)
        binding.recyclerView.setHasFixedSize(true)
    }
}