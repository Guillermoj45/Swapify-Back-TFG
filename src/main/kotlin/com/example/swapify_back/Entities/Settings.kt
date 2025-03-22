package com.example.swapify_back.Entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.util.UUID

@Data
@Entity(name = "settings")
@AllArgsConstructor
@NoArgsConstructor
class Settings {

    @Id
    @Column(name = "user_id", nullable = false)
    private val userId: UUID? = null

    @Column(name = "setting", nullable = false, columnDefinition = "json")
    private val setting: String = "{}"

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private val user: User? = null
}