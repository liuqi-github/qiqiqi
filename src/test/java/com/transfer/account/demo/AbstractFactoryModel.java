package com.transfer.account.demo;

/**
 * @author LiuQi
 * @date 2022/1/20 10:51
 */
public class AbstractFactoryModel {
    
    public interface Factory {
        Vegetable zhongGenCai();
        Vegetable zhongJingCai();
    }

    public static class ZhuanVegetableFactory implements Factory {
        public ZhuanVegetableFactory(){
            System.out.println("创建转基因工厂");
        }

        @Override
        public Vegetable zhongGenCai(){
            return new GenVegetable();
        }

        @Override
        public Vegetable zhongJingCai() {
            return new JingVegetable();
        }
    }

    public static class FeiVegetableFactory implements Factory {
        public FeiVegetableFactory(){
            System.out.println("创建非转基因工厂");
        }

        @Override
        public Vegetable zhongGenCai(){
            return new JingVegetable();
        }

        @Override
        public Vegetable zhongJingCai() {
            return new GenVegetable();
        }
    }


    interface Vegetable {
        void make();
    }


    public static class GenVegetable implements Vegetable {
        @Override
        public void make() {
            System.out.println("种植根菜");
        }
    }

    public static class JingVegetable implements Vegetable {
        @Override
        public void make() {
            System.out.println("种植茎菜");
        }
    }

    public static void main(String[] args) {
        Factory factory = new ZhuanVegetableFactory();
        Vegetable vegetable = factory.zhongGenCai();
        Vegetable jingcai = factory.zhongJingCai();
        vegetable.make();
        jingcai.make();
    }

}
