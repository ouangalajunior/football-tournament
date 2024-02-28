package cas.rad.springboot.footballtournament.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/tournament/create").hasRole("ADMIN"))
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/tournament/edit/{id}").hasRole("ADMIN"))
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/tournament/delete/{id}").hasRole("ADMIN"))
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/tournament/{tournamentId}/teams/add").hasRole("ADMIN"))
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/tournament/{tournamentId}/teams/remove/{teamId}").hasRole("ADMIN"))
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/team/create").hasRole("ADMIN"))
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/team/edit/{id}").hasRole("ADMIN"))
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/team/delete/{id}").hasRole("ADMIN"))
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/tournament/{tournamentId}/matches/create").hasRole("ADMIN"))
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/tournament/{tournamentId}/matches/edit/{id}").hasRole("ADMIN"))
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/tournament/{tournamentId}/matches/delete/{id}").hasRole("ADMIN"))
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/tournament/{tournamentId}/matches/add-score/{id}").hasRole("ADMIN"))
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("osee")
                .password(passwordEncoder().encode("Geneva2024##"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("cas-rad")
                .password(passwordEncoder().encode("Geneva2024##"))
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
