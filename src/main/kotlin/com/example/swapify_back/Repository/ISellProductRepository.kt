package com.example.swapify_back.Repository

import com.example.swapify_back.Entities.SellProduct
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ISellProductRepository: JpaRepository<SellProduct, UUID> {
}