package com.transfer.account.demo;


import java.util.*;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author LiuQi
 * @date 2020/12/10 16:20
 */
public class Main {
    public static void main(String[] args) throws Exception {
/*        int[] arr = new int[]{9,8,7,1,5,2,3,5,4,6};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));*/

        // [{minValue:2000,maxValue:3000},{minValue:600,maxValue:1000},{minValue:300,maxValue:500},{minValue:1000,maxValue:1500}]

        /*
        TestModel model1 = new TestModel(1501, 3000);
        TestModel model2 = new TestModel(500, 1000);
        TestModel model3 = new TestModel(300, 500);
        TestModel model4 = new TestModel(1000, 1500);
        List<TestModel> models = Arrays.asList(model1, model2, model3, model4);
        models.sort(Comparator.comparing(TestModel::getMinValue));
        for(int i = 0; i < models.size() - 1; i++){
            int bef = models.get(i).maxValue;
            int aft = models.get(i+1).minValue;
            if(aft != bef){
                throw new Exception("存在空区间");
            }
        }
        System.out.println("执行结束");
        */

/*        Stream<Integer> s2 = Stream.of(1, 2, 3, 4);
        Predicate<Integer> predicate2 = t -> t > 0;
        s2.parallel().reduce(Collections.synchronizedList(new ArrayList<>(16)), (r, t) -> {
            if (predicate2.test(t)) {
                r.add(t);
            }
            return r;
        },
            (r1, r2) -> {
                System.out.println(r1.size());
                return r1;
            }).forEach(System.out::println);*/


        /**
         * lambda语法：
         * System.out.println(Stream.of(1, 2, 3).parallel().reduce(4, (s1, s2) -> s1 + s2
         , (s1, s2) -> s1 * s2));
         */
        System.out.println(Stream.of(new AtomicInteger(1), new AtomicInteger(1), new AtomicInteger(1)).parallel().reduce(new AtomicInteger(4), new BiFunction<AtomicInteger, AtomicInteger, AtomicInteger>() {
                    @Override
                    public AtomicInteger apply(AtomicInteger integer, AtomicInteger integer2) {
                        synchronized (this){
                            return new AtomicInteger(integer.addAndGet(integer2.get()));
                        }
                    }
                }
                , (integer, integer2) -> new AtomicInteger(integer.addAndGet(integer2.get()))));

    }

    public static void diGui(int[] arr, int startIndex, int endIndex){
        if(startIndex >= endIndex){
            return;
        }
        // 获取基准元素位置
        int mark = sortAndGetMark(arr, startIndex, endIndex);
        // 分治排序
        diGui(arr, startIndex, mark - 1);
        diGui(arr, mark + 1, endIndex);
    }

    public static int sortAndGetMark(int[] arr, int startIndex, int endIndex){
        int provd = arr[startIndex];
        int mark = startIndex;
        for(int i = startIndex + 1; i <= endIndex; i++){
            if(arr[i] < provd){
                mark++;
                int a = arr[mark];
                arr[mark] = arr[i];
                arr[i] = a;
            }
        }
        arr[startIndex] = arr[mark];
        arr[mark] = provd;
        return mark;
    }

    public static void byStack(int[] arr, int startIndex, int endIndex){
        Stack<Map<String, Integer>> stack = new Stack<>();
        Map<String, Integer> map = new HashMap<>(16);
        map.put("startIndex", startIndex);
        map.put("endIndex", endIndex);
        stack.push(map);

        while(!stack.isEmpty()){
            Map<String, Integer> sMap = stack.pop();
            Integer sIndex = sMap.get("startIndex");
            Integer eIndex = sMap.get("endIndex");
            if(sIndex >= eIndex){
                return;
            }
            // 获取基准元素位置
            int mark = sortAndGetMark(arr, sIndex, eIndex);

            if(sIndex < mark - 1){
                Map<String, Integer> bmap = new HashMap<>(16);
                bmap.put("startIndex", sIndex);
                bmap.put("endIndex", mark - 1);
                stack.push(bmap);
            }

            if(eIndex > mark + 1){
                Map<String, Integer> bmap = new HashMap<>(16);
                bmap.put("startIndex", mark + 1);
                bmap.put("endIndex", eIndex);
                stack.push(bmap);
            }
        }
    }



    public static void selectionSort(int[] array) {
        for(int i = 0; i < array.length; i++){
            int minIndex = i;
            for(int j = i; j < array.length; j++){
                if(array[j] < array[minIndex]){
                    minIndex = j;
                }
            }

            int a = array[i];
            array[i] = array[minIndex];
            array[minIndex] = a;
        }
    }

    public static void insertSort(int[] array) {
        for(int i = 0; i < array.length; i++){
            int minIndex = i;
            for(int j = i - 1; j >= 0; j--){
                if(array[minIndex] < array[j]){
                    int a = array[j];
                    array[j] = array[minIndex];
                    array[minIndex] = a;
                }
                minIndex--;
            }
        }
    }

    public static void shellSort(int[] array){
        int length = array.length;
        for(int gap = length/2; gap > 0; gap/=2){
            for(int j = gap; j < length; j++){
                int index = j;
                while(index - gap >= 0 && array[index] < array[index-gap]){
                        //交换元素
                        int num = array[index];
                        array[index] = array[index-gap];
                        array[index-gap] = num;
                        index -= gap;
                }
            }
        }
    }

    static class TestModel{
        private Integer minValue;

        private Integer maxValue;

        public Integer getMinValue() {
            return minValue;
        }

        public void setMinValue(Integer minValue) {
            this.minValue = minValue;
        }

        public Integer getMaxValue() {
            return maxValue;
        }

        public void setMaxValue(Integer maxValue) {
            this.maxValue = maxValue;
        }


        public TestModel() {
        }

        public TestModel(Integer minValue, Integer maxValue) {
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }

}
