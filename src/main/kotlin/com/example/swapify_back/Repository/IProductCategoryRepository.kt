package com.example.swapify_back.Repository

import com.example.swapify_back.Entities.ProductCategory
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IProductCategoryRepository: JpaRepository<ProductCategory, UUID> {
}