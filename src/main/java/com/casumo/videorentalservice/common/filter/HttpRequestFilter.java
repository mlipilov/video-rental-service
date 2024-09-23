package com.casumo.videorentalservice.common.filter;

import static com.casumo.videorentalservice.common.constant.MdcKeyConstants.USER_ID;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.casumo.videorentalservice.common.annotation.RequestFilter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.slf4j.MDC;

@RequestFilter
public class HttpRequestFilter implements Filter {

  @Override
  public void doFilter(
      final ServletRequest servletRequest,
      final ServletResponse servletResponse,
      final FilterChain filterChain) {
    try {
      putUserIdToMdc(servletRequest, servletResponse, filterChain);
    } catch (IOException | ServletException e) {
      throw new RuntimeException(e);
    } finally {
      MDC.clear();
    }
  }

  private static void putUserIdToMdc(
      final ServletRequest servletRequest,
      final ServletResponse servletResponse,
      final FilterChain filterChain
  ) throws IOException, ServletException {
    final String userId = ((HttpServletRequest) servletRequest).getHeader(AUTHORIZATION);
    if (isBlank(userId)) {
      throw new RuntimeException();
    } else {
      MDC.put(USER_ID, userId);
      filterChain.doFilter(servletRequest, servletResponse);
    }
  }
}
