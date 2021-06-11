package com.haatehaate.service

import com.haatehaate.entity.Token
import com.haatehaate.entity.User
import com.haatehaate.repository.TokenRepository
import org.springframework.stereotype.Service
import java.security.SecureRandom
import java.time.LocalDateTime
import java.util.*

@Service
class TokenService(
    private val tokenRepository: TokenRepository
) {
    companion object {
        private const val TOKEN_LENGTH = 24
        private const val TOKEN_DEFAULT_VALID_TIME = 12
    }

    fun generateNewToken(user: User): Token {
        val newToken = Token(value = generateRandomToken(), tokenValidTill = tokenValidFromNowPlus(), user = user)
        val token = tokenRepository.findByUser(user).orElse(newToken)
        return tokenRepository.save(token)
    }

    fun refreshUserToken(user: User): Token {
        val token = tokenRepository
            .findByUser(user)
            .get()
            .updateTokenValidTill(tokenValidFromNowPlus())
        return tokenRepository.save(token)
    }

    private fun generateRandomToken(): String {
        val secureRandom = SecureRandom()
        val base64Encoder = Base64.getUrlEncoder()

        val byteArray = ByteArray(TOKEN_LENGTH)
        secureRandom.nextBytes(byteArray)
        return base64Encoder.encodeToString(byteArray).uppercase(Locale.getDefault())
    }

    private fun tokenValidFromNowPlus(hours: Long? = null): LocalDateTime {
        return LocalDateTime.now().plusHours(hours ?: TOKEN_DEFAULT_VALID_TIME.toLong())
    }
}