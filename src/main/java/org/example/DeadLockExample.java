package org.example;

public class DeadLockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void method1() {
        synchronized (lock1) {
            System.out.println("Поток 1: Захватил lock1");
            try {
                Thread.sleep(100); // Даём шанс второму потоку захватить lock2
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            synchronized (lock2) {
                System.out.println("Поток 1: Захватил lock1 и lock2");
            }
        }
    }

    public void method2() {
        synchronized (lock2) {
            System.out.println("Поток 2: Захватил lock2");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            synchronized (lock1) {
                System.out.println("Поток 2: Захватил lock1 и lock2");
            }
        }
    }

    public static void main(String[] args) {
        DeadLockExample example = new DeadLockExample();

        Thread thread1 = new Thread(example::method1);
        Thread thread2 = new Thread(example::method2);

        thread1.start();
        thread2.start();
    }
}