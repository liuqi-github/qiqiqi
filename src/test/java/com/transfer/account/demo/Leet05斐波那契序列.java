package com.transfer.account.demo;

import java.util.*;

/**
 * @author LiuQi
 * @date 2020/12/8 10:22
 */
public class Leet05斐波那契序列 {

    /*给定一个数字字符串 S，比如 S = "123456579"，
    我们可以将它分成斐波那契式的序列 [123, 456, 579]。
    形式上，斐波那契式序列是一个非负整数列表 F，
    且满足：
    0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
    F.length >= 3；
    对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
    另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。
    返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。*/

    public static void main(String[] args) {
        System.out.println(splitIntoFibonacci("120130250"));
    }

    public static List<Integer> test01(String s){
        List<Integer> list = new ArrayList<>();

        if(s == null || s.length() < 3){
            return list;
        }
        int len = s.length();

        for(int i = 1; i <= len/2; i++){
            long t1 = Long.valueOf(s.substring(0, i));
            if((s.charAt(0) == '0' && i > 1) || t1 > 0x7fffffff){
                break;
            }

            for(int j=1; j<=len/2; j++){
                long t2 = Long.valueOf(s.substring(i, i+j));
                if((s.charAt(i) == '0' && j > 1) || t2 > 0x7fffffff){
                    break;
                }

                list.add((int)t1);
                list.add((int)t2);

                long num = list.get(list.size()-1) + list.get(list.size()-2);
                //k 是用来取每一次便利元素的位数 每添加一个数组到序列中需要跳过上次添加元素的位数
                // 因为要取出的下个数字num（res.size-1 + res.size-2）一样的数出来
                for(int k=i+j; k <= len - getLenNum((int)num); k = k + getLenNum((int)num)){

                }
            }


        }
        return null;
    }

    public static  int getLenNum(int num){
        int i = 0;
        while(num >= 1){
            num = num / 10;
            i++;
        }
        return i;
    }

    public static List<Integer> splitIntoFibonacci(String S) {
        LinkedList<Integer> res=new LinkedList<>();
        dfs(S,0,res);
        return res;
    }

    public static boolean dfs(String S,int index,List<Integer> lis){
        if (index == S.length()) {
            return lis.size()>2;
        }
        for (int i=index+1;i<=S.length();i++) {
            String temp=S.substring(index,i);
            //长度大于10,或者Long解析出来大于INT_MAX了就直接break
            if (S.charAt(index) == '0' && i>index+1 || temp.length()>10 || Long.valueOf(temp)>Integer.MAX_VALUE) {
                break;
            }
            int str=Integer.valueOf(temp);
            int one=lis.size()>=2 ? lis.get(lis.size()-1):-1;
            int two=lis.size()>=2 ? lis.get(lis.size()-2):-1;
            lis.add(str);
            if ((one==-1 || two==-1 || one+two==str) && dfs(S,i,lis)) {
                return true;
            }
            lis.remove(lis.size()-1);
        }
        return false;
    }
}
