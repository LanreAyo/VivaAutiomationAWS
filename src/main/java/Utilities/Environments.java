package Utilities;

import java.util.HashMap;

public  class  Environments {

    public static String baseUrl = "http://www.web03.vivastreet.co.uk";
    public static String baseUrlLive = "http://vivastreet.co.uk";



    public static String GetEnvironment(TestEnvironments tee){
        HashMap testEnvironments = new HashMap();
        testEnvironments.put(TestEnvironments.Live,baseUrlLive);
        testEnvironments.put(TestEnvironments.Stage,baseUrl);

        return   testEnvironments.get(tee).toString();
    }

    public enum TestEnvironments {
        Live,
        Stage
    }
}