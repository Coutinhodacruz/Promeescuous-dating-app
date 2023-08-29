package africa.semicolon.promeescuous.security;

import africa.semicolon.promeescuous.security.filters.PromiscuousAuthenticationFilter;
import africa.semicolon.promeescuous.security.filters.PromiscuousAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        var authenticationFilter = new PromiscuousAuthenticationFilter(authenticationManager);
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(c->c.sessionCreationPolicy(STATELESS))
                .addFilterBefore(new PromiscuousAuthorizationFilter(), PromiscuousAuthenticationFilter.class)
                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(c->c.requestMatchers(POST, "/api/v1/user")
                        .permitAll())
                .authorizeHttpRequests(c->c.requestMatchers(POST,"/login")
                        .permitAll())
                .authorizeHttpRequests(c->c.anyRequest().authenticated())
                .build();
    }
}
