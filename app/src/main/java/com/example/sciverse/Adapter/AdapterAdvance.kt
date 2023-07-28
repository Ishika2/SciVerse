package com.example.sciverse.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.sciverse.AllModules.ATGC_Module
import com.example.sciverse.AllModules.DNAcuttingActivity
import com.example.sciverse.AllModules.DilutionActivity
import com.example.sciverse.AllModules.GCpercentActivity
import com.example.sciverse.AllModules.MolalityActivity
import com.example.sciverse.AllModules.MolarityActivity
import com.example.sciverse.AllModules.NormalityActivity
import com.example.sciverse.AllModules.ReverseComplementActivity
import com.example.sciverse.R
import com.example.sciverse.User.AdvanceUserFragment
import com.example.sciverse.model.moduleAdvance

class AdapterAdvance(private val context: AdvanceUserFragment, private val dataset: List<moduleAdvance>): RecyclerView.Adapter<AdapterAdvance.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textView2: TextView = view.findViewById((R.id.summary))
        val button: Button = view.findViewById(R.id.button)
        val constraintLayout: ConstraintLayout = view.findViewById(R.id.constraintLayout1)

        fun collapseExpandedView(){
            textView2.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_module_advance, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text =  context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
        holder.textView2.text = context.resources.getString(item.summaryResourceId)

        val isExpandable: Boolean = item.isExpandable
        holder.textView2.visibility = if(isExpandable) View.VISIBLE else View.GONE
        holder.button.visibility = if(isExpandable) View.VISIBLE else View.GONE

        if(holder.textView.text == "ATGC Module"){
            holder.button.setOnClickListener{
                context.context?.startActivity(Intent(context.context, ATGC_Module::class.java))
            }
        }
        if(holder.textView.text == "DNA Cutting"){
            holder.button.setOnClickListener{
                context.context?.startActivity(Intent(context.context, DNAcuttingActivity::class.java))
            }
        }
        if(holder.textView.text == "GC Percent"){
            holder.button.setOnClickListener{
                context.context?.startActivity(Intent(context.context, GCpercentActivity::class.java))
            }
        }
        if(holder.textView.text == "Reverse Complement"){
            holder.button.setOnClickListener{
                context.context?.startActivity(Intent(context.context, ReverseComplementActivity::class.java))
            }
        }

        holder.constraintLayout.setOnClickListener {
            isAnyItemExpanded(position)
            item.isExpandable = !item.isExpandable
            notifyItemChanged(position , Unit)
        }
    }
    fun isAnyItemExpanded(position: Int){
        val temp = dataset.indexOfFirst {
            it.isExpandable
        }
        if (temp >= 0 && temp != position){
            dataset[temp].isExpandable = false
            notifyItemChanged(temp, Unit)
        }
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if(payloads.isNotEmpty() && payloads[0] == 0){
            holder.collapseExpandedView()
        }else{
            super.onBindViewHolder(holder, position, payloads)

        }
    }

}