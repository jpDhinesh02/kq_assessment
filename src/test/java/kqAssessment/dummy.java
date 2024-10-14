package kqAssessment;

import utility.getPassword;

public class dummy {
    public static void main(String[] args) throws Exception {
        System.out.println("getLoginEmailOtp>>>>" + getPassword.getLoginEmailOtp("dhinesh.p@knowledgeq.com"));
        System.out.println("getLoginMobileOtp>>>>" + getPassword.getLoginMobileOtp("dhinesh.p@knowledgeq.com"));
        System.out.println(
                "getforgotPasswordEmailOtp>>>>" + getPassword.getForgotPasswordEmailOtp("dhinesh.p@knowledgeq.com"));
        System.out.println(
                "getforgotPasswordMobileOtp>>>>" + getPassword.getForgotPasswordMobileOtp("dhinesh.p@knowledgeq.com"));
        System.out.println("getpassword>>>>" + getPassword.getUserPassword("dhinesh.p@knowledgeq.com"));
        // sendMail.sendMailToUser("dhinesh.p@knowledgeq.com");
    }
}
