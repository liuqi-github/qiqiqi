package com.transfer.account.demo;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author LiuQi
 * @date 2020/12/9 17:44
 */
public class Leet06判断回文 {
    public static void main(String[] args) {
        System.out.println(isGood(1234567899));
    }

    public static boolean isGood(int num){
        String[] s = String.valueOf(num).split("");

        List<String> list = Arrays.asList(s);

        if(list.size() <= 0 || "-".equals(list.get(0))){
            return false;
        }

        Collections.reverse(list);

        StringBuilder val = new StringBuilder();
        list.stream().forEach(p -> val.append(p));
        String str = val.toString();
        try{
        if(Integer.valueOf(str) == num){
            return true;
        }
        }catch (Exception e){
            return false;
        }
        return  false;
    }


}
