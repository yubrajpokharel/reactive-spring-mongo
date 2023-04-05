package com.yubraj.reactive.repo;

import com.yubraj.reactive.domain.Rating;import com.yubraj.reactive.domain.Users;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RatingRepository extends ReactiveMongoRepository<Rating, String> {
  Mono<Rating> findByUserId(String userId);
}
