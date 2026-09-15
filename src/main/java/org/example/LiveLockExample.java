package org.example;

public class LiveLockExample {
    private volatile boolean flag1 = false;
    private volatile boolean flag2 = false;

    public void action1() {
        while (true) {
            if (flag2) {
                flag1 = true;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                if (flag2) { // Повторная проверка после сна
                    flag1 = false; // Сброс флага для уступки
                }
            }
        }
    }

    public void action2() {
        while (true) {
            if (flag1) {
                flag2 = true;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                if (flag1) {
                    flag2 = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        LiveLockExample example = new LiveLockExample();

        Thread thread1 = new Thread(example::action1);
        Thread thread2 = new Thread(example::action2);

        thread1.start();
        thread2.start();
    }
}