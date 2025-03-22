package com.example.swapify_back.Repository

import com.example.swapify_back.Entities.ReportProduct
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IReportProductRepository: JpaRepository<ReportProduct, UUID> {
}