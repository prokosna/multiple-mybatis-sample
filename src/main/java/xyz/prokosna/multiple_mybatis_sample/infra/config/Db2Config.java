package xyz.prokosna.multiple_mybatis_sample.infra.config;

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

import javax.sql.DataSource;

@Configuration
@MapperScan(
  basePackages = "xyz.prokosna.multiple_mybatis_sample.domain.repository.db2",
  sqlSessionTemplateRef = Db2Config.SESSION_TEMPLATE
)
@RequiredArgsConstructor
public class Db2Config {
  public static final String TRANSACTION_MANAGER = "db2TransactionManager";
  static final String SESSION_TEMPLATE = "db2SessionTemplate";
  private static final String DATASOURCE = "db2DataSource";
  private static final String SESSION_FACTORY = "db2SessionFactory";

  private final ApplicationContext context;

  @Value("${datasource.db2.mybatis.config-location}")
  private String configLocation;

  @Value("${datasource.db2.mybatis.mapper-locations}")
  private String mapperLocations;

  @Bean(name = DATASOURCE)
  @ConfigurationProperties(prefix = "datasource.db2.hikari")
  public DataSource dataSource() {
    return new HikariDataSource();
  }

  @Bean(name = TRANSACTION_MANAGER)
  public DataSourceTransactionManager dataSourceTransactionManager(
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
