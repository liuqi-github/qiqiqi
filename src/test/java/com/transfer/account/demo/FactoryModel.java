package com.transfer.account.demo;

/**
 * @author LiuQi
 * @date 2022/1/20 10:51
 */
public class FactoryModel {

    public interface Factory {
        Vegetable zhongcai();
    }

    public static class GenVegetableFactory implements Factory{
        @Override
        public Vegetable zhongcai(){
            return new GenVegetable();
        }
    }

    public static class JingVegetableFactory implements Factory{
        @Override
        public Vegetable zhongcai(){
            return new JingVegetable();
        }
    }


    interface Vegetable {
        void make();
    }


    public static class GenVegetable implements Vegetable {
        @Override
        public void make() {
            System.out.println("种植土豆");
        }
    }

    public static class JingVegetable implements Vegetable {
        @Override
        public void make() {
            System.out.println("种植胡萝卜");
        }
    }

    public static void main(String[] args) {
        Factory factory = new GenVegetableFactory();
        Vegetable vegetable = factory.zhongcai();
        vegetable.make();
    }

}
