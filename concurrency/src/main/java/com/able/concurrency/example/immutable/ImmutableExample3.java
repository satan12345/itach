package com.able.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Map;

public class ImmutableExample3 {

    private  static ImmutableList<Integer> LIST = ImmutableList.of(1, 2, 3);
    private  static ImmutableSet<Integer> SET = ImmutableSet.copyOf(LIST);
    private  static ImmutableMap<Integer,Integer> MAP=ImmutableMap.<Integer,Integer>builder().put(1,2).put(3,5).build();

    public static void main(String[] args) {
    		//LIST.add(4);
        System.out.println(MAP);
    }
}
