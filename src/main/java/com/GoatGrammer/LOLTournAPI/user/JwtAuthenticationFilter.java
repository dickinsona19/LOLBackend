package com.GoatGrammer.LOLTournAPI.user;

import com.GoatGrammer.LOLTournAPI.user.UserClass.User;
import com.GoatGrammer.LOLTournAPI.user.UserClass.UserService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class JwtAuthenticationFilter implements Filter {
    @Autowired
    private JwtService jwtService; // Your JWT service for token validation
    @Autowired
    private com.GoatGrammer.LOLTournAPI.user.UserClass.UserService userService; // Your user service

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Get the cookies from the request
        Cookie[] cookies = httpRequest.getCookies();
        String token = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("authToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        // Validate the token
        if (token != null && jwtService.validateToken(token)) {
            String email = jwtService.extractEmail(token); // Extract user email or ID from the token
            com.GoatGrammer.LOLTournAPI.user.UserClass.User user = userService.getUserByEmail(email).orElse(null);
            if (user != null) {
                // Optionally set the user in the context if needed
                // SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
            }
        }

        chain.doFilter(request, response); // Continue the filter chain
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
