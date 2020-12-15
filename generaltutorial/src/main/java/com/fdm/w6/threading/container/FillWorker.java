package com.fdm.w6.threading.container;

class FillWorker extends aWorker implements Runnable{
    FillWorker(Container container) {
        super(container);
    }

    @Override
    public void run() {
        while (true) {
            try {
                fill();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void fill() throws InterruptedException {
        synchronized (container) {
            while (container.isFull()) {
                container.wait();
            }
            container.addItem(new Item());
            super.broadcast();
            container.notify();
        }
    }
}
