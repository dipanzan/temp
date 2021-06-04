package com.haatehaate.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "Token")
@Table(name = "tokens")
data class Token(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "value")
    var value: String,

    @Column(name = "valid_till")
    var tokenValidTill: LocalDateTime,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "token")
    var user: User
) {
    fun updateTokenValidTill(tokenValidTill: LocalDateTime): Token {
        this.tokenValidTill = tokenValidTill
        return this
    }
}