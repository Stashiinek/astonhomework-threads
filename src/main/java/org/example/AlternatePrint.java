package org.example;

public class AlternatePrint {
    private int turn = 1;

    public void printOne() {
        synchronized (this) {
            while (turn != 1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            System.out.print("1");
            turn = 2;
            notifyAll();
        }
    }

    public void printTwo() {
        synchronized (this) {
            while (turn != 2) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            System.out.print("2");
            turn = 1;
            notifyAll();
        }
    }

    public static void main(String[] args) {
        AlternatePrint printer = new AlternatePrint();

        Thread thread1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                printer.printOne();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                printer.printTwo();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}