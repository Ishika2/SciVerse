package com.example.sciverse.NavigationDrawer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.sciverse.Adapter.AdapterAdvance
import com.example.sciverse.JobDB.job
import com.example.sciverse.JobDB.jobDao
import com.example.sciverse.R

class AdapterForJobid(private val context: Context, val datalist: List<job>): RecyclerView.Adapter<AdapterForJobid.ItemViewHolder>() {

    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val serial_no: TextView = view.findViewById(R.id.serial_no)
        val job_id: TextView = view.findViewById(R.id.job_id)
        val result: TextView = view.findViewById(R.id.result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.job_id_list, parent, false)
        return AdapterForJobid.ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = datalist[position]
        holder.serial_no.text = "serial no. :- "+item.id.toString()
        holder.job_id.text = "Job Id:- "+item.jobid.toString()
        holder.result.text = "Result:- "+item.Result.toString()
    }
}