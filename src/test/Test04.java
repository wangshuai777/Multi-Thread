package test;

/**
 * Created by wangshuai on 2018/2/8.
 */
public class Test04 {
    public static boolean isLoop(Node node){
        if(node==null||node.next==null){
            return false;
        }
        Node slow=node;
        Node fast=node.next;
        while(fast!=null){


        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Math.log(100)/Math.log(10));
    }

    public static void changeStr(String str){
        str="CCC";
    }

    public static  void changeName(Student s){
          s.name="aaa";
    }

    static class Student{
        int id;
        String name;
        public Student(String name){
            this.name=name;
        }
    }

    class Node {
        String value;
        Node next;
    }
}
