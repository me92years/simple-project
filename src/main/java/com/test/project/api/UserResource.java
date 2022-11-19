package com.test.project.api;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.project.model.user.User;
import com.test.project.provider.JwtTokenProvider;
import com.test.project.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserResource {
	private final JwtTokenProvider jwtTokenProvider;
	private final UserService userService;
	private final AuthenticationManager authenticationManager;

	@PostMapping("/api/user/check")
	public Long user_check(@RequestBody User user) {
		return userService.findUserByUsername(user.getUsername()).getId();
	}

	@PostMapping("/api/user/add")
	public Long user_add(@RequestBody User user) {
		return userService.addUser(user);
	}

	@PostMapping("/api/user/auth")
	public String user_auth(@RequestBody User user) {
		try {
			Authentication authentication = authenticationManager.authenticate(returnAuthenticationToken(user));
			return jwtTokenProvider.returnAccessToken(authentication);
		} catch (Exception e) {
			throw new BadCredentialsException("자격 증명 실패");
		}

	}

	private Authentication returnAuthenticationToken(User user) {
		return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
	}

}
