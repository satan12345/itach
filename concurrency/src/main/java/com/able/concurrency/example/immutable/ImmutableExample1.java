package com.able.concurrency.example.immutable;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

public class ImmutableExample1 {

    private  static Map<Integer,Integer> map= Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(2,3);
        map.put(4,5);
        Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {

    }
}
