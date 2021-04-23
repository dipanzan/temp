package com.haatehaate.repository

import com.haatehaate.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Int> {
    fun findUserByUsernameAndPassword(username: String, password: String): Optional<User>
    fun existsUserByUsername(username: String): Boolean
}