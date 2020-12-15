package com.fdm.w6.threading.container;

import java.util.LinkedList;
import java.util.List;

public class Container {
    private List<Item> list = new LinkedList<>();
    private static final int SIZE = 3;

    boolean isFull() {
        return list.size() == SIZE;
    }

    boolean isEmpty() {
        return list.isEmpty();
    }

    void addItem(Item i) {
        if (list.size() <= SIZE) list.add(i);
    }

    void popItem() {
        if (!list.isEmpty()) list.remove(0);
    }
}
