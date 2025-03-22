package com.example.swapify_back.Repository

import com.example.swapify_back.Entities.Product
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IProductRepository: JpaRepository<Product, UUID> {
}