package SKRUM.TicketGuru.auth;

import com.fasterxml.jackson.databind.ObjectMapper;

import SKRUM.TicketGuru.domain.exceptions.CustomForbiddenException;
import SKRUM.TicketGuru.domain.response.ErrorRes;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final ObjectMapper mapper;

    public JwtAuthorizationFilter(JwtUtil jwtUtil, ObjectMapper mapper) {
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String accessToken = jwtUtil.resolveToken(request);
            if (accessToken == null) {
                filterChain.doFilter(request, response);
                return;
            }
            System.out.println("token : " + accessToken);
            Claims claims = jwtUtil.resolveClaims(request);

            if (claims != null & jwtUtil.validateClaims(claims)) {
                String username = claims.getSubject();
                System.out.println("username : " + username);
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, "",
                        new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.FORBIDDEN,
                    "Token you entered is incorrect, try to get a new one at /api/auth/login");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            String errorResponseJson = mapper.writeValueAsString(errorResponse);
            response.getWriter().write(errorResponseJson);
            response.getWriter().flush();
            return;
        }
        filterChain.doFilter(request, response);
    }
}