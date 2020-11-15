package com.stack;

import java.util.Arrays;

/**
 * @author Rubbck
 * @date 2020/9/12-11:03
 * 用 java 实现栈 先进后出
 * 参考地址 : https://www.cnblogs.com/wugongzi/p/11187183.html
 *          https://blog.csdn.net/weixin_43533825/article/details/96708590
 *
 *  使用数组方式
 */
public class MyStack<T> {
    private T data[];
    private int maxSize;
    private  int  top;

    /**
     * 初始化栈
     * @param maxSize 栈的大小
     */
    public MyStack(int maxSize) {
        data = (T[])new Object[maxSize];
        this.top = -1;
        this.maxSize = maxSize;
    }

    /**
     * 判断栈时候为空
     * @return
     */
    public boolean isEmpty(){
        return (top==-1);
    }

    /**
     * 判断栈是否满栈
     * @return
     */
    public boolean isFull(){
        return (top==maxSize-1);
    }

    /**
     * 入栈操作
     * @param value 入栈元素
     * @return
     */
    public boolean push (T value){
        if(isFull()){
            return false;
        }
        top++;
        data[top] = value;
        return true;
    }

    /**
     * 取出栈顶元素
     * @return
     */
    public T pop(){
        if(isEmpty()){
            return  null;
        }
        T temp = data[top];
        data[top] = null;
        top--;
        return temp;
    }

    /**
     * 给栈扩大容量
     * @param size
     */
    public void expandCapacity(int size){
        int len = data.length;
        if(size>len){
            size = size * 3 / 2 +1; //每次扩大50%
            data = Arrays.copyOf(data,size);
        }
    }

    public static void main(String[] args) {
        MyStack<String> myStack = new MyStack<String>(4);
        myStack.push("anhui");
        myStack.push("shanghai");
        myStack.push("beijing");
        myStack.push("nanj");
       //测试栈已经满了的情况  返回false  表示入栈失败
      System.out.println(myStack.push("aa"));
       for(int i=0;i<4;i++) {
         System.out.println(myStack.pop());
       }
    }



}
