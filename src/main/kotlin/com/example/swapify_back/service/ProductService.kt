package com.example.swapify_back.service

import com.example.swapify_back.Entities.Product
import com.example.swapify_back.Entities.ProductCategory
import com.example.swapify_back.Repository.IProductCategoryRepository
import com.example.swapify_back.Repository.IProductRepository
import com.example.swapify_back.Repository.IReportProductRepository
import com.example.swapify_back.cloudinary.CloudinaryService

class ProductService(
    private val productRepository: IProductRepository,
    private val reportProductRepository: IReportProductRepository,
    private val productCategoryRepository: IProductCategoryRepository,
    private val cloudinaryService: CloudinaryService
) {

    fun deleteProduct(product: Product) {
        cloudinaryService.deleteImage(product.imagenes)
        productRepository.delete(product)
    }
}