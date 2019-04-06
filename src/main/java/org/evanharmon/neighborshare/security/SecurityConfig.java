package org.evanharmon.neighborshare.security;

import org.evanharmon.neighborshare.models.User;
import org.evanharmon.neighborshare.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.management.MXBean;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
                .antMatchers("/", "/register", "/login", "/css/*", "/js/*", "/img/logo.png").permitAll()
                .antMatchers("/view", "/view/*", "/item", "/item/*", "/user", "/user/*", "/files").hasAuthority("USER")

            .and()

            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/view")

            .and()

            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")

            ;
    }

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(new UserDetailsService() {
                    @Override
                    public UserDetails loadUserByUsername(String username)
                            throws UsernameNotFoundException {
                        return userRepository.findByUsername(username);
                    }
                })
                .passwordEncoder(User.passwordEncoder)
        ;
    }

}