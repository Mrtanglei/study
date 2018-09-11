package org.seckill;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class tt {

    public static void main(String[] args) {
        Float f = 422.77f;
        String s = null;
        List<Integer> list1 = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            list1.add(i);
        }
        System.out.println("list1 =" + list1.size());
        List<Integer> list2 = list1.parallelStream().map(i -> {
            return i;
        }).collect(Collectors.toList());
        System.out.println("list2 =" + list2.size());
        List<Integer> list3 = list2.parallelStream().map(i -> {
            return i;
        }).collect(Collectors.toList());
        System.out.println("list3 =" + list2.size());
    }
}
