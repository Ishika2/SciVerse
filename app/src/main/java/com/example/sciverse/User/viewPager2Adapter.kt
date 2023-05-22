package com.example.sciverse.User

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sciverse.R

class viewPager2Adapter:RecyclerView.Adapter<viewPager2Adapter.EventViewHolder>() {
    val eventList = listOf("0", "1")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    )= viewPager2Adapter.EventViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.layout_view_pager2, parent, false)
    )

    override fun onBindViewHolder(holder: viewPager2Adapter.EventViewHolder, position: Int) {
        (holder.view as? TextView)?.also{
            if(position == 0){
                it.text = "Basic User"
            }else{
                it.text = "Advance User"
            }
        }
    }

    override fun getItemCount(): Int {
        return eventList.count()
    }
    class EventViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}