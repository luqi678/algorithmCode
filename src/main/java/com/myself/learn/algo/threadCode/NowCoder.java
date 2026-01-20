package com.myself.learn.algo.threadCode;

/**
 * @projectName（项目名称）:qljt_cosmic
 * @package（包）:com.macro.mall
 * @className（类名称）:NowCoder
 * @description（类描述）:一句话描述该类的功能
 * @author（创建人）:lujian
 * @createDate（创建时间）:2025-11-28 14:36
 * @updateUser（修改人）:lujian
 * @updateDate（修改时间）:2025-11-28 14:36
 * @updateRemark（修改备注）:说明本次修改内容
 * @version（版本）:v1.0
 * @form(表单名称):
 **/
public class NowCoder {
    public static void main(String[] args) {

    }


    // 写一个二分法判断排序数组中是否存在某个数
    public static boolean binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}