package com.haatehaate.security

//import com.haatehaate.entity.User
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.core.userdetails.UserDetails
//
//class AppUserDetails(
//    user: User
//) : UserDetails {
//    companion object {
//        private const val APP_USER = "ROLE_APP_USER"
//    }
//
//    private val username: String
//    private val password: String
//    private val active: Boolean
//    private val authorities: MutableCollection<out GrantedAuthority>
//
//    init {
//        username = user.username
//        password = user.password
//        active = true
//        authorities = mutableListOf(SimpleGrantedAuthority(APP_USER))
//    }
//
//    override fun getUsername(): String {
//        return username
//    }
//
//    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
//        return authorities
//    }
//
//    override fun getPassword(): String {
//        return password
//    }
//
//    override fun isEnabled(): Boolean {
//        return active
//    }
//
//    override fun isAccountNonExpired(): Boolean {
//        return true
//    }
//
//    override fun isAccountNonLocked(): Boolean {
//        return true
//    }
//
//    override fun isCredentialsNonExpired(): Boolean {
//        return true
//    }
//}