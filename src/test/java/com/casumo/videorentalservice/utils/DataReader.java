package com.casumo.videorentalservice.utils;

import static org.springframework.util.ResourceUtils.getFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Files;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DataReader {

  private static final String INBOUND_PATH = "inbound";
  private static final String OUTBOUND_PATH = "outbound";

  private static final ObjectMapper mapper;

  static {
    mapper = new ObjectMapper();
    mapper.findAndRegisterModules();
  }

  @SneakyThrows
  public static String readOutboundJson(@NonNull String path) {
    return readFile(OUTBOUND_PATH + File.separator + path);
  }

  @SneakyThrows
  public static String readInboundJson(@NonNull String path) {
    return readFile(INBOUND_PATH + File.separator + path);
  }

  private static String readFile(String path) throws Exception {
    return new String(Files.readAllBytes(getFile("classpath:" + path).toPath()));
  }
}
