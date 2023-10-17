package com.tuan.banhang.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig  {
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT username, password, 1 as enabled FROM customers WHERE username = ?");
//        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT c.customer_id, r.name FROM customers c INNER JOIN roles r ON c.customer_id = r.customer_id WHERE c.username = ?");
//        return jdbcUserDetailsManager;
//    }
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john= User.builder()
                .username("tuan")
                .password("{noop}test123")
                .roles("USER")
                .build();
        UserDetails mary= User.builder()
                .username("hung")
                .password("{noop}test123")
                .roles("USER")
                .build();
        UserDetails susan= User.builder()
                .username("admin")
                .password("{noop}test123")
                .roles("ADMIN")
                .build();
        UserDetails tuan= User.builder()
                .username("tuan33qt")
                .password("{noop}test123")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(john,mary,susan,tuan);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/","/shop/**","/register").permitAll()
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(logout -> logout.permitAll()
                );

        return http.build();
    }
}
