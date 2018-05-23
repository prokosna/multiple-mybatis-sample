package xyz.prokosna.multiple_mybatis_sample.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class User {
  @NonNull private Long id;
  @NonNull private String firstName;
  @NonNull private String lastName;
}
