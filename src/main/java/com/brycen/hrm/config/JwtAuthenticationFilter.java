package com.brycen.hrm.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.brycen.hrm.model.security.CurrentUserService;


public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private CurrentUserService currentUserService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Lấy token từ request
		String jwt = getJwtFromRequest(request);
//		
		if(jwt == null ) {
			SecurityContextHolder.clearContext();
		}
		
		// Kiểm tra jwt có dữ liệu và mã hóa chuỗi jwt
		 if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
			// Lấy id user từ chuỗi jwt
             String username = jwtTokenProvider.getUserFromJWT(jwt);
             // Lấy thông tin người dùng từ id
             UserDetails userDetails = currentUserService.loadUserByUsername(username);
             
             
             // Kiểm tra
             if(userDetails != null) {
            	 UsernamePasswordAuthenticationToken
                 authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		         authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
		         SecurityContextHolder.getContext().setAuthentication(authentication);
		         
             }
		 }
		 filterChain.doFilter(request, response);
	}
	
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        // Kiểm tra xem header Authorization có chứa thông tin jwt không
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
