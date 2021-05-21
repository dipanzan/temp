package com.haatehaate.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "username", length = 14, unique = true, nullable = false)
    var username: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "otp_verified", nullable = false)
    var otpVerified: Boolean = false,

    @Column(name = "registered_at")
    var registeredAt: LocalDateTime = LocalDateTime.now()

    /*@OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "verification_id", referencedColumnName = "id")
    var verification: Verification*/
)/* : BaseEntity()*/