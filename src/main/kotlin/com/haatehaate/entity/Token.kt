package com.haatehaate.entity

import javax.persistence.*

//@Entity
//@Table(name = "tokens")
data class Token(
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,

//    @Column(name = "name", unique = true, nullable = true)
    val token: String
)
