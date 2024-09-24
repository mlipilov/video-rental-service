package com.casumo.videorentalservice.presentation;

import static com.casumo.videorentalservice.utils.DataReader.readInboundJson;
import static com.casumo.videorentalservice.utils.DataReader.readOutboundJson;
import static com.casumo.videorentalservice.utils.RequestUtils.sendPost;
import static org.assertj.core.api.Assertions.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

import com.casumo.videorentalservice.BaseIntegrationTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

class RentMovieControllerIT extends BaseIntegrationTest {

  private static final String RENTAL_URI = "api/v1/movies/rent";
  private static final String RENTAL_RQ = "rent-movie-rq.json";
  private static final String RENTAL_RS = "movie-rented-rs.json";

  @Test
  @SneakyThrows
  @Sql(scripts = "classpath:db/add-rental-testing-dataset.sql")
  void whenRentMovies_ThenSuccessfullyRent() {
    final String requestUrl = getLocalhostUrl(RENTAL_URI);
    final RestTemplate rt = getRestTemplate();
    final String rq = readInboundJson(RENTAL_RQ);
    final String expected = readOutboundJson(RENTAL_RS);

    queryExecutionListener.initCounter();
    final ResponseEntity<String> response = sendPost(rt, requestUrl, rq);

    assertThat(response).isNotNull();
    assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    //fetched customer and movies
    assertThat(queryExecutionListener.getSelectCount()).isEqualTo(2);
    //rented 2 movies
    assertThat(queryExecutionListener.getInsertCount()).isEqualTo(2);
    //updated user balance
    assertThat(queryExecutionListener.getUpdateCount()).isEqualTo(1);
    assertEquals(expected, response.getBody(), false);
  }
}