package com.myself.learn.algo.threadCode;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @projectName（项目名称）:qljt_cosmic
 * @package（包）:com.macro.mall
 * @className（类名称）:HorseRacingSimulation
 * @description（类描述）:一句话描述该类的功能
 * @author（创建人）:lujian
 * @createDate（创建时间）:2025-11-15 16:41
 * @updateUser（修改人）:lujian
 * @updateDate（修改时间）:2025-11-15 16:41
 * @updateRemark（修改备注）:说明本次修改内容
 * @version（版本）:v1.0
 * @form(表单名称):
 **/
public class HorseRacingSimulation {

    public static void main(String[] args) throws InterruptedException {
        HorseRacingSimulation horseRacingSimulation = new HorseRacingSimulation();
        ArrayList<Integer> result = new ArrayList<>();

        CountDownLatch ready = new CountDownLatch(10);
        CountDownLatch go = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(10);

        ExecutorService pool = Executors.newFixedThreadPool(10);



        for (int i = 0; i < 10; i++) {
            int finalI = i;
            pool.submit(
                    () -> {
                        try {
                            System.out.println(Thread.currentThread().getName() + "准备就绪！");
                            ready.countDown();
                            go.await();
                            System.out.println(Thread.currentThread().getName() + "开跑！");
                            Thread.sleep((long) (Math.random() * 1000));
                            System.out.println(Thread.currentThread().getName() + "跑结束！");
                            end.countDown();
                            result.add(finalI);
                        }catch (Exception e) {
                            e.printStackTrace();
                        }finally {
                        }
                    }
            );
        }

        ready.await();
        System.out.println("全部准备就绪");
        go.countDown();
        System.out.println("开跑");
        end.await();
        System.out.println("公布成绩");
        System.out.println(result.toString());
        pool.shutdown();


        for (int i : new int[5]) {

        }
    }





}
