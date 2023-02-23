package com.markus.onjava.concurrent;

import java.util.concurrent.Semaphore;

/**
 * @author: markus
 * @date: 2023/2/23 10:34 PM
 * @Description: Semaphore 使用示例
 * @Blog: https://markuszhang.com
 * @see Semaphore
 * Java版的信号量，用于调控线程资源的数量，在一段时间内仅允许指定数量的线程执行，其余线程均被阻塞
 * It's my honor to share what I've learned with you!
 */
public class SemaphoreDemo {
    private static class Taxi implements Runnable {
        private final Semaphore semaphore;

        public Taxi(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                // 获取信号量的准入资格
                semaphore.acquire();
                Thread.sleep(1000);
                System.out.println("Taxi [" + Thread.currentThread().getId() + "] pickup finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放信号
                semaphore.release();
            }
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Taxi(semaphore));
            thread.start();
        }
    }
}
