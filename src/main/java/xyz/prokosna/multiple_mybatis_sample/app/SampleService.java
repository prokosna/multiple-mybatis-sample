package xyz.prokosna.multiple_mybatis_sample.app;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.prokosna.multiple_mybatis_sample.domain.model.Item;
import xyz.prokosna.multiple_mybatis_sample.domain.model.User;
import xyz.prokosna.multiple_mybatis_sample.domain.repository.db1.ItemRepository;
import xyz.prokosna.multiple_mybatis_sample.domain.repository.db2.UserRepository;
import xyz.prokosna.multiple_mybatis_sample.infra.config.Db1Config;
import xyz.prokosna.multiple_mybatis_sample.infra.config.Db2Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class SampleService {
  private final ItemRepository itemRepository;
  private final UserRepository userRepository;

  @Transactional(value = Db1Config.TRANSACTION_MANAGER)
  public List<Item> runDb1Sample() {
    itemRepository.create();
    val rand = ThreadLocalRandom.current();
    val types = Arrays.asList("CB", "GSR", "ZXR");
    val cc = Arrays.asList("250", "400", "650");
    Collections.shuffle(types);
    Collections.shuffle(cc);
    val item1 = new Item(rand.nextLong(1000000), types.get(0) + cc.get(0), rand.nextInt(1000000));
    val item2 = new Item(rand.nextLong(1000000), types.get(1) + cc.get(1), rand.nextInt(1000000));
    val item3 = new Item(rand.nextLong(1000000), types.get(2) + cc.get(2), rand.nextInt(1000000));
    itemRepository.save(item1);
    itemRepository.save(item2);
    itemRepository.save(item3);
    val items = itemRepository.getAll();
    val list = new ArrayList<Item>();
    for (val item : items) {
      list.add(item);
    }
    return list;
  }

  @Transactional(value = Db2Config.TRANSACTION_MANAGER)
  public List<User> runDb2Sample() {
    userRepository.create();
    val rand = ThreadLocalRandom.current();
    val firstNames = Arrays.asList("Momoka", "Risa", "Haru", "Chie");
    val lastNames = Arrays.asList("Sakurai", "Matoba", "Yuuki", "Sasaki");
    Collections.shuffle(firstNames);
    Collections.shuffle(lastNames);
    val user1 = new User(rand.nextLong(10000000), firstNames.get(0), lastNames.get(0));
    val user2 = new User(rand.nextLong(10000000), firstNames.get(1), lastNames.get(1));
    val user3 = new User(rand.nextLong(10000000), firstNames.get(2), lastNames.get(2));
    userRepository.save(user1);
    userRepository.save(user2);
    userRepository.save(user3);
    val users = userRepository.getAll();
    val list = new ArrayList<User>();
    for (val user : users) {
      list.add(user);
    }
    return list;
  }

  public void clearAll() {
    itemRepository.clear();
    userRepository.clear();
  }
}
