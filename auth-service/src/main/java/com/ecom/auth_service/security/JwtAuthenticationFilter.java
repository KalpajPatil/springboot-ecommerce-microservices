//package com.ecom.auth_service.security;
//
//import java.io.IOException;
//import java.util.List;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.ecom.auth_service.repository.UserRepository;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//	private final Jwtutil jwtutil;
//	private final UserRepository userRepository;
//
//	public JwtAuthenticationFilter(Jwtutil jwtutil, UserRepository userRepository) {
//		this.jwtutil = jwtutil;
//		this.userRepository = userRepository;
//	}
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//
//		final String authHeader = request.getHeader("Authorization");
//		final String jwt;
//		final String username;
//
//		// 1. Check if the Authorization header exists and starts with "Bearer "
//		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//			filterChain.doFilter(request, response); // just continue → no JWT found
//			return;
//		}
//
//		// 2. Extract token from header
//		jwt = authHeader.substring(7); // remove "Bearer "
//
//		// 3. Extract username from JWT
//		username = jwtutil.extractUsername(jwt);
//
//		// 4. Check if user is not already authenticated in the SecurityContext
//		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//			// 5. Validate the token
//			if (jwtutil.validateToken(jwt)) {
//
//				// 6. Load user from DB (optional, can be skipped if roles are inside token)
//				var user = userRepository.findByUsername(username).orElse(null);
//
//				if (user != null) {
//					// 7. Build an Authentication object
//					SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+user.getRole().toUpperCase());
//					List.of(authority).forEach(x -> System.out.println(x));
//					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//							user.getUsername(), null, List.of(authority) // could set roles/authorities here
//					);
//
//					// 8. Attach request details
//					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//					// 9. Save Authentication object into SecurityContext
//					SecurityContextHolder.getContext().setAuthentication(authToken);
//				}
//			}
//		}
//
//		// 10. Continue the filter chain
//		filterChain.doFilter(request, response);
//	}
//}