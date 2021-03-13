package com.haatehaate.registration.repository

import com.haatehaate.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun existsUserByPhone(phone: String): Boolean
}