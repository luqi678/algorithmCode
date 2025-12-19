package com.myself.learn.algo;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @projectName（项目名称）:qljt_cosmic
 * @package（包）:com.macro.mall
 * @className（类名称）:TestThread
 * @description（类描述）:一句话描述该类的功能
 * @author（创建人）:lujian
 * @createDate（创建时间）:2025-08-25 16:54
 * @updateUser（修改人）:lujian
 * @updateDate（修改时间）:2025-08-25 16:54
 * @updateRemark（修改备注）:说明本次修改内容
 * @version（版本）:v1.0
 * @form(表单名称):
 **/
public class TestThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = new Runnable() {
            /**
             * Runs this operation.
             */
            @Override
            public void run() {
                System.out.println("runnable");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        Thread thread1 = new Thread();

        Callable<Integer> callable = new Callable<Integer>() {
            /**
             * Computes a result, or throws an exception if unable to do so.
             *
             * @return computed result
             * @throws Exception if unable to compute a result
             */
            @Override
            public Integer call() throws Exception {
                System.out.println("callable");
                return 1;
            }
        };

        FutureTask<Integer> futureTask = new FutureTask<>(callable);




        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(futureTask);
        executorService.submit(thread1);
        executorService.execute(runnable);

        System.out.println(futureTask.get());


        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println("supplyAsync");
            return 2;
        }, executorService);
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
            runnable.run();
            return 1;
        }, executorService);


        executorService.close();

//        ReentrantLock
    }
}