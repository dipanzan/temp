package com.haatehaate.entity

import javax.persistence.*

@Entity
@Table(name = "otps")
data class Otp(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(name = "otp")
    val otp: Int,

    @OneToOne
    val user: User
)
