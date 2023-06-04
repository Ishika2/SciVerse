package com.example.sciverse.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sciverse.AllModules.DilutionActivity
import com.example.sciverse.AllModules.MolalityActivity
import com.example.sciverse.AllModules.MolarityActivity
import com.example.sciverse.AllModules.NormalityActivity
import com.example.sciverse.R
import com.example.sciverse.bottomNavBar.CalculatorFragment
import com.example.sciverse.model.moduleCalculator

class AdapterCalculator(private val context: CalculatorFragment, private val dataset: List<moduleCalculator>): RecyclerView.Adapter<AdapterCalculator.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCalculator.ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_module_calculator, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text =  context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
        if(holder.textView.text == "Molarity") {
            holder.imageView.setOnClickListener {
                context.context?.startActivity(Intent(context.context, MolarityActivity::class.java))
            }
        }
        if(holder.textView.text == "Normality") {
            holder.imageView.setOnClickListener {
                context.context?.startActivity(Intent(context.context, NormalityActivity::class.java))
            }
        }
        if(holder.textView.text == "Molality") {
            holder.imageView.setOnClickListener {
                context.context?.startActivity(Intent(context.context, MolalityActivity::class.java))
            }
        }
        if(holder.textView.text == "Dilution") {
            holder.imageView.setOnClickListener {
                context.context?.startActivity(Intent(context.context, DilutionActivity::class.java))
            }
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}