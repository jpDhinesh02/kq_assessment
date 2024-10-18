package kqAssessment;
import utility.getPasswordDijango;

public class dummy {
    public static void main(String[] args) throws Exception {
        System.out.println("getLoginEmailOtp>>>" + getPasswordDijango.getLoginEmailOtp("dhinesh.p@knowledgeq.com"));
        System.out.println("getForgotPasswordMobileOtp>>>"
                + getPasswordDijango.getForgotPasswordMobileOtp("dhinesh.p@knowledgeq.com"));
        System.out.println("getUserPassword>>>" + getPasswordDijango.getUserPassword("dhinesh.p@knowledgeq.com"));
    }

}
