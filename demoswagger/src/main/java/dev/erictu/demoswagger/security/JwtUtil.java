package dev.erictu.demoswagger.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import java.util.Date;

/**
 * Jwt util
 *
 * @author eric.t.tu
 * @date 2019/3/14 15:25
 */
public class JwtUtil {
    private final static String BASE64_ENCODED_SECRET_KEY = "base64EncodedSecretKey";

    /**
     * 过期时间60s
     */
    private final static long TOKEN_EXT = 1000 * 60;

    public static String getToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .claim("roles", "user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXT))
                .signWith(SignatureAlgorithm.HS256, BASE64_ENCODED_SECRET_KEY)
                .compact();
    }

    public static void checkToken(String token) throws ServletException {
        try {
            Jwts.parser().setSigningKey(BASE64_ENCODED_SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException ee) {
            throw new ServletException("token expired");
        } catch (Exception e) {
            throw new ServletException("other token exception");
        }
    }
}
