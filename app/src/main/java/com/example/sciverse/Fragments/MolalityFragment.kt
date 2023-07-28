package com.example.sciverse.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sciverse.AllModules.GCpercentActivity
import com.example.sciverse.AllModules.MolalityActivity
import com.example.sciverse.R
import com.example.sciverse.databinding.FragmentMolalityBinding

class MolalityFragment : Fragment() {
    private var _binding: FragmentMolalityBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_molality, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set the heading
        binding.heading.text = R.string.molality.toString()

        // Set the summary
        binding.summary.text = getString(R.string.this_is_a_summary_of_the_molarity_topic)

        // Set the button click listener to open the MolarityActivity
        binding.button.setOnClickListener {
            startActivity(Intent(context, MolalityActivity::class.java))
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}