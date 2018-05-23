package xyz.prokosna.multiple_mybatis_sample.infra.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.prokosna.multiple_mybatis_sample.app.SampleService;
import xyz.prokosna.multiple_mybatis_sample.domain.model.Item;
import xyz.prokosna.multiple_mybatis_sample.domain.model.User;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleResource {
  private final SampleService sampleService;

  @GetMapping("/db1")
  public List<Item> db1() {
    return sampleService.runDb1Sample();
  }

  @GetMapping("/db2")
  public List<User> db2() {
    return sampleService.runDb2Sample();
  }

  @GetMapping("/clear")
  public void clear() {
    sampleService.clearAll();
  }
}
