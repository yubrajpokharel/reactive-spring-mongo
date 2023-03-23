package com.yubraj.reactive.repo;

import com.yubraj.reactive.domain.Users;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;import reactor.core.publisher.Mono;

@Repository
public interface RatingRepository extends ReactiveMongoRepository<Users, String> {
  Mono<Users> findByEmail(String email);
}
