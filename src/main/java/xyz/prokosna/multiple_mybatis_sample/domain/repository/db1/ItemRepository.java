package xyz.prokosna.multiple_mybatis_sample.domain.repository.db1;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.stereotype.Repository;
import xyz.prokosna.multiple_mybatis_sample.domain.model.Item;

@Repository
@Mapper
public interface ItemRepository {
  void create();

  Cursor<Item> getAll();

  Item getById(Long id);

  void save(Item item);

  void clear();
}
