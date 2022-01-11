package com.transfer.account.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author LiuQi
 * @date 2021/11/19 18:14
 */
public class Stack {
    private Node top = null;

    private static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

    public void push(int value){
        Node insertNode = new Node(value, null);
        if(top == null){
            top = insertNode;
            return;
        }

        insertNode.next = top;
        top = insertNode;
    }

    public static void main(String[] args) {
        List<Node> list = new ArrayList<>(10);
        List<Node> list2 = new ArrayList<>(10);
        list.addAll(list2);

        Map<Integer, Integer> map = new HashMap<>(16);
        map = list.stream().collect(Collectors.toMap(Node::getData, Node::getData));
        System.out.println(map);
    }


}
