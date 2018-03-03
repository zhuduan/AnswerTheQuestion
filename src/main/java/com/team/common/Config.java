package com.team.common;

/**
 * The config info 
 *
 * @author Haifeng.Zhu
 *         created at 1/23/18
 */
public class Config {

    // Customer mode : specific the tool you want to use
    public static final OcrMethod OCR_MODE = OcrMethod.BAIDU;
    public static final SearchMethod SEARCH_MODE = SearchMethod.BAIDU;

    // Baidu OCR : APPID/AK/SK - change to your params to use OCR
    public static final String APP_ID = "10735363";
    public static final String API_KEY = "le7sCcyhkwlC3TaQ1Ldz9yRy";
    public static final String SECRET_KEY = "GYMifAnU7LnszTApP9xS4lvvVkqgZdWP";

    // Google com.team.search : if you want to use google, should write your proxy here
    public static final String PROXY_ADDR = "sheraton.h.timonit.cn";
    public static final int PROXY_PORT = 15944;
    public static final String PROXY_USER = "duotai";
    public static final String PROXY_PASS = "xTXQ8-Atp747-Br2dJV";


    public enum OcrMethod {
        BAIDU(1),
        TESS(2);
        
        private int id;

        OcrMethod(int id) {
            this.id = id;
        }
    }

    public enum SearchMethod {
        BAIDU(1),
        GOOGLE(2);
        
        private int id;

        SearchMethod(int id) {
            this.id = id;
        }
    }
    
    public enum AnalysisMethod{
        DEFAULT(0, 1.0),
        HIT_NUM(1, 1.0),
        QUESTION_BANK(2, 1.0);
        
        private int id;
        private double weight;      // the answer's weight, using this to judge final answer from various method

        AnalysisMethod(int id, double weight) {
            this.id = id;
            this.weight = weight;
        }
    }

    public enum Schedule{

        PeekMeeting(1, "peekMeeting_noon", "13:00:00", true);

        private int id;
        private String name;
        private String startTime;
        private Boolean autoStart;

        Schedule(int id, String name, String startTime, Boolean autoStart) {
            this.id = id;
            this.name = name;
            this.startTime = startTime;
            this.autoStart = autoStart;
        }
    }
}
