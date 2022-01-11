package com.transfer.account.demo;

/**
 * @author LiuQi
 * @date 2021/11/16 15:42
 */
public class SingLinkedList {
    private static Node head = null;

    /**
     * 反转链表(带/不带头节点) 三指针移动的方式
     */
    public static ListNode reverseLinkedListWithoutHead(ListNode p){
        if(p == null || p.next == null){
            return p;
        }

        ListNode beg = null;
        ListNode mid = p;
        ListNode end = p.next;

        while(true){
            mid.next = beg;

            if(end == null){
                break;
            }
            beg = mid;
            mid = end;
            end = end.next;
        }

        //head.next = mid;
        return mid;
    }

    // 使用头插法反转链表  删除原链表头 删除的节点在新链表头插
    public static Node reverseLinkedListByInsertToHead(Node p){
        return null;
    }


    public static void insertBefore(Node p, Node insertNode){
        if(p == null && insertNode != null){
            head = insertNode;
        }

        if(head == p){
            insertNode.next = head;
            head = insertNode;
        }

        Node n = head;
        while(n.next != p){
            n = n.next;
            if(n == null){
                break;
            }
        }

        if(n != null) {
            insertNode.next = p;
            n.next = insertNode;
        }
    }


    public static void insertAfter(Node p, Node insertNode){
        if(p == null && insertNode != null){
            head = insertNode;
        }

        if(head == p){
            head.next = insertNode;
        }

        Node n = head;
        while(n != null && n.next != null){
            if(n == p){
                insertNode.next = p.next;
                p.next = insertNode;
                break;
            }
            n = n.next;
        }
    }

    public static void insertToHead(Node p, Node insertNode){
        insertNode.next = p;
//        return p;
    }

    public static void insertToLast(Node insertNode){
        Node n = head;
        while(n.next != null){
            n = n.next;
        }
        n.next = insertNode;
    }

    public static void deleteByNode(Node p){
        if(head == p){
            head = head.next;
        }

        if(p == null){
            return;
        }

        Node n = head;
        Node bef = null;
        while(n != null && n.next != null){
            bef = n;
            if(n.next == p){
                bef.next = p.next;
                break;
            }
            n = n.next;
        }
    }

    static class Node{
        private String data;
        private Node next;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node() {
        }
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static boolean hasCycle(ListNode head) {
        if(head == null || head.next == null || head.next.next == null){
            return false;
        }
        ListNode low = head;
        ListNode fast = head.next;
        while(low != fast){
            if(low.next == null || fast.next == null){
                return false;
            }
            low = low.next;
            fast = fast.next.next;
        }
        return true;
    }

    public static ListNode middleNode(ListNode head) {
        if(head == null){
            return null;
        }

        if(head.next == null){
            return head;
        }

        if(head.next.next == null){
            return head.next;
        }

        ListNode low = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            low = low.next;
            fast = fast.next.next;
        }
        return low;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dumy = new ListNode(0);
            dumy.next = head;
            ListNode low = dumy;
            ListNode fast = head;

            for(int i = 0; i < n; i++){
                fast = fast.next;
            }

            while(fast != null){
                low = low.next;
                fast = fast.next;
            }

            low.next = low.next.next;
            return dumy.next;
    }

    public static boolean isPalindrome(ListNode head) {
        if(head == null){
            return true;
        }

        ListNode low = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            low = low.next;
            fast = fast.next.next;
        }

        //奇数个
        ListNode reverse;
        if(fast == null){
            reverse = reverseLinkedListWithoutHead(low);
        }else{
            //偶数个
            reverse = reverseLinkedListWithoutHead(low.next);
        }

        return TRLIstNode(head, reverse);
    }


    public static boolean TRLIstNode(ListNode l, ListNode reverse){
        ListNode n = reverse;
        while(n != null){
            if(l.val != n.val){
                return false;
            }
            l = l.next;
            n= n.next;
        }
        return true;
    }

    public static void main(String[] args) {
//        Node a1 = new Node("a", null);
//        Node b1 = new Node("b", a1);
//        Node c1 = new Node("c", b1);

          ListNode d = new ListNode(1);
          ListNode c = new ListNode(2);
          ListNode b = new ListNode(2);
          ListNode a = new ListNode(1);

          a.next = b;
          b.next = c;
          c.next = d;

          System.out.println( isPalindrome(a));
    }





}
