package com.example.swapify_back.service

import com.example.swapify_back.Entities.ProductCategory
import com.example.swapify_back.Repository.IProductCategoryRepository
import com.example.swapify_back.Repository.IProductRepository
import com.example.swapify_back.Repository.IReportProductRepository

class ProductService(
    private val productRepository: IProductRepository,
    private val reportProductRepository: IReportProductRepository,
    private val productCategoryRepository: IProductCategoryRepository
) {
}