package com.example.swapify_back.Entities

import jakarta.persistence.Entity
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@Entity
@AllArgsConstructor
class DataUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id:Int

    @Column
    private val name:String

    @Column
    private val lastName:String

}
