package common;

/**
 * The config info 
 *
 * @author Haifeng.Zhu
 *         created at 1/23/18
 */
public class Config {
    
    
    public enum OcrMethod {
        BAIDU(1),
        Tess(2);
        
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
    
    
    public enum AnalysisMethod {
        FREQUENCE_STATISTIC(1, 1.0),
        OTHER(2, 1.0);
        
        private int id;
        private double weight;

        AnalysisMethod(int id, double weight) {
            this.id = id;
            this.weight = weight;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }
    }
    
    
    public enum GameType {
        PEEK_MEETING(1),
        MILLION_HERO(2);
        
        private int id;

        GameType(int id) {
            this.id = id;
        }
    }


    // Baidu Ocr : APPID/AK/SK
    public static final String APP_ID = "10735363";
    public static final String API_KEY = "le7sCcyhkwlC3TaQ1Ldz9yRy";
    public static final String SECRET_KEY = "GYMifAnU7LnszTApP9xS4lvvVkqgZdWP";
    
}
