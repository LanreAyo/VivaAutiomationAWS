package Utilities;

import java.time.LocalDateTime;

public class EmailGenerator {

    public String GenerateEmail(String email) {
        LocalDateTime date = LocalDateTime.now();
        String datee = date.toString();
        String  finalEmail = email +datee.substring(18,23)+datee.substring(18,23)+"@dm-companies.com";

        return finalEmail;
    }

    public  static String GenerateInvalidEmail() {
        return "";
    }
}
