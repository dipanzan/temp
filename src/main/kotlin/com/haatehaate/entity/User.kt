package com.haatehaate.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class User constructor(
    @Column(name = "username", length = 14, unique = true, nullable = false)
    val username: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "token", nullable = true)
    val token: UUID? = null
)
