package com.example.crud_shopall.security;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");

        return http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/api/v1/auth/**", "/v3/api-docs/**", "/v3/api-docs.yaml", "/swagger-ui/**", "/swagger-ui.html")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/estado", "/api/v1/municipio", "/api/v1/localidad").hasAnyRole("Vendedor", "Comprador")
                .requestMatchers(HttpMethod.GET, "/api/v1/tipo_pago", "/api/v1/lada/all", "/api/v1/sexo/all").hasAnyRole("Vendedor", "Comprador")
                .requestMatchers(HttpMethod.GET, "/api/v1/categoria/all", "/api/v1/marca/all").hasAnyRole("Vendedor", "Comprador")
                .requestMatchers(HttpMethod.GET, "/api/v1/venta_operacion/all", "/api/v1/ventadetalle", "/api/v1/ventastatus").hasAnyRole("Vendedor", "Comprador")
                .requestMatchers(HttpMethod.GET, "/api/v1/producto/all", "/api/v1/tienda", "/api/v1/tiendaProducto/all").hasRole("Comprador")
                .requestMatchers(HttpMethod.GET,"/api/v1/resenia").hasRole("Vendedor")
                .requestMatchers("/api/v1/telefono/**", "/api/v1/venta/**").hasAnyRole("Vendedor", "Comprador")
                .requestMatchers("/api/v1/tienda/**", "/api/v1/producto/**", "/api/v1/productoCategoria/**").hasRole("Vendedor")
                .requestMatchers("/api/v1/empleado/**", "/api/v1/tiendaProducto/**").hasRole("Vendedor")
                .requestMatchers("/api/v1/cliente/**", "/api/v1/resenia/**").hasRole("Comprador")
                .requestMatchers("/api/v1/**").hasRole("Administrador")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /*@Bean
    UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password(passwordEncoder().encode("admin")).roles().build());
        return manager;
    }*/

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    } //Encripta la contraseña

    /*public static void main(String[] args) {
        BCryptPasswordEncoder bcryptp = new BCryptPasswordEncoder();
        System.out.println("Pass: " + bcryptp.encode("JuanisJoplin4000"));
    }*/
}
