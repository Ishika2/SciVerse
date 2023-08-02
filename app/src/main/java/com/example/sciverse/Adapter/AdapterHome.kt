package com.example.sciverse.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sciverse.AllModules.ATGC_Module
import com.example.sciverse.AllModules.DNAcuttingActivity
import com.example.sciverse.AllModules.DilutionActivity
import com.example.sciverse.AllModules.GCpercentActivity
import com.example.sciverse.AllModules.MolalityActivity
import com.example.sciverse.AllModules.MolarityActivity
import com.example.sciverse.AllModules.NormalityActivity
import com.example.sciverse.AllModules.ReverseComplementActivity
import com.example.sciverse.HomeScreenViewHolder.FirstViewHolder
import com.example.sciverse.HomeScreenViewHolder.SecondViewHolder
import com.example.sciverse.HomeScreenViewHolder.ThirdViewHolder
import com.example.sciverse.R
import com.example.sciverse.bottomNavBar.HomeFragment
import com.example.sciverse.databinding.ItemCalculatorBinding
import com.example.sciverse.databinding.ItemGenericBinding
import com.example.sciverse.databinding.ItemModuleBinding
import com.example.sciverse.model.allmodules
import com.example.sciverse.model.moduleHomeCalculator
import com.example.sciverse.model.moduleHomeGeneric
import com.example.sciverse.model.moduleHomeModule
import org.w3c.dom.Text
import java.lang.IllegalArgumentException

class AdapterHome(private val context: HomeFragment,
                  private val dataset: List<allmodules>):
    RecyclerView.Adapter<AdapterHome.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textview1: TextView = view.findViewById(R.id.textView1)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val summary: TextView = view.findViewById(R.id.summary)
        val button: Button = view.findViewById(R.id.button)
        val constraintLayout: ConstraintLayout = view.findViewById(R.id.constraintLayout1)

        fun collapseExpandedView(){
            summary.visibility = View.GONE
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_all_modules, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textview1.text =  context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
        holder.summary.text = context.resources.getString(item.summary)

        val isExpandable: Boolean = item.isExpandable
        holder.summary.visibility = if(isExpandable) View.VISIBLE else View.GONE
        holder.button.visibility = if(isExpandable) View.VISIBLE else View.GONE

        if(holder.textview1.text == "DNA Cutting"){
            holder.button.setOnClickListener{
                context.context?.startActivity(Intent(context.context, DNAcuttingActivity::class.java))
            }
        }
        if(holder.textview1.text == "ATGC Module"){
            holder.button.setOnClickListener{
                context.context?.startActivity(Intent(context.context, ATGC_Module::class.java))
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