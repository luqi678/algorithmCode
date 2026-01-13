package com.myself.learn.algo.huawei.C2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * P00397.华为od机试—找到比自己强的人 / 师徒关系
 * 2025C卷
 * difficulty:4
 * @author luqi
 */
public class C397 {

    /**
     * 全局静态 Scanner，方便在任何函数中使用
     */
    static Scanner sc = new Scanner(System.in);
    static int maxIndex = 0;

    public static void main(String[] args) {

        if(sc.hasNextLine()){
            String line = sc.nextLine();
            int[][] relations = parseMatrix(line);
            // 用数组显示视图关系
            int[] parents = new int[maxIndex + 1];
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
            }
            for (int[] relation : relations) {
                parents[relation[1]] = relation[0];
            }
            // 比自己强的徒弟人数
            int[] strongCount = new int[maxIndex];
            // 计算强的徒弟人数
            for (int i = 1; i < parents.length; i++) {
                if (i < parents[i])
                    strongCount[parents[i] - 1]++;
                if (i < parents[parents[i]] && parents[i] != parents[parents[i]])
                    strongCount[parents[parents[i]] - 1]++;
            }
            System.out.println(Arrays.toString(strongCount).replaceAll("\\s+", ""));
        }


        // 记得关闭（虽然机试不关也不报错，但这是好习惯）
        sc.close();
    }



    /**
     * 通用矩阵解析器：处理 [[...],[...]] 格式，自动忽略空格和非数字字符
     */
    private static int[][] parseMatrix(String str) {
        if (str == null || str.isEmpty()) return new int[0][0];

        // 使用正则匹配每一行内容 [...]
        List<int[]> rows = new ArrayList<>();
        // 匹配嵌套在 [] 里的内容
        java.util.regex.Matcher m = java.util.regex.Pattern.compile("\\[([^\\[\\]]+)\\]").matcher(str);

        while (m.find()) {
            String rowContent = m.group(1);
            String[] parts = rowContent.split(",");
            int[] row = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                row[i] = Integer.parseInt(parts[i].trim());
                maxIndex = Math.max(maxIndex, row[i]);
            }
            rows.add(row);
        }
        return rows.toArray(new int[0][]);
    }
}
