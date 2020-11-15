package com.stack;


/**
 * @author Rubbck
 * @date 2020/9/12-15:59
 * 用java 实现栈
 * 使用链表实现栈
 */
public class MyNodeStack<T> {
    /**
     * 定有链表类
     * @param <T> 数据类型
     */
  class Node<T>{
      //数据
      private T data;
      //node类本身
      private Node<T> next;
    //初始化链表
      public Node(T data) {
          this.data = data;
      }
      //获取下一个节点
      public Node<T> getNext(){
          return  this.next;
      }
      //设置下一个节点
      public void setNext(Node<T> next){
          this.next = next;
      }
      //获取节点数据
      public T getData(){
         return this.data;
      }
      //设置节点数据
      public void setData(T d){
          this.data = d;
      }

  }

    private Node<T> top = null; //栈顶

    public MyNodeStack() {
        this.top = null;
    }

    public boolean isEmpty(){
        if (top!=null){
            return false;
        }
        return  true;
    }
    public boolean push(T value){
        Node<T> node = new Node<T>(value);
        node.setNext(top);
        top = node;
        return  true;
    }
    public T pop(){
        if(isEmpty()){
            return  null;
        }
        T temp = top.data;
        top=top.getNext();
        return temp;
    }
    public T peek(){
        if(isEmpty()){
            return null;
        }
        return top.data;
    }

    public static void main(String[] args) {
        MyNodeStack<String> ns=new MyNodeStack<String>();

        //测试是否为空
        System.out.println("=======是否为空======");
        System.out.println(ns.isEmpty());
        System.out.println("=============");
        //压栈测试
        System.out.println("=======压栈======");
        ns.push("北京");
        ns.push("上海");
        ns.push("深证");
        ns.push("广州");
        //是否为空
        System.out.println("=======是否为空======");
        System.out.println(ns.isEmpty());
        System.out.println("=============");

        System.out.println("=======出栈=======");
        //出栈
        System.out.println(ns.pop());
        System.out.println(ns.pop());
        System.out.println(ns.pop());
        System.out.println(ns.pop());
        System.out.println(ns.pop());

        //是否为空
        System.out.println("=======是否为空======");
        System.out.println(ns.isEmpty());
        System.out.println("=============");
    }

}
