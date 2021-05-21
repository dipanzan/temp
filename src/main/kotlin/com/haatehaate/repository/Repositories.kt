package com.haatehaate.repository

import com.haatehaate.entity.SmsLog
import com.haatehaate.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun existsUserByUsernameAndOtpVerifiedIsTrue(username: String): Boolean
    fun existsUserByUsernameAndOtpVerifiedIsFalse(username: String): Boolean
    fun findUserByUsernameAndPassword(username: String, password: String): Optional<User>
    fun existsUserByUsername(username: String): Boolean
    fun findUserByUsername(username: String): Optional<User>
}

interface SmsLogRepository : JpaRepository<SmsLog, Long> {
    fun countByRecipientNumber(recipientNumber: String): Long
    fun findByRecipientNumber(recipientNumber: String): Optional<SmsLog>
    fun existsSmsLogByRecipientNumberAndOtpNot(recipientNumber: String, otp: String): Boolean
}


/*@Repository
interface VerificationRepository : JpaRepository<Verification, Long>*/
