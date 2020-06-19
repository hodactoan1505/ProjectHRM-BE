package com.brycen.hrm.config;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.brycen.hrm.model.security.CurrentUser;
import com.brycen.hrm.response.object.UserResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * [Description]:Class xử lý get dữ liệu trong token
 * [ Remarks ]:<br>
 * [Copyright]: Copyright (c) 2020<br>
 * 
 * @author Brycen VietNam Company
 * @version 1.0
 */
@Component
public class JwtTokenProvider {
	private final String JWT_SECRET = "brycen";
	private final int JWT_EXPIRATION = 6000000;
	
	public String generateToken(CurrentUser currentUser, UserResponse employeeResponse) {
		Date now = new Date();
		Date expiration = new Date(now.getTime() + JWT_EXPIRATION);

		return Jwts.builder()
					.setSubject(currentUser.getUsername())
					.setIssuedAt(now)
					.setExpiration(expiration)
					.signWith(SignatureAlgorithm.HS512, JWT_SECRET)
					.claim("employee", employeeResponse)
					.compact();
	}
	
	
	public String getUserFromJWT(String token) {
		Claims claims = Jwts.parser()
							.setSigningKey(JWT_SECRET)
							.parseClaimsJws(token)
							.getBody();
		
		return claims.getSubject();
	}
	
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
			return true;
//			exceptionJWT.setValidate(true);
//			exceptionJWT.setMessage("success");
		}catch (MalformedJwtException ex) {
//			exceptionJWT.setValidate(false);
//			exceptionJWT.setMessage("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
//			exceptionJWT.setValidate(false);
//			exceptionJWT.setMessage("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
//			exceptionJWT.setValidate(false);
//			exceptionJWT.setMessage("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
//			exceptionJWT.setValidate(false);
//			exceptionJWT.setMessage("JWT claims string is empty.");
        }
        return false;
	}
}
