package br.com.fiap.mercadoexpressmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
                .username("marcel")
                .password(passwordEncoder.encode("tranquilo123"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // recursos estaticos
                .requestMatchers("/css/**", "/img/**").permitAll()
                // pagina de login
                .requestMatchers("/login").permitAll()
                // leitura publica: listar e ver detalhes de produtos
                .requestMatchers(HttpMethod.GET, "/produtos").permitAll()
                .requestMatchers(HttpMethod.GET, "/produtos/{id:[0-9]+}").permitAll()
                .requestMatchers("/").permitAll()
                // qualquer outra operacao em /produtos (criar, editar, excluir) exige login
                .requestMatchers("/produtos/**").authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/produtos", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/produtos?logout")
                .permitAll()
            );

        // Protecao CSRF permanece ativa (padrao do Spring Security). Todos os
        // formularios da aplicacao usam th:action do Thymeleaf, que inclui o
        // token automaticamente.

        return http.build();
    }
}
