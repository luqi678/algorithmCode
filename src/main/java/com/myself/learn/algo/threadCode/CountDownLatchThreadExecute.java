package com.myself.learn.algo.threadCode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @projectName（项目名称）:qljt_cosmic
 * @package（包）:com.macro.mall
 * @className（类名称）:TestCountDownLatch
 * @description（类描述）:一句话描述该类的功能
 * @author（创建人）:lujian
 * @createDate（创建时间）:2025-11-15 15:12
 * @updateUser（修改人）:lujian
 * @updateDate（修改时间）:2025-11-15 15:12
 * @updateRemark（修改备注）:说明本次修改内容
 * @version（版本）:v1.0
 * @form(表单名称):
 **/
public class CountDownLatchThreadExecute {
    public static void main(String[] args) throws InterruptedException {
        // 创建CountDownLatch对象，用来做线程通信
        CountDownLatch latch = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        CountDownLatch latch3 = new CountDownLatch(1);

        // 创建并启动线程T1
        Thread t1 = new Thread(new CountDownLatchRunnable(latch), "T1");
        // 创建并启动线程T2
        Thread t2 = new Thread(new CountDownLatchRunnable(latch2), "T2");
        // 创建并启动线程T3
        Thread t3 = new Thread(new CountDownLatchRunnable(latch3), "T3");
        t1.start();
        // 等待线程T1执行完
        latch.await();
        t2.start();
        // 等待线程T2执行完
        latch2.await();
        t3.start();
        // 等待线程T3执行完
        latch3.await();


        // 创建信号量
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        Thread t4 = new Thread(new SemaphoreRunnable(semaphore), "T4");
        t4.start();
        semaphore.acquire();
        Thread t5 = new Thread(new SemaphoreRunnable(semaphore), "T5");
        t5.start();
        semaphore.acquire();
        Thread t6 = new Thread(new SemaphoreRunnable(semaphore), "T6");
        t6.start();
        boolean match = semaphore.tryAcquire();
        System.out.println(match);

    }
}

class CountDownLatchRunnable implements Runnable {
    private CountDownLatch latch;

    public CountDownLatchRunnable(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            // 模拟执行任务
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " is Running.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 完成一个线程，计数器减1
            latch.countDown();
        }
    }
}


class SemaphoreRunnable implements Runnable {

    private Semaphore semaphore;

    public SemaphoreRunnable(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            // 模拟执行任务
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " is Running.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放许可证，表示完成一个线程
            semaphore.release();
        }
    }

}
