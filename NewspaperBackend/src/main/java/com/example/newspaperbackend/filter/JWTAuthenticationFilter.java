package com.example.newspaperbackend.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.newspaperbackend.Module.Person;
import com.example.newspaperbackend.config.AuthenticationConfigConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@RequiredArgsConstructor
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        System.out.println("ATTEMPT");

        try {
            Person creds = new ObjectMapper()
                    .readValue(request.getInputStream(),  Person.class);
            System.out.println(creds.getUsername() + " :EMAIL");
            System.out.println(creds.getPassword() + " :PASSWORD");


            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword()
                    ));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {

        System.out.println("SUCCESS");

        User user = (User) auth.getPrincipal();

        System.out.println(user.getAuthorities()+ "         " + user.getUsername());
        Algorithm algorithm = Algorithm.HMAC512(AuthenticationConfigConstants.SECRET.getBytes());
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withClaim("role",  auth.getAuthorities().iterator().next().getAuthority())
                .withExpiresAt(new Date(System.currentTimeMillis() + AuthenticationConfigConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(AuthenticationConfigConstants.SECRET.getBytes()));
        response.setHeader(AuthenticationConfigConstants.HEADER_STRING, access_token);

        //START - SENDING JWT AS A BODY
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
                "{\"" + AuthenticationConfigConstants.HEADER_STRING + "\":\"" + AuthenticationConfigConstants.TOKEN_PREFIX + access_token + "\"}"
        );
        //END - SENDING JWT AS A BODY
    }
}
