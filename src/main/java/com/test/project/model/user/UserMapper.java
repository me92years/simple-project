package com.test.project.model.user;

import java.util.Optional;

public interface UserMapper {
  Optional<User> findUserByUsername(String username);
  Long addUser(User user);
}
