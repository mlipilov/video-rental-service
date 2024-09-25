package com.casumo.videorentalservice.presentation;

import static com.casumo.videorentalservice.utils.DataReader.readInboundJson;
import static com.casumo.videorentalservice.utils.DataReader.readOutboundJson;
import static com.casumo.videorentalservice.utils.RequestUtils.sendPatch;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

import com.casumo.videorentalservice.BaseIntegrationTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
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
    final ResponseEntity<String> response = sendPatch(rt, requestUrl, rq);

    assertThat(response).isNotNull();
    assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    assertEquals(expected, response.getBody(), false);

    //fetched customer and movies
    assertThat(queryExecutionListener.getSelectCount()).isEqualTo(2);
    //rented 2 movies
    assertThat(queryExecutionListener.getInsertCount()).isEqualTo(2);
    //updated user balance, update 2 movies rental count
    assertThat(queryExecutionListener.getUpdateCount()).isEqualTo(3);
  }

  @Test
  @SneakyThrows
  @Sql(scripts = "classpath:db/add-rental-when-insufficient-funds-dataset.sql")
  void givenUserWithInsufficientFunds_whenRentMovies_ThenReturnErrorRs() {
    final String requestUrl = getLocalhostUrl(RENTAL_URI);
    final RestTemplate rt = getRestTemplate();
    final String rq = readInboundJson(RENTAL_RQ);

    queryExecutionListener.initCounter();
    assertThatThrownBy(() -> sendPatch(rt, requestUrl, rq))
        .isInstanceOf(InternalServerError.class)
        .hasMessage("500 : \"{\"errorMessage\":\"Inefficient funds!\"}\"");

    //fetched customer and movies
    assertThat(queryExecutionListener.getSelectCount()).isEqualTo(2);
    //no inserts and updates due to validation error
    assertThat(queryExecutionListener.getInsertCount()).isEqualTo(0);
    assertThat(queryExecutionListener.getUpdateCount()).isEqualTo(0);
  }
}