package com.stack;

/**
 * @author Rubbck
 * @date 2020/9/13-15:48
 * 用java实现共享栈  : 共享栈
 */
public class DoubleStack {
 private final static int MAXSIZE = 20;
 private int[] stackElem;
 private int top1; //将top1 设置为指向栈1 栈顶元素的位置
 private int top2; //将top2设置为指向栈2 栈顶元素的位置

    /**
     * 初始化栈
     */
    public DoubleStack() {
        top1 = -1;
        top2 = MAXSIZE;
        stackElem  = new int[MAXSIZE];
    }
    public boolean isEmptyStack(){
        if(top1==-1&&top2==MAXSIZE){
            return  true;
        }
        return false;
    }
    public void clearStack(){
        top2 = MAXSIZE;
        top1 = -1;
    }
    public int lengthStack(){
        return  (top1 +1)+(MAXSIZE -top2);
    }
    public int getTop1Elem(){
        if(top1 ==-1){
            return  -1;
        }
        return  stackElem[top1];
    }
    public int getTop2Elem(){
        if(top2 == MAXSIZE){
            return  -1;
        }
        return  stackElem[top2];
    }
    //入栈
    public void PushStack(int stackNumber,int e){
        if(top1+1 == top2){
            System.out.println("栈已满");
            return;
        }
        if(stackNumber ==1){
            top1 +=1;
            stackElem[top1] = e;
            return;
        }
        if(stackNumber ==2){
            top2 -=1;
            stackElem[top2] = e;
            return;
        }
    }
    //出栈
    public int PopStack(int stackNumber){
        int rs;
        if(isEmptyStack()){
            System.out.println("栈为空");
            return  -1;
        }
        if(stackNumber ==1){
            rs = stackElem[top1];
            top1--;
            return  rs;
        }
        if(stackNumber ==2){
            rs = stackElem[top2];
            top2++;
            return  rs;
        }
        return -1;
    }
    //横穿Travelse
    public void  TravelseStack(){
        System.out.println("栈中元素为: ");
        int i= 0;
        while (i<=top1){
            System.out.println(stackElem[i++]+"");
        }
        i = top2;
        while(i<MAXSIZE){
            System.out.println(stackElem[i++]+"");
        }
    }

    public static void main(String[] args) {

        DoubleStack seqStack=new DoubleStack();

        //1压栈
        for(int j=1;j<=5;j++) {
            seqStack.PushStack(1,j);
        }
        //2压栈
        for(int i=MAXSIZE;i>=MAXSIZE-2;i--) {
            seqStack.PushStack(2, i);
        }
        //输出
        seqStack.TravelseStack();
        System.out.println("栈的长度为："+seqStack.lengthStack());

        seqStack.PopStack(2);
        seqStack.TravelseStack();
        System.out.println("栈1的栈顶元素为: " + seqStack.getTop1Elem());
        System.out.println("栈2的栈顶元素为: " + seqStack.getTop2Elem());
        System.out.println("栈的长度为: " + seqStack.lengthStack());

        for (int i = 6; i <= MAXSIZE-2; i++) {
            seqStack.PushStack(1,i);
        }
        seqStack.TravelseStack();
        System.out.println("栈1的栈顶元素为: " + seqStack.getTop1Elem());
        System.out.println("栈2的栈顶元素为: " + seqStack.getTop2Elem());
        System.out.println("栈顶元素为: " + seqStack.getTop2Elem());
        System.out.println("栈的长度为: " + seqStack.lengthStack());

        System.out.println("栈是否为空: " + seqStack.isEmptyStack());
        seqStack.clearStack();
        System.out.println("栈是否为空: " + seqStack.isEmptyStack());
    }

}
