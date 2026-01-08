package com.myself.learn.temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedReaderTemp {


    public static void main(String[] args) throws IOException {
        // 使用快读，防止 N=10^6 时读取超时
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] jobs = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");
            jobs[i][0] = Integer.parseInt(parts[0]); // SLA 时间
            jobs[i][1] = Integer.parseInt(parts[1]); // 积分
        }
    }
}
