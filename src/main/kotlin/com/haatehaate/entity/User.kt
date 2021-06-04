package com.haatehaate.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "User")
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
    var otpVerified: Boolean,

    @JoinColumn(name = "token_id")
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var token: Token? = null,

    @Column(name = "registered_at")
    var registeredAt: LocalDateTime = LocalDateTime.now()

) {
    fun setOtpVerified(otpVerified: Boolean): User {
        this.otpVerified = otpVerified
        return this
    }

    fun setToken(token: Token): User {
        this.token = token
        return this
    }

    fun setRegisteredAt(registeredAt: LocalDateTime): User {
        this.registeredAt = registeredAt
        return this
    }
}