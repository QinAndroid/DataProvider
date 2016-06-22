package com.qzk.dataprovider;

/**
 * 类名：MainTest
 * 描述：
 * 包名： com.qzk.dataprovider
 * 项目名：DataProvider
 * Created by qinzongke on 6/22/16.
 */
public class MainTest {

    public static void main(String[] args) {
        String str = "aaaaaa";
        test(str);
        System.out.println(str);
        String s = new String("assasasas");
        test(s);
        System.out.println(s);
        TT t = new TT();
        t.setStr("aaa_____");
        t(t);
        System.out.print(t.getStr());

    }

    private static void test(String str) {
        str = "ssssssss";
    }
    private static void t(TT t){
        t.setStr("a------>");
    }

   static class TT{
        private String str;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }
}
