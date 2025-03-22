package com.example.swapify_back.Entities

import com.fasterxml.jackson.annotation.JsonIgnore
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
    var userId: UUID? = null

    @Column(name = "setting", nullable = false, columnDefinition = "json")
    var setting: String = "{}"

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var user: User? = null
}