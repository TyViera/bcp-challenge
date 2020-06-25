package com.bcp.bcpchallengebackend.util;

import com.google.gson.Gson;
import java.util.Map;

public class TestUtils {

  public static <T> T parseMapToObject(Map<?, ?> inputMap, Class<T> targetClass) {
    var gson = new Gson();
    var jsonElement = gson.toJsonTree(inputMap);
    return gson.fromJson(jsonElement, targetClass);
  }

  public static <K, V> Map<K, V> parseObjectToMap(Object inputObject) {
    var gson = new Gson();
    var json = gson.toJson(inputObject);
    return gson.fromJson(json, Map.class);
  }

}
