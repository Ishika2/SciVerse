package com.example.sciverse.HomeScreenViewHolder

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sciverse.databinding.ItemGenericBinding

class SecondViewHolder(val binding: ItemGenericBinding): RecyclerView.ViewHolder(binding.root) {
    val imageView: ImageView = binding.imageView
    val textView: TextView = binding.textview
}