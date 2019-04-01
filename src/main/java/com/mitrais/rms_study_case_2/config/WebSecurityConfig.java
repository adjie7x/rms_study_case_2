package com.mitrais.rms_study_case_2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.
//                jdbcAuthentication()
//                .usersByUsernameQuery(usersQuery)
//                .authoritiesByUsernameQuery(rolesQuery)
//                .dataSource(dataSource)
//                .passwordEncoder(bCryptPasswordEncoder);

        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] allowedUrl = {"/login"};


        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/login","/template/*").permitAll()
                    .antMatchers("/auth/admin/*").hasRole("ADMIN")
                    .antMatchers("/auth/*").hasAnyRole("ADMIN","USER")
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error=true")
//                    .defaultSuccessUrl("/")
                    .successForwardUrl("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                .and()
                .logout()
                    .logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/bower_components/**","/dist/**","/plugins/**");
    }
}
