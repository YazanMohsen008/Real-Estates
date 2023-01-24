package com.informationengineering.internetapplicationproject.Filters;

import com.informationengineering.internetapplicationproject.Models.User;
import com.informationengineering.internetapplicationproject.Services.UserService;
import com.informationengineering.internetapplicationproject.Utils.TokenHandler;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    TokenHandler tokenHandler;
    @Autowired
    UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("Authorization"))
                    token = cookie.getValue();
            }
            if (token != null && !token.equals("")) {
                String username = tokenHandler.getUsername(token);
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    User user = userService.getUser(username);
                    if (tokenHandler.validateToken(token, user.getUsername())) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));//TODO ??
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
