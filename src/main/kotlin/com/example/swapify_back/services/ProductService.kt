package com.example.swapify_back.services

import com.example.swapify_back.entities.Product
import com.example.swapify_back.repository.IProductCategoryRepository
import com.example.swapify_back.repository.IProductRepository
import com.example.swapify_back.repository.IReportProductRepository
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