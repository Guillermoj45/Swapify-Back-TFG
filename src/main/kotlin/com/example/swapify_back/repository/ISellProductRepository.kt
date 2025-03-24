package com.example.swapify_back.repository

import com.example.swapify_back.entities.SellProduct
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ISellProductRepository: JpaRepository<SellProduct, UUID> {
}