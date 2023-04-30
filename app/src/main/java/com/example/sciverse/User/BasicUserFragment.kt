package com.example.sciverse.User

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sciverse.Adapter.AdapterBasic
import com.example.sciverse.data.DatasourceBasic
import com.example.sciverse.databinding.FragmentBasicUserBinding

class BasicUserFragment : Fragment() {
    private lateinit var _binding: FragmentBasicUserBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBasicUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myDataset = DatasourceBasic().loadModules()
        binding.recyclerView.adapter = AdapterBasic(this, myDataset)
        binding.recyclerView.setHasFixedSize(true)
    }

}