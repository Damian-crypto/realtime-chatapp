package com.zeus.chatapp.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zeus.chatapp.utils.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @Component is a fundamental annotation in Spring used to identify and register
// classes as Spring beans. These stereotype annotations provide semantic information
// about the roles of the annotated classes, making the code more expressive and
// helping the Spring container manage and organize components effectively.
// When you use these annotations, Spring automatically detects and registers the
// annotated classes during component scanning.
@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    
        final String HEADER_PREFIX = "Bearer ";
        final String AUTHORIZATION_HEADER = request.getHeader("Authorization");
        // System.out.println(AUTHORIZATION_HEADER);
        
        String jwt = null;
        String username = null;

        // If authorization token comes as a cookie
        // String AUTHORIZATION_HEADER = null;
        // if (request.getCookies() != null) {
        //     for (final Cookie cookie : request.getCookies()) {
        //         if (cookie.getName().equals("token")) {
        //             authorizationHeader = cookie.getValue();
        //         }
        //     }
        // }


        // if (AUTHORIZATION_HEADER != null) {
        //     jwt = AUTHORIZATION_HEADER;
        //     username = jwtUtil.extractUsername(jwt);
        // }

        if (AUTHORIZATION_HEADER != null && AUTHORIZATION_HEADER.startsWith(HEADER_PREFIX)) {
            jwt = AUTHORIZATION_HEADER.substring(HEADER_PREFIX.length());
            username = jwtUtil.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // System.out.println(userDetails);

            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                
                usernamePasswordAuthenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder
                    .getContext()
                    .setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
    
}
