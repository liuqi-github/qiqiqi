package com.transfer.account.design;

/**
 * @author LiuQi
 * @date 2021/6/29 11:00
 * 工厂模式
 */
public class facotryPattern {
    /*
    工厂模式三种： 1.简单工厂模式 2.工厂模式 3.抽象工厂模式
    1.简单工厂模式 打个比方 一个餐馆生产多种拉面
    2.工厂模式 多个餐馆生产不同的拉面
    3.抽象工厂模式 多个工厂不止生产拉面还生产其他附属食品比如盖饭、凉面、拌面（抽象工厂，具体工厂，抽象产品，具体产品）
    */
    static abstract class Factory{
        abstract Noodle noodle();

        abstract Vegetable vegetable();
    }


    static class SiJiMianGuan extends Factory{
        @Override
        Noodle noodle() {
            return new LanZhouLaMian();
        }

        @Override
        Vegetable vegetable() {
            return new HuLuoBo();
        }
    }

    class DaChuXiaoCai extends Factory{
        @Override
        Noodle noodle() {
            return new XinJiangLaMian();
        }

        @Override
        Vegetable vegetable() {
            return new XiLanHua();
        }
    }


    interface Noodle{
        void display();
    }

    interface Vegetable{
        void display();
    }

    static class LanZhouLaMian implements Noodle{
        @Override
        public void display() {
            System.out.println("生产兰州拉面");
        }
    }

    static class XinJiangLaMian implements Noodle{
        @Override
        public void display() {
            System.out.println("生产新疆拉面");
        }
    }

    static class HuLuoBo implements Vegetable{
        @Override
        public void display() {
            System.out.println("配菜胡萝卜");
        }
    }

    class XiLanHua implements Vegetable{
        @Override
        public void display() {
            System.out.println("配菜西兰花");
        }
    }


    public static void main(String[] args) {
        /*
        工厂方法模式总结
        工厂方法模式是简单工厂模式的延伸，它继承了简单工厂模式的优点，同时还弥补了简单工厂模式的不足。工厂方法模式是使用频率最高的设计模式之一，是很多开源框架和API类库的核心模式。
        1. 主要优点
        工厂方法模式的主要优点如下：
        (1) 在工厂方法模式中，工厂方法用来创建客户所需要的产品，同时还向客户隐藏了哪种具体产品类将被实例化这一细节，用户只需要关心所需产品对应的工厂，无须关心创建细节，甚至无须知道具体产品类的类名。
        (2) 基于工厂角色和产品角色的多态性设计是工厂方法模式的关键。它能够让工厂可以自主确定创建何种产品对象，而如何创建这个对象的细节则完全封装在具体工厂内部。工厂方法模式之所以又被称为多态工厂模式，就正是因为所有的具体工厂类都具有同一抽象父类。
        (3) 使用工厂方法模式的另一个优点是在系统中加入新产品时，无须修改抽象工厂和抽象产品提供的接口，无须修改客户端，也无须修改其他的具体工厂和具体产品，而只要添加一个具体工厂和具体产品就可以了，这样，系统的可扩展性也就变得非常好，完全符合“开闭原则”。
        2. 主要缺点
        工厂方法模式的主要缺点如下：
        (1) 在添加新产品时，需要编写新的具体产品类，而且还要提供与之对应的具体工厂类，系统中类的个数将成对增加，在一定程度上增加了系统的复杂度，有更多的类需要编译和运行，会给系统带来一些额外的开销。
        (2) 由于考虑到系统的可扩展性，需要引入抽象层，在客户端代码中均使用抽象层进行定义，增加了系统的抽象性和理解难度，且在实现时可能需要用到DOM、反射等技术，增加了系统的实现难度。
        3. 适用场景
        在以下情况下可以考虑使用工厂方法模式：
        (1) 客户端不知道它所需要的对象的类。在工厂方法模式中，客户端不需要知道具体产品类的类名，只需要知道所对应的工厂即可，具体的产品对象由具体工厂类创建，可将具体工厂类的类名存储在配置文件或数据库中。
        (2) 抽象工厂类通过其子类来指定创建哪个对象。在工厂方法模式中，对于抽象工厂类只需要提供一个创建产品的接口，而由其子类来确定具体要创建的对象，利用面向对象的多态性和里氏代换原则，在程序运行时，子类对象将覆盖父类对象，从而使得系统更容易扩展。
          */
        System.out.println("去四季面馆吃饭");
        Factory factory = new SiJiMianGuan();
        Noodle noodle = factory.noodle();
        Vegetable vegetable = factory.vegetable();
        noodle.display();
        vegetable.display();

        Father father = new Son();
        System.out.println("年龄是" + father.a + "岁");

        Son son = (Son)father;
        System.out.println("年龄是" + son.a + "岁");
    }


    static class Father{
        int a = 20;
        void display(){
            System.out.println("我是爸爸");
        }
    }

    static class Son extends Father{
        int a = 10;

        @Override
        void display(){
            System.out.println("我是儿子");
        }
    }



}
