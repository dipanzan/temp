package com.haatehaate.repository

import com.haatehaate.entity.SmsLog
import com.haatehaate.entity.Token
import com.haatehaate.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findUserByUsername(username: String): Optional<User>
    fun existsByUsernameAndOtpVerifiedIsTrue(username: String): Boolean
    fun existsByUsernameAndOtpVerifiedIsFalse(username: String): Boolean
    fun findUserByUsernameAndPassword(username: String, password: String): Optional<User>
    fun findUserByUsernameAndOtpVerifiedIsFalse(username: String): Optional<User>
}

@Repository
interface SmsLogRepository : JpaRepository<SmsLog, Long> {
    fun existsByRecipientNumberAndOtp(recipientNumber: String, otp: String): Boolean
    fun existsByOtp(otp: String): Boolean
    fun countByRecipientNumber(recipientNumber: String): Long
}

@Repository
interface TokenRepository : JpaRepository<Token, Long> {
    fun findByUser(user: User): Optional<Token>
}
/*@Repository
interface VerificationRepository : JpaRepository<Verification, Long>*/
