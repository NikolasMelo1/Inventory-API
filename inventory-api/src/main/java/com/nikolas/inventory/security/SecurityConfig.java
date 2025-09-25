
package com.nikolas.inventory.security;
import com.nikolas.inventory.user.AppUserRepository;
import org.springframework.context.annotation.*; import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration public class SecurityConfig {
  @Bean public UserDetailsService uds(AppUserRepository repo){ return username -> repo.findByUsername(username)
    .map(u-> User.withUsername(u.getUsername()).password(u.getPassword()).roles(u.getRole().replace("ROLE_","")).build())
    .orElseThrow(()->new UsernameNotFoundException("not found")); }
  @Bean public PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }
  @Bean public SecurityFilterChain chain(HttpSecurity http, JwtTokenFilter filter) throws Exception {
    http.csrf(cs->cs.disable()).authorizeHttpRequests(a->a
      .requestMatchers("/auth/**","/actuator/health").permitAll()
      .requestMatchers(HttpMethod.GET,"/sales/**","/salespersons/**").hasAnyRole("ADMIN","USER")
      .anyRequest().authenticated()).addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    return http.build(); }
  @Bean public AuthenticationManager am(AuthenticationConfiguration cfg) throws Exception { return cfg.getAuthenticationManager(); }
}