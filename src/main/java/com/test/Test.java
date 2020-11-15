package com.test;

import java.text.ParseException;

/**
 * @author Rubbck
 * @date 2020/11/2-15:36
 */
public class Test {
    public static void main(String[] args) throws ParseException {

        String end = null;
        String  o = (end ==  null || end == "")? null : String.valueOf(new java.text.SimpleDateFormat("yyyy/MM/dd").parse(end));
        System.out.println(o);
    }
}
