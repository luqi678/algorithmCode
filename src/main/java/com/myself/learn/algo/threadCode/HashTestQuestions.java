package com.myself.learn.algo.threadCode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @projectName（项目名称）:qljt_cosmic
 * @package（包）:com.macro.mall
 * @className（类名称）:HashTestQuestions
 * @description（类描述）:一句话描述该类的功能
 * @author（创建人）:lujian
 * @createDate（创建时间）:2025-12-09 15:14
 * @updateUser（修改人）:lujian
 * @updateDate（修改时间）:2025-12-09 15:14
 * @updateRemark（修改备注）:说明本次修改内容
 * @version（版本）:v1.0
 * @form(表单名称):
 **/
public class HashTestQuestions {

    public static void main(String[] args) {
        HashTestQuestions test = new HashTestQuestions();
        // 缺失的第一个正整数
        // System.out.println(test.minNumberDisappeared(
        //                 new int[]{-2,3,4,1,5}
        //         ));
        // 三数之和为0
        System.out.println(test.threeSum(
                new int[]{-5,-5,-4,-4,-4,-2,-2,-2,0,0,0,1,1,3,4,4}
        ));

    }



    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param nums int整型一维数组
     * @return int整型
     */
    public int minNumberDisappeared (int[] nums) {
        for (int i = 0; i < nums.length; i++) {

            while (nums[i] > 0 && nums[i] < nums.length + 1 && nums[i] != i+1) {
                int num = nums[i];
                // 互换 i 和 nums[i] - 1
                int temp = nums[num - 1];
                nums[num - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 1 || nums[i] > nums.length  || nums[i] != i+1) {
                return i + 1;
            }
        }
        return  nums.length + 1;
    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param num int整型一维数组
     * @return int整型ArrayList<ArrayList<>>
     */
    public ArrayList<ArrayList<Integer>> threeSum (int[] num) {
        // write code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(num.length < 3){
            return result;
        }else if (num.length == 3){
            if (num[0] + num[1] + num[2] == 0) {
                Arrays.sort(num);
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(num[0]);
                temp.add(num[1]);
                temp.add(num[2]);
                result.add(temp);
                return result;
            }else{
                return result;
            }
        }

        Arrays.sort(num);
        for (int i = 0; i < num.length; i++) {
            int num1 = num[i];
            int left = i + 1;
            int right = num.length - 1;
            if (i > 0 && num[i] == num[i-1] ) {
                continue;
            }
            if (num1 > 0) {
                break;
            }

            while (left < right ) {
                int num2 = num[left];
                int num3 = num[right];
                if (i != left - 1 && num[left] == num[left-1] ) {
                    left++;
                    continue;
                }
                if (num2 + num3 > -num1) {
                    right --;
                }else if (num2 + num3 < -num1) {
                    left ++ ;
                }else {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(num1);
                    temp.add(num2);
                    temp.add(num3);
                    result.add(temp);
                    left++;
                }
            }
        }
        return result;
    }
}