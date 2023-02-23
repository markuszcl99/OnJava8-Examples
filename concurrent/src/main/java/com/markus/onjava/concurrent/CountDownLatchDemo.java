package com.markus.onjava.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author: markus
 * @date: 2023/2/23 10:06 PM
 * @Description: CountDownLatch使用示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 * @see CountDownLatch
 * 一种同步辅助工具，允许一个或多个线程等待，直到其他线程中执行的一组操作完成
 */
public class CountDownLatchDemo {
    private static class Task implements Runnable {
        private final CountDownLatch countDownLatch;

        public Task(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("Thread-[" + Thread.currentThread().getId() + "] action execute!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }
    }

    private static class FirstTask implements Runnable {
        private final CountDownLatch countDownLatch;

        public FirstTask(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("Thread-[" + Thread.currentThread().getId() + "] FirstTask execute!");
            countDownLatch.countDown();
        }
    }

    private static class SecondTask implements Runnable {
        private final CountDownLatch countDownLatch;

        public SecondTask(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
                System.out.println("Thread-[" + Thread.currentThread().getId() + "] SecondTask execute!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        demo2();
    }

    /**
     * 一个线程的执行等待其他线程执行完后执行
     *
     * @throws InterruptedException
     */
    private static void demo1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Task(countDownLatch));
            thread.start();
        }
        countDownLatch.await();
        System.out.println("main thread action execute!");
    }

    /**
     * 一批线程等待另一批线程执行完成后执行
     */
    private static void demo2() {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new FirstTask(countDownLatch));
            thread.start();
        }

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new SecondTask(countDownLatch));
            thread.start();
        }

        System.out.println("SecondTasks wait FirstTasks!");
        countDownLatch.countDown();
    }
}
