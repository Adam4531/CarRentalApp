package pl.zetosoftware.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(
//        prePostEnabled = true,
//        securedEnabled = true,
//        jsr250Enabled = true)
public class AuthenticationConfiguration extends WebSecurityConfigurerAdapter {


    private final CurrentUserService currentUserService;
    private final SessionFilter sessionFilter;
    private final PasswordEncoder passwordEncoder;

    @Lazy
    public AuthenticationConfiguration(CurrentUserService currentUserService, SessionFilter sessionFilter, PasswordEncoder passwordEncoder) {
        this.currentUserService = currentUserService;
        this.sessionFilter = sessionFilter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(currentUserService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http
                .cors()
                .and()
                .csrf()
                .disable();

        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, exception) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                                exception.getMessage()))
                .and();

        http
                .authorizeRequests()
                .antMatchers("/api/login", "/api/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        http
                .addFilterBefore(sessionFilter, UsernamePasswordAuthenticationFilter.class);

    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

    ///////////////////poprzedni config do dzialajacej rejestracji
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .headers()
//                .frameOptions().disable()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/web/users/login","/web/users/register", "users/**")
//                .permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/cars/{id}")
//                .hasAnyRole("ROLE_ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/");
//    }


//    @Bean
//    public AuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder());
//        provider.setUserDetailsService(this.userDetailsService);
//        return provider;
//    }

