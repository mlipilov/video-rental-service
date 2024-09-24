package com.casumo.videorentalservice.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Files;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.util.ResourceUtils;

@UtilityClass
public class DataReader {

  public record Param(String key, String value) {

  }

  private static final String INBOUND_PATH = "inbound";
  private static final String OUTBOUND_PATH = "outbound";

  private static final ObjectMapper mapper;

  static {
    mapper = new ObjectMapper();
    mapper.findAndRegisterModules();
  }

  @SneakyThrows
  public static <T> T readObj(@NonNull String path, @NonNull Class<T> objType) {
    final String json = readFile(path);
    return mapper.readValue(json, objType);
  }

  @SneakyThrows
  public static <T> T readObj(@NonNull String path, @NonNull TypeReference<T> typeReference) {
    final String json = readFile(path);
    return mapper.readValue(json, typeReference);
  }

  @SneakyThrows
  public static String readOutboundJson(@NonNull String path) {
    return readFile(OUTBOUND_PATH + File.separator + path);
  }

  @SneakyThrows
  public static String readInboundJson(@NonNull String path) {
    return readFile(INBOUND_PATH + File.separator + path);
  }

  @SneakyThrows
  public static <T> T readObjWithParam(@NonNull String path, @NonNull Class<T> objType,
      Param... params) {
    String json = readFile(path);
    for (Param param : params) {
      json = json.replace(param.key, param.value);
    }
    return mapper.readValue(json, objType);
  }

  public static <T> T readInboundObj(@NonNull String path, @NonNull Class<T> objType) {
    return readObj(INBOUND_PATH + File.separator + path, objType);
  }

  public static <T> T readInboundObj(@NonNull String path, @NonNull TypeReference<T> objType) {
    return readObj(INBOUND_PATH + File.separator + path, objType);
  }

  public static <T> T readOutboundObj(@NonNull String path, @NonNull Class<T> objType) {
    return readObj(OUTBOUND_PATH + File.separator + path, objType);
  }

  public static <T> T readOutboundObj(@NonNull String path, @NonNull TypeReference<T> objType) {
    return readObj(OUTBOUND_PATH + File.separator + path, objType);
  }

  public static <T> T readOutboundObjWithParam(@NonNull String path, @NonNull Class<T> objType,
      Param... params) {
    return readObjWithParam(OUTBOUND_PATH + File.separator + path, objType, params);
  }

  private static String readFile(String path) throws Exception {
    return new String(Files.readAllBytes(ResourceUtils.getFile("classpath:" + path).toPath()));
  }
}
