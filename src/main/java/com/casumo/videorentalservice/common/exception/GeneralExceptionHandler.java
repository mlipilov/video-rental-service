package com.casumo.videorentalservice.common.exception;

import com.casumo.videorentalservice.model.response.ErrorRs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GeneralExceptionHandler {

  /**
   * internalServerError is used for simplicity
   * @param ex ex
   * @return response entity
   */
  @ExceptionHandler(RuntimeException.class)
  ResponseEntity<ErrorRs> handleRuntimeException(final RuntimeException ex) {
    final ErrorRs errorRs = new ErrorRs();
    errorRs.setErrorMessage(ex.getMessage());
    log.error("Error during processing request: ", ex);
    return ResponseEntity.internalServerError().body(errorRs);
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<ErrorRs> handleException(final Exception ex) {
    final ErrorRs errorRs = new ErrorRs();
    errorRs.setErrorMessage("Something went wrong...");
    log.error("Error during processing request: ", ex);
    return ResponseEntity.internalServerError().body(errorRs);
  }
}
