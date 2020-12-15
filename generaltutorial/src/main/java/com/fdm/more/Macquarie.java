package com.fdm.more;

public class Macquarie {

    public void divide(boolean a, boolean b) {
        if (a) System.out.println("A");
        else if (a && b) System.out.println("a && b");
        else {
            if (!b) System.out.println("not b");
            else System.out.println("else");
        }
    }

    public static void main(String[] args) {
        Macquarie macquarie = new Macquarie();
        macquarie.divide(false, true);
        macquarie.car();
    }

    public void car() {
        Car car = new Car();
        car.topS();
        car.topS(200);
        car.topS("toyota", 1000);
    }

    public class Car {
        public void topS() {
            topS(100);
        }
        public void topS(int ts) {
            topS("", ts);
        }
        public void topS(String str, int ts) {
            String fp = str.isEmpty() ? "The top speed of the vehicle is " : "The top speed of the vehicle " + str +
                    " is ";
            System.out.println(fp + ts + " kmph");
        }
    }
}
