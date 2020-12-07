package com.transfer.account.demo;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author LiuQi
 * @date 2020/12/3 10:05
 */
public class Leet02求最长回文字符串 {
    //之前是求不一样的最长  现在是求前后用一样的元素封闭的最长
    public static void main(String[] args) {
        System.out.println(test01("dwwd"));
    }

    //这个方法 aaaabbbbaaaa  相当于先检索连号的 如 bbbb 然后连号的全部查出来后查询这一块两边相等的  aaaa（bbbb）aaaa
    public static String longestPalindrome(String s) {
        String result = "";
        int[] limit = {0, 0};
        char[] ch = s.toCharArray();
        int i = 0;
        while (i < ch.length) {
            i = indexOf(ch, i, limit);
        }
        result = s.substring(limit[0], limit[1]);
        return result;
    }

    public static int indexOf(char[] ch, int low, int[] limit) {
        int high = low + 1;

        //查看传入元素的右边是否和传入元素相等  这个while检索的是连续相等的 aaaaa
        while (high < ch.length && ch[high] == ch[low]) {
            high++;
        }
        int result = high;

        //查询两边是否也是完全相等  这个检索的是两边相等的 bbaaabb
        while (low > 0 && high < ch.length && ch[low - 1] == ch[high]) {
            low--;
            high++;
        }

        if (high - low > limit[1] - limit[0]) {
            limit[0] = low;
            limit[1] = high;
        }
        return result;
    }


    public static String test01(String s){
        //初始化边界数组
        int[] limit = {0,0};
        char[] chars = s.toCharArray();

        int i =0;

        //String(引用变量) 但是String值不可变  在方法中传参改变的只是它的引用 引用改变了也就指向了不同的对象
        // 原方法内输出的只是原对象不是改变引用的String  StringBuilder(引用变量) 他的值可以被方法内改变是因为他 自始至终都是指向一个对象
        StringBuilder str = new StringBuilder("");
        while(i < s.length()){
            i = method01(chars, i, limit, str);
        }

        System.out.println(str);
        return s.substring(limit[0], limit[1]);
    }

    public static  int method01(char[] chars, int low, int[] limit, StringBuilder str){
        int high = low + 1;
        while(high < chars.length && chars[high] == chars[low]){
            high++;
        }
        int result = high;
        while (low > 0 && high < chars.length && chars[low-1] == chars[high]) {
            low--;
            high++;
        }

        if(high-low > limit[1] - limit[0]){
            limit[1]  = high;
            limit[0]  = low;
        }

        str = str.append("调用过方法了");
        return result;
    }
}
