package com.yubraj.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
public class ReactiveSpringMongoApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReactiveSpringMongoApplication.class, args);
  }

}
