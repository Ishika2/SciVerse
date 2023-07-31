package com.example.sciverse.HomeScreenViewHolder

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sciverse.databinding.ItemCalculatorBinding
import org.w3c.dom.Text

class FirstViewHolder(val binding:ItemCalculatorBinding):RecyclerView.ViewHolder(binding.root) {
    val textView1: TextView = binding.textView1
    val summary1: TextView = binding.summary1
    val go1: TextView = binding.go1

    val textView2: TextView = binding.textView2
    val summary2: TextView = binding.summary2
    val go2: TextView = binding.go2
}