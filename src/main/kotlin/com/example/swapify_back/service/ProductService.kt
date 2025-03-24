package com.example.swapify_back.service

import com.example.swapify_back.repository.IProductCategoryRepository
import com.example.swapify_back.repository.IProductRepository
import com.example.swapify_back.repository.IReportProductRepository

class ProductService(
    private val productRepository: IProductRepository,
    private val reportProductRepository: IReportProductRepository,
    private val productCategoryRepository: IProductCategoryRepository
) {
}