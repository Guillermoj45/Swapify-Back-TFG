package com.example.swapify_back.Entities

import com.example.swapify_back.Entities.enums.Rol
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.Cascade

@Data
@Entity(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private val id:Long? = null

    @Column(name = "username", nullable = false, unique = true)
    private val userName:String = ""

    @Column(name = "password", nullable = false)
    private val password:String = ""

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role", nullable = false, columnDefinition = "0")
    private val rol: Rol = Rol.USER

    @JsonIgnore


    @OneToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "data_user_id", referencedColumnName = "id")
    var userData: DataUser? = null

}