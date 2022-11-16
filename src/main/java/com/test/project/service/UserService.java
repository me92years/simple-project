package com.test.project.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.project.model.user.User;
import com.test.project.model.user.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  public User findUserByUsername(String username) {
    return userMapper.findUserByUsername(username).get();
  }
  public Long addUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userMapper.addUser(user);
  }
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userMapper.findUserByUsername(username).get(); 
  }
  
}
