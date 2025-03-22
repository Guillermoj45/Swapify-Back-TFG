package com.example.swapify_back.Entities

import com.example.swapify_back.Entities.enums.PremiunRol
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import java.util.UUID

@Data
@Entity(name = "premiun")
@AllArgsConstructor
@NoArgsConstructor
class Premium {

    @Id
    @Column(name = "profile_id", nullable = false)
    private val profileId: UUID? = null

    @Column(name = "by_at", nullable = false)
    private val byAt: LocalDateTime? = null

    @Column(name = "rol", nullable = false)
    private val rol: PremiunRol = PremiunRol.FREE
}