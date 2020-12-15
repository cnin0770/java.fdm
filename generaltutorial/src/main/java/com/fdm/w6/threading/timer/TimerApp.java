package com.fdm.w6.threading.timer;

class TimerApp implements Runnable {
    private int count = 0;
    private int upper_bound;
    private Runnable upper_limb;

    TimerApp(int upper_bound, Runnable upper_limb) {
        this.upper_bound = upper_bound;
        this.upper_limb = upper_limb;
    }

    int getCount() {
        return count;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (upper_limb == null) {
                    ++count;
                    continue;
                }
                if (++count >= upper_bound) {
                    count = 0;
                    synchronized (upper_limb) {
                        upper_limb.notify();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TimerApp hours = new TimerApp(0, null);
        TimerApp minutes = new TimerApp(60, hours);
        TimerApp seconds = new TimerApp(60, minutes);
        TimerApp tenthsOfSeconds = new TimerApp(10, seconds);

        (new Thread(hours)).start();
        (new Thread(minutes)).start();
        (new Thread(seconds)).start();
        (new Thread(tenthsOfSeconds)).start();

        while (hours.getCount() <= 24) {
            try {
                Thread.sleep(100);
                synchronized (tenthsOfSeconds) {
                    tenthsOfSeconds.notify();
                    System.out.printf("[APP] %d: %d: %d: %d\n", hours.getCount(), minutes.getCount(), seconds.getCount(), tenthsOfSeconds.getCount());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
