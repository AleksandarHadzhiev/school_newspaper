package com.example.newspaperbackend.config;

import com.example.newspaperbackend.filter.JWTAuthenticationFilter;
import com.example.newspaperbackend.filter.JWTAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, AuthenticationConfigConstants.SIGN_UP_URL).permitAll()
                //ROLE BASED AUTHENTICATION START
                .antMatchers("/**").permitAll()
                .antMatchers("/article/get/all/{date}").permitAll()
                .antMatchers("/article/createArticle").hasAnyAuthority("USER")
                .antMatchers("/user/sign/in/{username}").hasAnyAuthority("USER")
                .antMatchers("/article/get/article/{id}").hasAnyAuthority("USER")
                .antMatchers("/article/createArticle").hasAnyAuthority("USER")
                .antMatchers("/article/edit").hasAnyAuthority("USER")
                .antMatchers("/article/get/all/forUser/{id}").hasAnyAuthority("USER")
                .antMatchers("/user/edit/information").hasAnyAuthority("USER")
                .antMatchers(HttpMethod.DELETE,"/user/delete/profile/{id}").hasAnyAuthority("USER")
                .antMatchers(HttpMethod.DELETE,"/article/removeArticle/{id}").hasAnyAuthority("USER")
                //ROLE BASED AUTHENTICATION END
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
