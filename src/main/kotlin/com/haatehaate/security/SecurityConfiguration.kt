package com.haatehaate.security

//import com.haatehaate.user.AppUserDetailsService
//import org.springframework.context.annotation.Bean
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
//import org.springframework.security.crypto.password.NoOpPasswordEncoder
//import org.springframework.security.crypto.password.PasswordEncoder

//@EnableWebSecurity
//class SecurityConfiguration(
//    private val appUserDetailsService: AppUserDetailsService
//) : WebSecurityConfigurerAdapter() {
//
//    override fun configure(auth: AuthenticationManagerBuilder?) {
//        auth?.userDetailsService(appUserDetailsService)
//    }
//
//    override fun configure(http: HttpSecurity?) {
//        http?.authorizeRequests()?.apply {
//            antMatchers("/**").permitAll()
//            and().formLogin()
//        }
//    }
//
//    @Bean
//    fun providePasswordEncoder(): PasswordEncoder {
//        return NoOpPasswordEncoder.getInstance()
//    }
//}
