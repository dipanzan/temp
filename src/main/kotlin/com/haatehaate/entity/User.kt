package com.haatehaate.entity

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @Column(name = "uuid", nullable = true)
    val uuid: UUID = UUID.randomUUID(),
    @Column(name = "phone", length = 14, unique = true, nullable = false)
    val phone: String,
    @Column(name = "password", nullable = false)
    val password: String
)
