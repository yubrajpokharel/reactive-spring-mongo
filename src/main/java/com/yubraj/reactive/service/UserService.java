package com.yubraj.reactive.service;

import com.yubraj.reactive.domain.Users;
import com.yubraj.reactive.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public Flux<Users> getAllUsers() {
    return userRepository.findAll();
  }

  public Mono<Users> getUserById(String id) {
    return userRepository.findById(id);
  }

  public Mono<Users> createUser(Users user) {
    return userRepository
        .findByEmail(user.getEmail())
        .flatMap(existingUser -> Mono.error(new RuntimeException("Email already exists")))
        .switchIfEmpty(userRepository.save(user)).cast(Users.class);
  }

  public Mono<Users> updateUser(String id, Users users) {
    return userRepository
        .findById(id)
        .switchIfEmpty(Mono.error(new RuntimeException("User does not exists!")))
        .flatMap(
            existingPerson -> {
              existingPerson.setName(users.getName());
              existingPerson.setFirstName(users.getFirstName());
              existingPerson.setLastName(users.getLastName());
              existingPerson.setAge(users.getAge());
              existingPerson.setEmail(users.getEmail());
              return userRepository.save(existingPerson);
            });
  }

  public Mono<Void> deleteUser(String id) {
    return userRepository.deleteById(id);
  }
}
