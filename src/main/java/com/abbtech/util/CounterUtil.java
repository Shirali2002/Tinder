package com.abbtech.util;

import java.util.HashMap;
import java.util.Map;

public class CounterUtil {

  private static final Map<Integer, Integer> counterMap = new HashMap<>(); // id , count

  public static void mapNext(int id) {
    counterMap.putIfAbsent(id, 0);
    Integer oldId = counterMap.get(id);
    counterMap.put(id, oldId+1);
  }

  public static int getId (int id) {
    counterMap.putIfAbsent(id, 0);
    return counterMap.get(id);
  }

  public static void removeById (int id) {
    counterMap.remove(id);
  }

}