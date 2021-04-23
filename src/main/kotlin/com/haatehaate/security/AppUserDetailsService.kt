package com.haatehaate.security

//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.core.userdetails.UsernameNotFoundException
//import org.springframework.stereotype.Service

//@Service
//class AppUserDetailsService(
//    private val userRepository: UserRepository
//) : UserDetailsService {
//
//    @Throws(UsernameNotFoundException::class)
//    override fun loadUserByUsername(username: String): UserDetails {
//        val user = userRepository.findByUsername(username)
//
//        return user.map {
//            AppUserDetails(it)
//        }.orElseThrow {
//            UsernameNotFoundException("Username $username not found!")
//        }
//    }
//}