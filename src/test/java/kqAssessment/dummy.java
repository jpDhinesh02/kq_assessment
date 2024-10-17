package kqAssessment;

import mailer.sendMail;

public class dummy {
    public static void main(String[] args) throws Exception {
        // fun(1, 2, 3, 4,5,6,7);
        sendMail.killProcessOnPort();
    }

    static void fun(int... a) {
        System.out.println("Number of arguments: "+ a.length);
        int j =0;
        for (int i : a)
          j=i+j;
        System.out.println("sum of fun>>> "+j);
    }
}
