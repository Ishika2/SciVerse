package com.example.sciverse.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sciverse.AllModules.ATGC_Module
import com.example.sciverse.AllModules.DilutionActivity
import com.example.sciverse.AllModules.GCpercentActivity
import com.example.sciverse.AllModules.MolalityActivity
import com.example.sciverse.AllModules.MolarityActivity
import com.example.sciverse.AllModules.NormalityActivity
import com.example.sciverse.AllModules.ReverseComplementActivity
import com.example.sciverse.HomeScreenViewHolder.FirstViewHolder
import com.example.sciverse.HomeScreenViewHolder.SecondViewHolder
import com.example.sciverse.HomeScreenViewHolder.ThirdViewHolder
import com.example.sciverse.bottomNavBar.HomeFragment
import com.example.sciverse.databinding.ItemCalculatorBinding
import com.example.sciverse.databinding.ItemGenericBinding
import com.example.sciverse.databinding.ItemModuleBinding
import com.example.sciverse.model.moduleHomeCalculator
import com.example.sciverse.model.moduleHomeGeneric
import com.example.sciverse.model.moduleHomeModule
import java.lang.IllegalArgumentException

class AdapterHome(private val context: HomeFragment,
                  private val dataset1: List<moduleHomeCalculator>,
                  private val dataset2: List<moduleHomeGeneric>,
                  private val dataset3: List<moduleHomeModule>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val FIRST_VIEW = 1
        const val SECOND_VIEW = 2
        const val THIRD_VIEW = 3
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            FIRST_VIEW -> FirstViewHolder(ItemCalculatorBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            SECOND_VIEW -> SecondViewHolder(ItemGenericBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            THIRD_VIEW -> ThirdViewHolder(ItemModuleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw IllegalArgumentException("Invalid argument type")
        }
    }

    override fun getItemCount(): Int {
        return dataset1.size + dataset2.size + dataset3.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            FIRST_VIEW -> {
                val item = dataset1[position]
                val viewHolder = holder as FirstViewHolder
                viewHolder.textView1.text = context.resources.getString(item.stringResourceId1)
                viewHolder.summary1.text = context.resources.getString(item.summary1)
                viewHolder.textView2.text = context.resources.getString(item.stringResourceId2)
                viewHolder.summary2.text = context.resources.getString(item.summary2)
                // update other views as needed
                viewHolder.go1.setOnClickListener{
                    val intent = Intent(context.context, MolalityActivity::class.java)
                    context.context?.startActivity(intent)
                }
                viewHolder.go2.setOnClickListener{
                    val intent = Intent(context.context, MolarityActivity::class.java)
                    context.context?.startActivity(intent)
                }
            }
            SECOND_VIEW -> {
                val item = dataset2[position - dataset1.size]
                val viewHolder = holder as SecondViewHolder
            }
            THIRD_VIEW -> {
                val item = dataset3[position - dataset1.size - dataset2.size]
                val viewHolder = holder as ThirdViewHolder
                viewHolder.textView1.text = context.resources.getString(item.stringResourceId1)
                viewHolder.textView2.text = context.resources.getString(item.stringResourceId2)
                viewHolder.textView3.text = context.resources.getString(item.stringResourceId3)
                // update other views as needed
                viewHolder.textView1.setOnClickListener {
                    val intent = Intent(context.context, ATGC_Module::class.java)
                    context.context?.startActivity(intent)
                }
                viewHolder.textView2.setOnClickListener{
                    val intent = Intent(context.context, GCpercentActivity::class.java)
                    context.context?.startActivity(intent)
                }
                viewHolder.textView3.setOnClickListener{
                    val intent = Intent(context.context, ReverseComplementActivity::class.java)
                    context.context?.startActivity(intent)
                }
            }
            else -> throw IllegalArgumentException("Invalid argument type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position < dataset1.size -> FIRST_VIEW
            position < dataset1.size + dataset2.size -> SECOND_VIEW
            else -> THIRD_VIEW
        }
    }


}