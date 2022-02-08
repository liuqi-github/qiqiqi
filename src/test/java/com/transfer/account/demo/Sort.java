package com.transfer.account.demo;

import java.util.Arrays;

/**
 * @author LiuQi
 * @date 2022/1/12 10:35
 */
public class Sort {
    public static void main(String[] args) {
        int[] arr = new int[]{1,4,2,7,5,9,8,6};
        //bubbleSort(arr);
        //selectionSort(arr);
        //insertSort(arr);
//        shellSort(arr);
        fastSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr){
        int sortIndex = arr.length - 1;
        for(int a = 0; a < arr.length - 1; a++){
            boolean isSorted = true;
            int last = 0;
            for(int b = 0; b < sortIndex; b++){
                if(arr[b] > arr[b+1]){
                    int num = arr[b];
                    arr[b] = arr[b+1];
                    arr[b+1] = num;
                    isSorted = false;
                    last = b;  // 一轮循环中最后交换的位置 这个位置之后就是有序队列
                }
            }
            sortIndex = last;
            if (isSorted) {
                break;
            }
        }
    }

    public static void selectionSort(int[] arr){
        int length = arr.length;
        for(int a = 0; a < length - 1; a++){
            int minIndex = a;
            for(int b = a; b < length; b++){
                if(arr[b] < arr[minIndex]){
                    minIndex = b;
                }
            }
            int minNum = arr[minIndex];
            arr[minIndex] = arr[a];
            arr[a] = minNum;
        }
    }

    public static void insertSort(int[] arr){
        for(int a = 0; a < arr.length; a++){
            System.out.println(a);
            int num = arr[a];
            int index = a;
            for(int b = a; b >= 0; b--){
                if(num < arr[b]){
                    int temp = arr[index];
                    arr[index] = arr[b];
                    arr[b] = temp;
                    index = b;
                }
            }
        }
    }

    public static void shellSort(int[] arr){
        int length = arr.length;
        int gap = arr.length / 2;
        while(gap > 0){
            for(int b = length - 1; b >= gap; b -= gap){
                if(arr[b] < arr[b - gap]){
                    int temp = arr[b];
                    arr[b] = arr[b - gap];
                    arr[b - gap] = temp;
                }
            }
            gap /= 2;
        }
    }

    public static void fastSort(int[] arr, int start, int end){
        if(start >= end){
            return;
        }

        int base = sort(arr, start, end);
        fastSort(arr, start, base - 1);
        fastSort(arr, base + 1, end);
    }

    public static int sort(int[] arr, int mark, int end){
        int num;
        num = arr[mark];
        int base = mark;
        for(int a = mark + 1; a <= end; a++){
            if(arr[a] < num){
                mark++;
                int temp = arr[a];
                arr[a] = arr[mark];
                arr[mark] = temp;
            }
        }
        arr[base] = arr[mark];
        arr[mark] = num;
        return mark;
    }

    public static int[] mergeSort(){




        return null;
    }




}
