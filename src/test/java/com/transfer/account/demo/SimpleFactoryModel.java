package com.transfer.account.demo;

/**
 * @author LiuQi
 * @date 2022/1/20 10:51
 */
public class SimpleFactoryModel {

    public static class Factory {
        private String pizzaName;

        public Factory(String pizzaName){
            this.pizzaName = pizzaName;
        }

        public Vegetable zhongcai(){
            if("potato".equals(pizzaName)){
                return new Potato();
            }else if("carrot".equals(pizzaName)){
                return new Carrot();
            }
            return null;
        }
    }


    interface Vegetable {
        void make();
    }


    public static class Potato implements Vegetable {
        @Override
        public void make() {
            System.out.println("种植土豆");
        }
    }

    public static class Carrot implements Vegetable {
        @Override
        public void make() {
            System.out.println("种植胡萝卜");
        }
    }

    public static void main(String[] args) {
        Factory factory = new Factory("potato");
        Vegetable vegetable = factory.zhongcai();
        vegetable.make();
    }
}
