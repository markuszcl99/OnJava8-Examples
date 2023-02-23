package com.markus.onjava.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: markus
 * @date: 2023/2/23 10:26 PM
 * @Description: CycleBarrier使用示例
 * @Blog: https://markuszhang.com
 * @see CyclicBarrier
 * 循环屏障，即一组线程互相等待到达一个屏障后再去执行后续的动作
 * It's my honor to share what I've learned with you!
 */
public class CycleBarrierDemo {
    private static class SportsMan implements Runnable {
        private final CyclicBarrier cyclicBarrier;

        public SportsMan(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("sportsman [" + Thread.currentThread().getId() + "] is ready!");
                cyclicBarrier.await();
                System.out.println("sportsman [" + Thread.currentThread().getId() + "] is Running!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(9);
        for (int i = 0; i < 8; i++) {
            Thread thread = new Thread(new SportsMan(cyclicBarrier));
            thread.start();
        }
        System.out.println("The referee fired the gun!");
        cyclicBarrier.await();
    }
}
