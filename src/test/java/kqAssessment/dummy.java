package kqAssessment;

import mailer.sendMail;
import utility.Components;

public class dummy {

    public static void main(String[] args) throws Exception {
        Components.printColorful("Hello", "green_Bold");
        // sendMail.sendMailToUser("dhinesh.p@knowledgeq.com", null);
        System.out.println(sendMail.getLatestReport());
    }

}