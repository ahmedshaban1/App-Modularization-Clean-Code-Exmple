package com.example.home.presenter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.example.home.data.HomeSection
import com.example.uicomponents.helpers.inflate
import kotlinx.android.synthetic.main.home_adapter_item.view.*

class HomeAdapter(val list: List<HomeSection>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeViewHolder(parent.inflate(R.layout.home_adapter_item))

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val section = list[position]
        with(holder.itemView) {
            sectionTitle.text = section.title
        }
        holder.bind(section)
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var homeInnerAdapter: HomeInnerAdapter
        fun bind(section: HomeSection) {
            homeInnerAdapter = HomeInnerAdapter(section)
            itemView.sectionsRv.apply {
                layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = homeInnerAdapter
            }
        }
    }
}


