package com.fdm.w6.threading.container;

public class ContainerApp {
    public static void main(String[] args) {
        Container container = new Container();
        Thread fillWorkerThread = new Thread(new FillWorker(container));
        Thread emptyWorkerThread = new Thread(new EmptyWorker(container));

        fillWorkerThread.start();
        emptyWorkerThread.start();
    }
}
