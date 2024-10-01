package codeplac.codeplac.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private SecurityFilter securityFilter; // Injeta seu filtro de segurança

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring security filter chain");

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/teste").permitAll()
                        .requestMatchers(HttpMethod.POST, "/group/create").permitAll()
                        .requestMatchers(HttpMethod.POST, "/event/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/registration/create").hasRole("PARTICIPANT")
                        .requestMatchers(HttpMethod.GET, "/auth/list").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/auth/{matricula}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/auth/modify/{matricula}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/auth/destroy/{matricula}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/event/list").permitAll()
                        .requestMatchers(HttpMethod.GET, "/event/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/event//modify/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/event/destroy/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/group/list").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/group/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/group/modify/{id}").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/group/destroy/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/registration/list").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/registration/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/registration/modify/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/registration/destroy/{id}").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("PARTICIPANT")
                        .anyRequest().authenticated())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new Http403ForbiddenEntryPoint()))

                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        logger.info("Security filter chain configured");

        return http.build();
    }

        @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5500", "http://localhost:5173"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);
    
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        logger.info("Creating PasswordEncoder bean");
        return new BCryptPasswordEncoder();
    }

    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        logger.info("Creating AuthenticationManager bean");
        return authenticationConfiguration.getAuthenticationManager();
    }
}