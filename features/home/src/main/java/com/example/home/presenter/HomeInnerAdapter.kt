package com.example.home.presenter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.common.ApiConfigs.IMAGE_BASE_URL
import com.example.home.Constants
import com.example.home.R
import com.example.home.data.HomeSection
import com.example.uicomponents.helpers.inflate
import kotlinx.android.synthetic.main.category_item.view.*
import kotlinx.android.synthetic.main.product_item.view.*

class HomeInnerAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var section: HomeSection? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (section?.viewType) {
            Constants.PRODUCT_VIEW_TYPE ->{
                return ProductViewHolder(parent.inflate(R.layout.product_item))
            }
            else -> {
                return CategoryViewHolder(parent.inflate(R.layout.category_item))
            }
        }
    }

    override fun getItemCount(): Int {
        section?.categories?.let {
            return it.size
        }
        section?.products?.let {
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CategoryViewHolder -> {
                val item = section?.categories?.get(position)
                holder.itemView.categoryView.apply {
                    item?.let {
                        setTitle(it.name)
                        setCover("${IMAGE_BASE_URL}${it.imageUrl}")
                    }

                }

            }
            is ProductViewHolder -> {
                val item = section?.products?.get(position)
                holder.itemView.productView.apply {
                    item?.let {
                        setTitle(it.title!!)
                        setCover("${IMAGE_BASE_URL}${it.imageUrl}")
                        setPrice("$${it.price}")
                    }

                }
            }
        }
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    fun updateSection(updatedSection: HomeSection) {
        section = updatedSection/*
        section.viewType = updatedSection.viewType
        updatedSection.categories?.let {
            section.categories?.clear()
            section.categories?.addAll(it)
        }?:run{
            updatedSection.products?.let {
                section.products?.clear()
                section.products?.addAll(it)
            }
        }
*/

        notifyDataSetChanged()
    }

}