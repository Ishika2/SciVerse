package com.example.sciverse.HomeScreenViewHolder

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sciverse.databinding.ItemCalculatorBinding

class FirstViewHolder(val binding:ItemCalculatorBinding):RecyclerView.ViewHolder(binding.root) {
    val textView1: TextView = binding.textView1
    val imageView1: ImageView = binding.imageView1

    val textView2: TextView = binding.textView2
    val imageView2: ImageView = binding.imageView2

    val textView3: TextView = binding.textView3
    val imageView3: ImageView = binding.imageView3

    val textView4: TextView = binding.textView4
    val imageView4: ImageView = binding.imageView4
}