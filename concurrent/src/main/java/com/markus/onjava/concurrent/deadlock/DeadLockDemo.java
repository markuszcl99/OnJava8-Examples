package com.markus.onjava.concurrent.deadlock;

/**
 * @author: markus
 * @date: 2023/2/22 10:21 PM
 * @Description: 死锁代码演示
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        Thread thread1 = new Thread(new Task(lockA, lockB));
        Thread thread2 = new Thread(new Task(lockB, lockA));
        thread1.start();
        thread2.start();
    }
}

class Task implements Runnable {

    private final String lockA;
    private final String lockB;

    public Task(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        try {
            synchronized (lockA) {
                log("contain lock:[" + lockA + "]");
                Thread.sleep(2000);
                synchronized (lockB) {
                    log("contain lock:[" + lockB + "]");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void log(String msg) {
        System.out.println("Thread id [" + Thread.currentThread().getId() + "] msg: " + msg);
    }
}