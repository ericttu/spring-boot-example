package dev.erictu.demoswagger.security;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Jwt interceptor
 *
 * @author eric.t.tu
 * @date 2019/3/14 15:25
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            throw new ServletException("invalid Authorization header");
        }

        try {
            JwtUtil.checkToken(authHeader);
            return true;
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
}
