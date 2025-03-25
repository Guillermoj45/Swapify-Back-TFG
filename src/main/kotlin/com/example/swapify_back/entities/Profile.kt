package com.example.swapify_back.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDate
import java.util.UUID
import kotlin.collections.ArrayList

@Data
@Entity(name = "Profile")
@AllArgsConstructor
@NoArgsConstructor
class Profile {

    @Id
    @Column(name = "id", nullable = false)
    var id: UUID? = null

    @Column(name = "nickname", nullable = false, unique = true)
    var nickname: String = ""

    @Column(name = "avatar", nullable = false)
    var avatar: String = ""

    @Column(name = "born_date")
    var bornDate: LocalDate? = null

    @Column(name = "ban_at")
    var banAt: LocalDate? = null

    @Column(name = "is_new_user", nullable = false)
    var isNewUser: Boolean = true

    @OneToOne
    @JoinColumn(name = "id")
    @JsonIgnore
    var user: User? = null

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    var settings: MutableList<Settings> = ArrayList()

    @OneToOne
    @JoinColumn(name = "id")
    var premium: Premium? = null

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    var products: MutableList<Product> = ArrayList()

    @ManyToMany
    @JoinTable(
        name = "save_product",
        joinColumns = [JoinColumn(name = "profile_id")],
        inverseJoinColumns = [JoinColumn(name = "product_id")]
    )
    var productSave: MutableList<Product> = ArrayList()
}