package xyz.prokosna.multiple_mybatis_sample.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Item {
  @NonNull private Long id;
  @NonNull private String name;
  @NonNull private Integer price;
}
