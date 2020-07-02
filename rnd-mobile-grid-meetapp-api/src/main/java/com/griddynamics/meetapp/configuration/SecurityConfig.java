package com.griddynamics.meetapp.configuration;

import com.griddynamics.meetapp.security.AuthenticationEntryPointImpl;
import com.griddynamics.meetapp.security.JsonAuthenticationSuccessHandler;
import com.griddynamics.meetapp.security.JsonUsernamePasswordAuthenticationFilter;
import com.griddynamics.meetapp.security.Role;
import com.griddynamics.meetapp.security.oauth.google.GoogleOAuthAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsAuthenticationProvider")
    private AuthenticationProvider userDetailsAuthenticationProvider;

    @Autowired
    @Qualifier("googleOauthAuthenticationProvider")
    private AuthenticationProvider googleAuthenticationProvider;

    @Bean
    public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter() throws Exception {
        JsonUsernamePasswordAuthenticationFilter bean =
                new JsonUsernamePasswordAuthenticationFilter();
        bean.setAuthenticationManager(authenticationManager());
        bean.setAuthenticationSuccessHandler(jsonAuthenticationSuccessHandler());
        return bean;
    }

    @Bean
    public GoogleOAuthAuthenticationFilter googleOAuthAuthenticationFilter() throws Exception {
        GoogleOAuthAuthenticationFilter bean =
                new GoogleOAuthAuthenticationFilter();
        bean.setAuthenticationManager(authenticationManager());
        bean.setAuthenticationSuccessHandler(jsonAuthenticationSuccessHandler());
        return bean;
    }

    @Bean
    public JsonAuthenticationSuccessHandler jsonAuthenticationSuccessHandler() {
        return new JsonAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationEntryPointImpl authenticationEntryPointImpl() {
        return new AuthenticationEntryPointImpl();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(googleAuthenticationProvider);
        auth.authenticationProvider(userDetailsAuthenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception { //TODO remove before prod
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/h2/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // fix for /h2
        http.headers().frameOptions().disable();

        // disabling CSRF
        http.csrf().disable();

        // setting auth entrypoint for correct 401 error messaging
        http.httpBasic().authenticationEntryPoint(authenticationEntryPointImpl());

        // setting user & admin authorities
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/api/v1/login").permitAll()
                .mvcMatchers(HttpMethod.POST, "/api/v1/login/oauth/google").permitAll()
                .mvcMatchers(HttpMethod.POST, "/api/v1/register").permitAll()
                .mvcMatchers(HttpMethod.GET, "/api/v1/events").permitAll()
                .mvcMatchers(HttpMethod.POST, "/**").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name(), Role.GRID.name(), Role.SCANNER.name())
                .mvcMatchers(HttpMethod.PUT, "/**").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name(), Role.GRID.name(), Role.SCANNER.name())
                .mvcMatchers(HttpMethod.GET, "/**").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name(), Role.GRID.name(), Role.SCANNER.name())
                .anyRequest().authenticated();

        // set JSON auth filter
        // instead of URL-encoded auth filter
        http.addFilterAt(jsonUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(googleOAuthAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
