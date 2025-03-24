package com.example.swapify_back.repository

import com.example.swapify_back.entities.ProductCategory
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IProductCategoryRepository: JpaRepository<ProductCategory, UUID> {
}