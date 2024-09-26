package com.casumo.videorentalservice;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;
import static org.springframework.test.context.jdbc.SqlMergeMode.MergeMode.MERGE;

import com.casumo.videorentalservice.business.rental.service.impl.CurrentDateService;
import com.casumo.videorentalservice.utils.VideoRentalQueryExecutionListener;
import java.text.MessageFormat;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles(profiles = "integration")
@SqlMergeMode(MERGE)
@Sql(
    scripts = {"classpath:db/truncate-tables.sql"},
    executionPhase = BEFORE_TEST_METHOD,
    config = @SqlConfig(transactionMode = ISOLATED))
public class BaseIntegrationTest {

  private static final String POSTGRES_IMAGE = "postgres:latest";
  private static final String DATASOURCE_URL = "DATASOURCE_URL";
  private static final String DB_USERNAME = "DB_USERNAME";
  private static final String DB_PASSWORD = "DB_PASSWORD";
  private static final String LOCALHOST_URL = "http://localhost:{0}/{1}";

  @LocalServerPort
  protected int port;
  @Autowired
  protected VideoRentalQueryExecutionListener queryExecutionListener;
  @Autowired
  private RestTemplateBuilder restTemplateBuilder;
  @SpyBean
  protected CurrentDateService currentDateService;

  protected static final PostgreSQLContainer<?> POSTGRES_SQL_CONTAINER;

  protected String getLocalhostUrl(@NonNull final String uri) {
    return MessageFormat.format(LOCALHOST_URL, String.valueOf(port), uri);
  }

  protected RestTemplate getRestTemplate() {
    return restTemplateBuilder
        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
        .build();
  }

  static {
    POSTGRES_SQL_CONTAINER = new PostgreSQLContainer<>(POSTGRES_IMAGE);
    POSTGRES_SQL_CONTAINER.start();
  }

  @DynamicPropertySource
  static void registerDynamicProperties(final DynamicPropertyRegistry registry) {
    registry.add(DATASOURCE_URL, POSTGRES_SQL_CONTAINER::getJdbcUrl);
    registry.add(DB_USERNAME, POSTGRES_SQL_CONTAINER::getUsername);
    registry.add(DB_PASSWORD, POSTGRES_SQL_CONTAINER::getPassword);
  }
}
