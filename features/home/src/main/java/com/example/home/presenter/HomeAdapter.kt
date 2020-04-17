package com.example.home.presenter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.example.home.data.HomeSection
import com.example.home.databinding.HomeAdapterItemBinding
import com.example.uicomponents.helpers.inflate
import kotlinx.android.synthetic.main.home_adapter_item.view.*

class HomeAdapter(var list: MutableList<HomeSection> = mutableListOf(), viewModel: HomeViewModel) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeViewHolder(parent.inflate(R.layout.home_adapter_item))

    override fun getItemCount() = list.size
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val section = list[position]
        holder.bind(section)
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var homeInnerAdapter: HomeInnerAdapter
        private val binding = HomeAdapterItemBinding.bind(itemView)
        fun bind(section: HomeSection) {
            binding.section = section
            homeInnerAdapter = HomeInnerAdapter()
            itemView.sectionsRv.apply {
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                adapter = homeInnerAdapter
            }
        }
    }

    fun updateList(listUpdate: List<HomeSection>) {
        list.clear()
        list.addAll(listUpdate)
        notifyDataSetChanged()

    }
}


