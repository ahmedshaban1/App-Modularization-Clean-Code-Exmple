package com.example.home.data.datasource.local

import com.example.home.data.HomeSection
import com.example.local.dao.CategoryDao
import com.example.local.dao.ProductDao

class HomeLocalImpl(val productDao: ProductDao, val categoryDao: CategoryDao) : HomeLocal {
    override suspend fun getHome(): List<HomeSection> {
        val list: MutableList<HomeSection> = mutableListOf()
        val cats = categoryDao.getAll()
        if (cats.isNotEmpty()) {
            val catsSection =
                HomeSection(title = "Categories", categories = cats.toMutableList(), viewType = "category")
            list.add(catsSection)
        }
        val allProducts  = productDao.getAll()
        allProducts.filter { item-> item.isFeatured!! }.let {
            val section =
                HomeSection(title = "Featured", products = it.toMutableList(), viewType = "product")
            list.add(section)
        }

        allProducts.filter { item-> item.isBestSell!! }.let {
            val section =
                HomeSection(title = "Best Sell", products = it.toMutableList(), viewType = "product")
            list.add(section)
        }

        allProducts.filter { item-> item.isMastRated!! }.let {
            val section =
                HomeSection(title = "Mast Rated", products = it.toMutableList(), viewType = "product")
            list.add(section)
        }

        allProducts.filter { item-> item.isMastVisited!! }.let {
            val section =
                HomeSection(title = "Mast Visited", products = it.toMutableList(), viewType = "product")
            list.add(section)
        }

        allProducts.filter { item-> item.isRecommended!! }.let {
            val section =
                HomeSection(title = "Recommend for you", products = it.toMutableList(), viewType = "product")
            list.add(section)
        }
        return list
    }

    override suspend fun saveHomeData(data: List<HomeSection>) {
        for (section in data) {
            section.categories?.let {
                it.forEach {
                    categoryDao.insert(it)
                }
            }

            section.products?.let {
                it.forEach {
                    productDao.insert(it)
                }
            }
        }
    }
}