package com.transfer.account.demo;

import com.alibaba.fastjson.JSON;
import net.minidev.json.JSONUtil;

import java.util.ArrayList;
import java.util.*;

/**
 * @author LiuQi
 * @date 2020/12/2 10:43
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DemoApplication.class)
public class Leet01求最大不重复字符串长度 {
//    @Test
    public void test(){
        String str = "aabbccdd";
        char[] chars = str.toCharArray();

        List<Character> charList = new ArrayList<>();
        charList.add(chars[0]);

        System.out.println("是否包含" + charList.contains(chars[0]));
        System.out.println("是否包含" + charList.contains('a'));

    }



    public static void main(String[] args) {
   /*     String str = "adwaggadfff";
        Map<Integer, Integer> map = new HashMap<>();
        char[] chars = str.toCharArray();

        System.out.println("数组大小" + chars.length);

        int length = chars.length;
        if(length <= 0){
            System.out.println("传参不能为空");
            return ;
        }else if(length == 1){
            return ;
        }


        //按str从左至右逐个递减的方式 每次都减一个元素求出最大长度的字符串
        for(int j = 0; j < length-1; j++){
            List<Character> list = new ArrayList<>();
            List<Character> resList = new ArrayList<>();
            //如果长度不为空 先填充第一个元素
            int samNum = 1;
            int tag = 1;
            list.add(chars[j]);
            resList.add(chars[j]);

            for(int i = j; i < length-1; i++){
                if(chars[i] != chars[i+1] && !list.contains(chars[i+1])){
                    tag+=1;
                    if(!list.contains(chars[i])){
                        list.add(chars[i]);
                    }
                    list.add(chars[i+1]);
                }else{
                    if(samNum < tag){
                        samNum = tag;
                        resList = list;
                    }
                    list = new ArrayList<>();
                    tag = 1;
                }
            }

            if(samNum < tag){
                samNum = tag;
                resList = list;
            }

            System.out.println("最长的长度" + samNum);
            System.out.println("字符串");
            resList.forEach(p -> System.out.print(p));

            map.put(j, resList.size());
        }

        AtomicInteger maxValue = new AtomicInteger();
        map.forEach((k,v) -> {
            if(v > maxValue.get()){
                maxValue.set(v);
            }
        });

        System.out.println("maxNum"+maxValue.get());
        System.out.println("map" + map);*/

        System.out.println("最大"+test02("dalgkidmhd"));



/*        //如果长度不为空 先填充第一个元素
        int samNum = 1;
        int tag = 1;
        list.add(chars[0]);
        resList.add(chars[0]);

        for(int i = 0; i < length-1; i++){
            if(chars[i] != chars[i+1] && !list.contains(chars[i+1])){
                tag+=1;
                if(!list.contains(chars[i])){
                    list.add(chars[i]);
                }
                list.add(chars[i+1]);
            }else{
                if(samNum < tag){
                    samNum = tag;
                    resList = list;
                }
                list = new ArrayList<>();
                tag = 1;
            }
        }

        if(samNum < tag){
            samNum = tag;
            resList = list;
        }

        System.out.println("最长的长度" + samNum);
        System.out.println("字符串");
        resList.forEach(p -> System.out.print(p));*/
    }



    public static int test01(String s){
        Map<Integer, Integer> map = new HashMap<>();
        int maxValue =0;
        char[] chars = s.toCharArray();

        int length = chars.length;
        if(length <= 0){
            return 0;
        }else if(length == 1){
            return 1;
        }else if(length >= 50000){
            return 0;
        }

        //按str从左至右逐个递减的方式 每次都减一个元素求出最大长度的字符串

            List<Character> list = new ArrayList<>();
            List<Character> resList = new ArrayList<>();
            //如果长度不为空 先填充第一个元素
            list.add(chars[0]);
            resList.add(chars[0]);

            //一个循环 遍历查询当前集合中已存在的字符 如果出现重复记录下当前最大长度并去除这个重复元素之前的字符在末尾添加这个元素 以此类推返回值取最大长度
            for(int i = 0; i < length-1; i++){
                if(chars[i] != chars[i+1] && !list.contains(chars[i+1])){
                    if(!list.contains(chars[i])){
                        list.add(chars[i]);
                    }
                    list.add(chars[i+1]);
                }else{
                    if(resList.size() < list.size()){
                        resList = list;
                    }
                    //出现重复 去掉现有字符串重复之前的元素
                    //list -> string
                    String str = "";
                    for(char c : list){
                        str = str + c;
                    }
                    int index = str.indexOf(chars[i+1]);
                    str = str.substring(index+1);
                    System.out.println(str);
                    char[] chs = str.toCharArray();

                    list = new ArrayList<>();

                    for(char c : chs){
                        list.add(c);
                    }
                    list.add(chars[i+1]);

                    if(resList.size() < list.size()){
                        resList = list;
                    }
                }

                if(maxValue > length-i){
                    break;
                }
            }

            if(resList.size() < list.size()){
                resList = list;
            }

            maxValue = Math.max(resList.size(), maxValue);
            return maxValue;
    }

    public static int test02(String s){/*100%*/
        char[] chars = s.toCharArray();
        //这里用到了ASCII码来区分不同的元素  不同位置的相同ASCII码是相同的
        int[] last = new int[128];
        for(int i = 0; i<128; i++){
            last[i] = -1;
        }

        int start = 0;
        int res = 0;

        for(int i = 0; i<chars.length; i++){
            int index = chars[i];
            //记录元素出现的位置 没有出现重复元素的话 start会一直为0
            start = Math.max(start, last[index] +1);
            //字符串长度 i当前便利位置-start没有出现重复值的开始位置
            res = Math.max(res, i - start + 1);
            //记录这个元素出现的位置
            last[index] = i;
        }


        return res;
    }


    public static int[] test03(){
        int target = 19;
        int[] nums = {7,12,10,15,19};
        List<Integer> resList = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for(int i =0; i<nums.length; i++){
            map.put(i,nums[i]);
        }

        map.forEach((k,v) ->{
         list.add(k);
        });

        for(Integer i : list){
            int a = map.get(i);
            map.remove(i);
            map.forEach((k,v) -> {
                if((a+v)==target){
                    resList.add(k);
                    resList.add(i);
                }
            });
            if(resList.size() == 2){
                return resList.stream().mapToInt(Integer::valueOf).toArray();
            }
        }

        return resList.stream().mapToInt(Integer::valueOf).toArray();
    }

}
