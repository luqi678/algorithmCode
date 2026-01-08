package com.myself.learn.algo.huawei.two;

import java.text.NumberFormat;
import java.util.Scanner;

/**
 * 运输时间
 * @author 2405051
 */
public class TransportationTime {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }
        int times = 0;
        Double distance = 0d;
        String input1 = sc.nextLine();
        try {
            String[] split = input1.split("\\s+");
            if (split.length != 2) {
                System.out.println("输入错误");
                return;
            }
            times = Integer.parseInt(split[0]);
            distance = Double.parseDouble(split[1]);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Double[] speeds = new Double[times];
        for (int i = 0; i < times; i++) {
            if (!sc.hasNextLine()) {
                return;
            }
            speeds[i] = Double.parseDouble(sc.nextLine());
        }

        double reachTime = 0d;
        for (int i = 0; i < times; i++) {
            reachTime = Math.max(reachTime, distance/speeds[i] + Double.parseDouble(String.valueOf(i)));
        }
        NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(2);
        instance.setGroupingUsed(false);
        System.out.println(instance.format(reachTime+1-Double.parseDouble(String.valueOf(times))));
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) {
            return;
        }

        // 1. 读取第一行
        String input1 = sc.nextLine();
        String[] split = input1.trim().split("\\s+"); // 使用 trim 和 \\s+ 防止空格解析错误
        if (split.length != 2) return;

        int m = Integer.parseInt(split[0]); // 车辆数
        double n = Double.parseDouble(split[1]); // 距离 (使用 double 保持精度)

        // arrived 记录前一辆车到达终点的时刻
        double arrived = 0;

        for (int i = 0; i < m; i++) {
            if (!sc.hasNextLine()) break;
            // 2. 读取速度
            double speed = Double.parseDouble(sc.nextLine().trim());

            // 3. 核心逻辑：
            // 当前车理论到达时间 = 出发时间(i) + 行驶时间(n / speed)
            // 实际到达时间 = max(前车到达时间, 当前车理论到达时间)
            // 必须使用 double 进行运算，不要在中间步骤四舍五入！
            double currentArrive = i + (n / speed);
            arrived = Math.max(arrived, currentArrive);
        }

        // 4. 计算最后花费时间 = 到达时间 - 最后一辆车的出发时间
        double finalCost = arrived - (m - 1);

        // 5. 格式化输出 (关键步骤)
        // 使用 NumberFormat 自动处理 "2.0" -> "2" 和保留小数的问题
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false); // 不使用千分位逗号
        nf.setMaximumFractionDigits(3); // 最多保留3位小数

        System.out.println(nf.format(finalCost));
    }
}