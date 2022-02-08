package com.transfer.account.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiuQi
 * @date 2022/1/21 15:02
 */
public class SingletonModel {
    // 饿汉 懒汉 IoDH
    public static class EHanSingleton{
        private static final EHanSingleton singleton = new EHanSingleton();
        private static List<String> serverList = null;

        private EHanSingleton(){
            serverList = new ArrayList<>(16);
        }

        public static EHanSingleton instance(){
            return singleton;
        }

        public void addServer(String server){
            serverList.add(server);
        }

        public void removeServer(String server){
            serverList.remove(server);
        }
    }

    public static class LanHanSingleton{
        private static LanHanSingleton singleton = null;
        private static List<String> serverList = null;

        private LanHanSingleton(){
            serverList = new ArrayList<>(16);
        }

        public static LanHanSingleton instance(){
            if(singleton == null){
                synchronized (LanHanSingleton.class){
                    if(singleton == null){
                        singleton = new LanHanSingleton();
                    }
                }
            }
            return singleton;
        }

        public void addServer(String server){
            serverList.add(server);
        }

        public void removeServer(String server){
            serverList.remove(server);
        }
    }

    public static class IoDHSingleton{
        private static List<String> serverList = null;

        private IoDHSingleton(){
            serverList = new ArrayList<>(16);
        }

        private static class HolderClass {
            private static final IoDHSingleton sin = new IoDHSingleton();
        }

        public static IoDHSingleton instance(){
           return HolderClass.sin;
        }

        public void addServer(String server){
            serverList.add(server);
        }

        public void removeServer(String server){
            serverList.remove(server);
        }
    }

}