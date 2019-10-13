package com.bits.pieces.spring.mongo;

import org.aspectj.weaver.ast.Test;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 10/8/2019
 */
public class Account {
    public static void main(String[] args) {
//        Account test = new Account();
        meth(args);
    }
    public Integer getNumber(){
        return 2;
    }
    public void setNumber(Integer n)
    {

    }
    public void myMethod(Runnable r){

    }

    static class Helper {
        private int data = 5;
        public void bump(int inc) {
            inc++;
            data = data +inc;
        }
    }

    public static void meth(String[] arg){
        System.out.println(arg);
        System.out.println(arg[1]);
    }
}
