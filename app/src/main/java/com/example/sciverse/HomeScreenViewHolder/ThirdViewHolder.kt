package com.example.sciverse.HomeScreenViewHolder

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sciverse.databinding.ItemModuleBinding

class ThirdViewHolder(val binding: ItemModuleBinding): RecyclerView.ViewHolder(binding.root) {
    val textView1: TextView = binding.textView1

    val textView2: TextView = binding.textView2

    val textView3: TextView = binding.textView3
}