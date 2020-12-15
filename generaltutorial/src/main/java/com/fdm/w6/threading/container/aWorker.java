package com.fdm.w6.threading.container;

abstract class aWorker {
    Container container;
    aWorker(Container container) {
        this.container = container;
    }

    void broadcast() {
        System.out.println(this.getClass().getSimpleName() + " works.");
    }
}
