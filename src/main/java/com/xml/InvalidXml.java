package com.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

/**
 * @author Rubbck
 * @date 2020/11/2-10:04
 */
public class InvalidXml {
    public static void main(String[] args) {

        // 测试的字符串应该为：<r><c d="s" n="j"></c></r>
        // 正常的对应的byte数组为
        byte[] ba1 = new byte[] { 60, 114, 62, 60, 99, 32, 100, 61, 34, 115,
                34, 32, 110, 61, 34, 106, 34, 62, 60, 47, 99, 62, 60, 47, 114,
                62 };
        System.out.println("ba1     length=" + ba1.length);
        String ba1str = new String(ba1);
        System.out.println(ba1str);
        System.out.println("ba1str  length=" + ba1str.length());
        System.out.println("-----------------------------------------");
        // 和正常的byte 数组相比 多了一个不可见的 31
        byte[] ba2 = new byte[] { 60, 114, 62, 60, 99, 32, 100, 61, 34, 115,
                34, 32, 110, 61, 34, 106, 31, 34, 62, 60, 47, 99, 62, 60, 47,
                114, 62 };
        System.out.println("ba2     length=" + ba2.length);
        String ba2str = new String(ba2);
        System.out.println(ba2str);
        System.out.println("ba2str  length=" + ba2str.length());
        System.out.println("-----------------------------------------");
        try {
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory
                    .newInstance();
            dbfactory.setIgnoringComments(true);
            DocumentBuilder docBuilder = dbfactory.newDocumentBuilder();

            // 过滤掉非法不可见字符 如果不过滤 XML解析就报异常
            String filter = ba2str.replaceAll(
                    "[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
            String ss = "<entity> <Ckbill __type=\"sdo:dybiz.loanp.biz.pojo.Ckbill\" __id=\"0\"><id __type=\"java:java.lang.Long\">37270</id><ruleid __type=\"java:java.lang.Long\">0</ruleid><code __type=\"java:java.lang.String\">20200702</code><title __type=\"java:java.lang.String\">关于客户( 彭丽 )的日常检查工单</title><depid __type=\"java:java.lang.Long\">44</depid><depname __type=\"java:java.lang.String\">上冈支行</depname><empida __type=\"java:java.lang.Long\">355</empida><empnamea __type=\"java:java.lang.String\">王正悟</empnamea><empidb __type=\"java:java.lang.Long\">340</empidb><empnameb __type=\"java:java.lang.String\">邵林法</empnameb><custid __type=\"java:java.lang.Long\">1549424</custid><custname __type=\"java:java.lang.String\">彭丽</custname><linktel __type=\"java:java.lang.String\">15365799516</linktel><linkaddr __type=\"java:java.lang.String\">\u001C江苏省盐城市建湖县冈西镇创业北路43号\u001C</linkaddr><loanamt __type=\"java:java.math.BigDecimal\">200000.00</loanamt><loanbalamt __type=\"java:java.math.BigDecimal\">200000.00</loanbalamt><cktype __type=\"java:java.lang.String\">01</cktype><cktypedesc __type=\"java:java.lang.String\">征信、汇法网查询，电话联系</cktypedesc><ckreason __type=\"java:java.lang.String\">02</ckreason><ckreasondesc __type=\"java:java.lang.String\">季度检查</ckreasondesc><riskval __isNullOrEmpty=\"null\"/><processinstid __type=\"java:java.lang.Long\">66899</processinstid><creator __type=\"java:java.lang.String\">sql</creator><crttime __type=\"java:java.sql.Timestamp\">2020-09-28 21:09:11.943</crttime><modifier __type=\"java:java.lang.String\">王正悟</modifier><modtime __type=\"java:java.sql.Timestamp\">2020-11-02 09:35:10.8</modtime><delflag __type=\"java:java.lang.String\">0</delflag><ckdate __isNullOrEmpty=\"null\"/><entityname __type=\"java:java.lang.String\">dybiz.loanp.biz.pojo.Ckbill</entityname><entitycode __isNullOrEmpty=\"null\"/><loanacctbizlnks __collection=\"list\"/><moddate __type=\"java:java.util.Date\">2020-11-02 09:35:10</moddate><modempid __type=\"java:java.lang.Long\">355</modempid></Ckbill> </entity>";
            // 解析实例字符串
            System.out.println("过滤后的length=" + filter.length());
            ByteArrayInputStream bais = new ByteArrayInputStream(filter
                    .getBytes());

            Document doc = docBuilder.parse(bais);
            Element rootEl = doc.getDocumentElement();
            System.out.println("过滤后解析正常 root child length="
                    + rootEl.getChildNodes().getLength());
            //解析出错的字符串
            //后面会报错
            ByteArrayInputStream inputStream = new ByteArrayInputStream(ss.getBytes());
            Document doc1 = docBuilder.parse(inputStream);
            Element rootEl1 = doc1.getDocumentElement();
            System.out.println("过滤后解析正常 root child length="
                    + rootEl.getChildNodes().getLength());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

