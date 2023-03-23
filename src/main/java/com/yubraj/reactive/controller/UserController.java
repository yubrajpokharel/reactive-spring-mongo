package com.yubraj.reactive.controller;

import com.yubraj.reactive.domain.Users;
import com.yubraj.reactive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping
  public Flux<Users> getAllPersons() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public Mono<Users> getPersonById(@PathVariable String id) {
    return userService.getUserById(id);
  }

  @PostMapping
  public Mono<Users> createPerson(@RequestBody Users user) {
    return userService
        .createUser(user)
        .onErrorResume(
            e ->
                Mono.error(
                    new ResponseStatusException(
                        HttpStatus.CONFLICT, "Email already been used: " + user.getEmail(), e)));
  }

  @PutMapping("/{id}")
  public Mono<Users> updatePerson(@PathVariable String id, @RequestBody Users user) {
    return userService
        .updateUser(id, user)
        .onErrorResume(
            e ->
                Mono.error(
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User doesn't exists with id:" + id)));
  }

  @DeleteMapping("/{id}")
  public Mono<Void> deletePerson(@PathVariable String id) {
    return userService.deleteUser(id);
  }
}
