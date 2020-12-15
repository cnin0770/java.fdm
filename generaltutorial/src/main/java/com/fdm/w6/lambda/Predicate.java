package com.fdm.w6.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Predicate {
    iPredicate<String> predicate1 = new iPredicate<String>(){
        @Override
        public boolean test(String value) {
            return value.isEmpty();
        }
    };
    iPredicate<String> predicate2 = (value) -> {
        return (value != null) ? value.isEmpty() : false;
    };
    iPredicate<String> predicate3 = (value) -> false;
    iPredicate<String> predicate4 = String::isEmpty;

    public static void main(String[] args) {
        Predicate predicate = new Predicate();
        boolean[] result = {
                predicate.predicate1.test(""),
                predicate.predicate2.test(null),
                predicate.predicate3.test("test"),
                predicate.predicate4.test("test")
        };

        Arrays.asList(result).forEach(System.out::println);
    }

    @FunctionalInterface
    interface iPredicate<T> {
        boolean test(T t);
    }
}
