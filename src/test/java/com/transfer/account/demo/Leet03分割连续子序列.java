package com.transfer.account.demo;

import java.util.ArrayList;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author LiuQi
 * @date 2020/12/4 10:30
 */
public class Leet03分割连续子序列 {
    public static void main(String[] args) {
        int[] nums = {1,2,2,3,3,4,4,5};
        if(nums==null||nums.length<=2) return/* false*/;
        /**
         * 1.贪心算法,数x只有两种插入方式,一种是将x插入到x-1为结尾的序列,另一种就是重新开始一个新的序列,
         * 但是需要将后面两个数字作为一个新的子序列建立出来,那么总得来说,第一种方式效率将大于第二种效率
         * 2.需要建立两个hash表,其中一个用来保存出现过的所有数字的出现的总次数;领一个hash表用于记录
         * 当前位置的可构成的序列的个数
         * 3.尽量将所有数够长(贪心算法  并不是立即创建一个最短的长度为3的序列  而是尽可能延长序列的长度实在没有满足条件的序列再去创建子序列)
         */
        //先建立两个hash表
        // 一个记录数字出现次数
        Map<Integer, Integer>  numCount = new HashMap<>();
        //另一个记录出现的尾数为该数的序列个数
        Map<Integer, Integer>  seqCount = new HashMap<>();

        for(int i : nums){
            int count = numCount.getOrDefault(i, 0);
            numCount.put(i, ++count);
        }

        for(int i : nums){
            //元素剩余使用次数
            int count = numCount.getOrDefault(i, 0);
            //将元素添加进序列中
            if(count > 0){
                //首先将元素添加到序列的末尾
                int seqNum = seqCount.getOrDefault(i-1, 0);
                if(seqNum > 0){
                    //如果存在可以添加元素的序列 更改序列数目 如果存在序列可以添加元素就尽量延长序列的长度
                    numCount.put(i, count-1);
                    seqCount.put(i-1, seqNum-1);
                    seqCount.put(i, seqCount.getOrDefault(i, 0)+1);
                }else{
                    //如果不存在 新建一个长度为3的序列  因为是递增序列而且又是从左往右遍历的 所以大的元素肯定在右边
                    int midCount = numCount.getOrDefault(i+1, 0);
                    int bigCount = numCount.getOrDefault(i+2, 0);
                    if(midCount > 0 && bigCount > 0){
                        numCount.put(i+1, midCount-1);
                        numCount.put(i+2, bigCount-1);
                        numCount.put(i, count-1);
                        seqCount.put(i+2, seqCount.getOrDefault(i+2, 0)+1);
                    }else{
                        System.out.println("失败");
                    }
                }
            }
        }
        System.out.println("结束");
    }


    public boolean isPossible(int[] nums) {
        if(nums==null||nums.length<=2) return false;
        /**
         * 1.贪心算法,数x只有两种插入方式,一种是将x插入到x-1为结尾的序列,另一种就是重新开始一个新的序列,
         * 但是需要将后面两个数字作为一个新的子序列建立出来,那么总得来说,第一种方式效率将大于第二种效率
         * 2.需要建立两个hash表,其中一个用来保存出现过的所有数字的出现的总次数;领一个hash表用于记录
         * 当前位置的可构成的序列的个数
         * 3.尽量将所有数够长
         */
        //先建立两个hash表
        // 一个记录数字出现次数
        Map<Integer, Integer>  numCount = new HashMap<>();
        //另一个记录出现的尾数为该数的序列个数
        Map<Integer, Integer>  seqCount = new HashMap<>();

        for(int i : nums){
            int count = numCount.getOrDefault(i, 0);
            numCount.put(i, ++count);
        }

        for(int i : nums){
            //元素剩余使用次数
            int count = numCount.getOrDefault(i, 0);
            //将元素添加进序列中
            if(count > 0){
                //首先将元素添加到序列的末尾
                int seqNum = seqCount.getOrDefault(i-1, 0);
                if(seqNum > 0){
                    //如果存在可以添加元素的序列 更改序列数目 如果存在序列可以添加元素就尽量延长序列的长度
                    numCount.put(i, count-1);
                    seqCount.put(i-1, seqNum-1);
                    seqCount.put(i, seqCount.getOrDefault(i, 0)+1);
                }else{
                    //如果不存在 新建一个长度为3的序列  因为是递增序列而且又是从左往右遍历的 所以大的元素肯定在右边
                    int midCount = numCount.getOrDefault(i+1, 0);
                    int bigCount = numCount.getOrDefault(i+2, 0);
                    if(midCount > 0 && bigCount > 0){
                        numCount.put(i+1, midCount-1);
                        numCount.put(i+2, bigCount-1);
                        numCount.put(i, count-1);
                        seqCount.put(i+2, seqCount.getOrDefault(i+2, 0)+1);
                    }else{
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
