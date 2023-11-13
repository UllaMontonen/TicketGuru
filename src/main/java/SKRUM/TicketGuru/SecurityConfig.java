package SKRUM.TicketGuru;

import SKRUM.TicketGuru.auth.JwtAuthorizationFilter;
import SKRUM.TicketGuru.web.UserDetailsServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfig(UserDetailsServiceImpl customUserDetailsService, JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.userDetailsService = customUserDetailsService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;

    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(10));
        return authenticationManagerBuilder.build();
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
    	CorsConfiguration configuration = new CorsConfiguration();
    	configuration.setAllowedOrigins(Arrays.asList("*"));
    	configuration.setAllowedMethods(Arrays.asList("*"));
    	configuration.setAllowedHeaders(Arrays.asList("*"));
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", configuration);
    	return source; 	
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf
                		.ignoringRequestMatchers(antMatcher("/h2-console/**"))
                		.disable())
                .cors(withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(antMatcher("/api/auth/**")).permitAll()
                        .requestMatchers(antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(antMatcher("/api/customers/**")).hasRole("ADMIN")

                        .requestMatchers(antMatcher(HttpMethod.GET,"/api/transactions/**")).hasAnyRole("ADMIN", "USER")
                        .requestMatchers(antMatcher(HttpMethod.POST,"/api/transactions/**")).hasAnyRole("ADMIN", "USER")
                        .requestMatchers(antMatcher(HttpMethod.PUT,"/api/transactions/**")).hasAnyRole("ADMIN", "USER")
                        .requestMatchers(antMatcher(HttpMethod.DELETE,"/api/transactions/**")).hasAnyRole("ADMIN", "USER")

                        .requestMatchers(antMatcher(HttpMethod.GET, "/api/tickets/**")).hasAnyRole("ADMIN", "USER", "SCANNER")
                        .requestMatchers(antMatcher(HttpMethod.PATCH, "/api/tickets/**")).hasAnyRole("ADMIN", "USER", "SCANNER")
                        .requestMatchers(antMatcher(HttpMethod.POST,"/api/tickets/**")).hasAnyRole("ADMIN", "USER")
                        .requestMatchers(antMatcher(HttpMethod.PUT,"/api/tickets/**")).hasAnyRole("ADMIN", "USER")
                        .requestMatchers(antMatcher(HttpMethod.DELETE,"/api/tickets/**")).hasAnyRole("ADMIN", "USER")

                        .requestMatchers(antMatcher(HttpMethod.GET, "/api/events/**")).hasAnyRole("USER", "ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.POST, "/api/events/**")).hasRole("ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.PUT, "/api/events/**")).hasRole("ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.DELETE, "/api/events/**")).hasRole("ADMIN")

                        .requestMatchers(antMatcher(HttpMethod.GET, "/api/tickettypes/**")).hasAnyRole("USER", "ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.POST, "/api/tickettypes/**")).hasRole("ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.PUT, "/api/tickettypes/**")).hasRole("ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.DELETE, "/api/tickettypes/**")).hasRole("ADMIN")
                        .anyRequest().authenticated())
                .headers(headers -> headers
                		.frameOptions().disable())
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}