package com.fdm.w6.threading.container;

class EmptyWorker extends aWorker implements Runnable {
    EmptyWorker(Container container) {
        super(container);
    }

    @Override
    public void run() {
        while (true) {
            try {
                empty();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void empty() throws InterruptedException {
        synchronized (container) {
            while (container.isEmpty()) {
                container.wait();
            }
            container.popItem();
            super.broadcast();
            container.notify();
        }
    }
}
