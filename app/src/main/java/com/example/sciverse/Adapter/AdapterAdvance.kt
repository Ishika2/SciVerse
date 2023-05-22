package com.example.sciverse.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sciverse.AllModules.ATGC_Module
import com.example.sciverse.AllModules.MolarityActivity
import com.example.sciverse.AllModules.NormalityActivity
import com.example.sciverse.R
import com.example.sciverse.User.AdvanceUserFragment
import com.example.sciverse.model.moduleAdvance

class AdapterAdvance(private val context: AdvanceUserFragment, private val dataset: List<moduleAdvance>): RecyclerView.Adapter<AdapterAdvance.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_module_advance, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: AdapterAdvance.ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text =  context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)

        if(holder.textView.text == "ATGC Module") {
            holder.imageView.setOnClickListener {
                context.context?.startActivity(Intent(context.context, ATGC_Module::class.java))
            }
        }
    }

}