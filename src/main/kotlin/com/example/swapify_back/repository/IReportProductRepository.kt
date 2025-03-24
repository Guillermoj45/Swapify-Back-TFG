package com.example.swapify_back.repository

import com.example.swapify_back.entities.ReportProduct
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IReportProductRepository: JpaRepository<ReportProduct, UUID> {
}