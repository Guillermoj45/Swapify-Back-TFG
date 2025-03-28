package com.example.swapify_back.entities

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
    @Column(name = "profile_id", nullable = false)
    var profileId: UUID? = null

    @Column(name = "name", nullable = false)
    var name: String = ""

    @Column(name = "setting_value", nullable = false)
    var settingValue: String = ""

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    var profile: Profile? = null
}