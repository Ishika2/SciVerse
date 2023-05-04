package com.example.sciverse.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sciverse.HomeScreenViewHolder.FirstViewHolder
import com.example.sciverse.HomeScreenViewHolder.SecondViewHolder
import com.example.sciverse.HomeScreenViewHolder.ThirdViewHolder
import com.example.sciverse.R
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
                viewHolder.imageView1.setImageResource(item.imageResourceId1)
                viewHolder.textView2.text = context.resources.getString(item.stringResourceId2)
                viewHolder.imageView2.setImageResource(item.imageResourceId2)
                viewHolder.textView3.text = context.resources.getString(item.stringResourceId3)
                viewHolder.imageView3.setImageResource(item.imageResourceId3)
                viewHolder.textView4.text = context.resources.getString(item.stringResourceId4)
                viewHolder.imageView4.setImageResource(item.imageResourceId4)
                // update other views as needed
//                viewHolder.imageView1.setOnClickListener {
//                    // navigate to a fragment when imageView1 is clicked
//                    val fragment = YourFragment()
//                    val transaction = context.parentFragmentManager.beginTransaction()
//                    transaction.replace(R.id.fragment_container, fragment)
//                    transaction.addToBackStack(null)
//                    transaction.commit()
//                }
            }
            SECOND_VIEW -> {
                val item = dataset2[position - dataset1.size]
                val viewHolder = holder as SecondViewHolder
                viewHolder.imageView.setImageResource(item.stringResourceId1)
                // update other views as needed
//                viewHolder.imageView1.setOnClickListener {
//                    // navigate to a fragment when imageView1 is clicked
//                    val fragment = YourFragment()
//                    val transaction = context.parentFragmentManager.beginTransaction()
//                    transaction.replace(R.id.fragment_container, fragment)
//                    transaction.addToBackStack(null)
//                    transaction.commit()
//                }
            }
            THIRD_VIEW -> {
                val item = dataset3[position - dataset1.size - dataset2.size]
                val viewHolder = holder as ThirdViewHolder
                viewHolder.textView1.text = context.resources.getString(item.stringResourceId1)
                viewHolder.imageView1.setImageResource(item.imageResourceId1)
                viewHolder.textView2.text = context.resources.getString(item.stringResourceId2)
                viewHolder.imageView2.setImageResource(item.imageResourceId2)
                viewHolder.textView3.text = context.resources.getString(item.stringResourceId3)
                viewHolder.imageView3.setImageResource(item.imageResourceId3)
                // update other views as needed
//                viewHolder.imageView1.setOnClickListener {
//                    // navigate to a fragment when imageView1 is clicked
//                    val fragment = YourFragment()
//                    val transaction = context.parentFragmentManager.beginTransaction()
//                    transaction.replace(R.id.fragment_container, fragment)
//                    transaction.addToBackStack(null)
//                    transaction.commit()
//                }
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