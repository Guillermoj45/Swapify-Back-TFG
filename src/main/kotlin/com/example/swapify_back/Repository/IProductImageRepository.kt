package com.example.swapify_back.Repository

import com.example.swapify_back.Entities.Product
import com.example.swapify_back.Entities.ProductImage
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IProductImageRepository: JpaRepository<ProductImage, UUID> {
}