package xyz.prokosna.multiple_mybatis_sample.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(
  basePackages = "xyz.prokosna.multiple_mybatis_sample.domain.repository.db1",
  sqlSessionTemplateRef = Db1Config.SESSION_TEMPLATE
)
@RequiredArgsConstructor
public class Db1Config {
  public static final String TRANSACTION_MANAGER = "db1TransactionManager";
  static final String SESSION_TEMPLATE = "db1SessionTemplate";
  private static final String DATASOURCE = "db1DataSource";
  private static final String SESSION_FACTORY = "db1SessionFactory";

  private final ApplicationContext context;

  @Value("${datasource.db1.mybatis.config-location}")
  private String configLocation;

  @Value("${datasource.db1.mybatis.mapper-locations}")
  private String mapperLocations;

  @Bean(name = DATASOURCE)
  @ConfigurationProperties(prefix = "datasource.db1.hikari")
  public DataSource dataSource() {
    return new HikariDataSource();
  }

  @Bean(name = TRANSACTION_MANAGER)
  public PlatformTransactionManager platformTransactionManager(
      @Qualifier(DATASOURCE) DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean(name = SESSION_FACTORY)
  public SqlSessionFactory sqlSessionFactory(@Qualifier(DATASOURCE) DataSource dataSource)
      throws Exception {
    val bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    bean.setConfigLocation(context.getResource(configLocation));
    bean.setMapperLocations(context.getResources(mapperLocations));
    return bean.getObject();
  }

  @Bean(name = SESSION_TEMPLATE)
  public SqlSessionTemplate sqlSessionTemplate(
      @Qualifier(SESSION_FACTORY) SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }
}
