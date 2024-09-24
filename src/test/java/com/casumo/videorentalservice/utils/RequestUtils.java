package com.casumo.videorentalservice.utils;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@UtilityClass
public class RequestUtils {

  private static final String CUSTOMER_ID = "54321";

  public static ResponseEntity<String> sendGet(final RestTemplate rt, final String url) {
    final RequestEntity<?> re = new RequestEntity<>(getAuthHeader(), GET, URI.create(url));
    return rt.exchange(re, String.class);
  }

  public static <T> ResponseEntity<String> sendPost(
      final RestTemplate rt,
      final String url,
      T body
  ) {
    final RequestEntity<T> re = new RequestEntity<>(body, getAuthHeader(), POST, URI.create(url));
    return rt.exchange(re, String.class);
  }

  public static <T> ResponseEntity<String> sendPatch(
      final RestTemplate rt,
      final String url,
      T body
  ) {
    final RequestEntity<T> re = new RequestEntity<>(body, getAuthHeader(), PATCH, URI.create(url));
    return rt.exchange(re, String.class);
  }

  private static HttpHeaders getAuthHeader() {
    final HttpHeaders headers = new HttpHeaders();
    headers.set(AUTHORIZATION, CUSTOMER_ID);
    headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);
    return headers;
  }
}
