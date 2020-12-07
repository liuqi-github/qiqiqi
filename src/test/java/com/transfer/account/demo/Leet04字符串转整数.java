package com.transfer.account.demo;

import com.google.common.base.Strings;
import com.google.common.primitives.Ints;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author LiuQi
 * @date 2020/12/7 15:26
 */
public class Leet04字符串转整数 {
    public static void main(String[] args) {
        System.out.println(getValueNum("-91283472332"));
    }

    public static int getValueNum(String s){
        char[] nums = {'0','1','2','3','4','5','6','7','8','9'};
        //Arrays.asList asList接受的参数是一个泛型的变长参数，
        //而基本数据类型是无法泛型化的 所以接受string数组可以正常转化  而接收int数组无法做到正常转化
        List<Character> list = new String(nums).chars().mapToObj(c -> (char)c).collect(Collectors.toList());
        boolean isFuShu = false;
        boolean isAllNum = true;
        int maxValue =  (int)Math.pow(2,31);
        int minValue =  (int)Math.pow(-2,31);

        s= s.trim();
        if(s.length() > 0){
            if(s.charAt(0) == '-'){
                s = s.substring(1);
                isFuShu = true;
            }else if(s.charAt(0) == '+'){
                s = s.substring(1);
            }

            char[] chars = s.toCharArray();
            for(int i = 0; i < chars.length; i++){
                if(!list.contains(chars[i])){
                    s = s.substring(0,i);
                    isAllNum = false;
                    break;
                }
            }
        }

        int num;
        try {
            if(s.length() <= 0){
                return 0;
            }else if(s.length() > 0){
                num = Integer.parseInt(s);
                if(isFuShu){
                    return num*-1;
                }
                return num;
            }
        }catch(Exception e){
            System.out.println("程序异常");
            return  (isFuShu) ? minValue : maxValue;
        }
        return 0;
    }
}
