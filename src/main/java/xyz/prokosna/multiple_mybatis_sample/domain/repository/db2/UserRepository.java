package xyz.prokosna.multiple_mybatis_sample.domain.repository.db2;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.stereotype.Repository;
import xyz.prokosna.multiple_mybatis_sample.domain.model.User;

@Repository
@Mapper
public interface UserRepository {
  void create();

  Cursor<User> getAll();

  User getById(Long id);

  void save(User user);

  void clear();
}
