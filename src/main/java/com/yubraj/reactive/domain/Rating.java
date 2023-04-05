package com.yubraj.reactive.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("rating")
@Data
public class Rating {
  @Id private String id;

  @NotBlank private String userId;

  @NotBlank
  private String movieId;

  @Min(1)
  @Max(10)
  private int rating;
}
