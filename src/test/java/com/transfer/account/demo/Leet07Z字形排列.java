package com.transfer.account.demo;

import  java.util.*;

/**
 * @author LiuQi
 * @date 2020/12/10 13:18
 */
public class Leet07Z字形排列 {
    public static void main(String[] args) {
        System.out.println(test01("L", 3));
    }

    public  static String test01(String str, int row){
        String[] s = str.split("");
        StringBuilder res = new StringBuilder();

        if(s.length <= 1) return str;
        if(row <= 1)  return str;

        //循环行数
        for(int i = 0; i < row; i++){
            for(int j = i; j < s.length; j += (row-1)*2){
                //横向填充
                res.append(s[j]);
                //填充中间行
                if(i > 0 && i < row - 1){
                    int a = j + 2 * (row-i-1);
                    if(a < s.length){
                        res.append(str.charAt(a));
                    }
                }
            }
        }
        return res.toString();
    }
}
