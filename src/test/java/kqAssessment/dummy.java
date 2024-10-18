package kqAssessment;
import utility.Components;
import utility.getPassword;
import utility.jsonUtilites;

public class dummy {
    public static void main(String[] args) throws Exception {
        String userName = "dhinesh.p@knowledgeq.com";
     System.out.println( getPassword.getLoginEmailOtp(userName));
     System.out.println( getPassword.getUserPassword(userName));
      System.out.println(getPassword.getForgotPasswordMobileOtp(userName));
    }

}
