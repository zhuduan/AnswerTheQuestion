package ocr.impl;

import com.baidu.aip.ocr.AipOcr;
import common.GameConfig;
import common.GameConfig_PeekMeeting;
import model.QuestionAndAnswer;
import ocr.OCR;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.ImageUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 618 on 2018/1/12.
 * @author lingfengsan
 */
public class BaiDuOCR implements OCR{
    
    //设置APPID/AK/SK
    private static final String APP_ID = "10735363";
    private static final String API_KEY = "le7sCcyhkwlC3TaQ1Ldz9yRy";
    private static final String SECRET_KEY = "GYMifAnU7LnszTApP9xS4lvvVkqgZdWP";
    private static final AipOcr CLIENT=new AipOcr(APP_ID, API_KEY, SECRET_KEY);
    
    // set the internet params
    private static final Integer CONNECTION_TIMEOUT_MILLIONS = 2000;
    private static final Integer SOCKET_TIMEOUT_MILLIONS = 6000;
    
    private GameConfig config = null;
    
    public BaiDuOCR(GameConfig config){
       // 可选：设置网络连接参数
       CLIENT.setConnectionTimeoutInMillis(CONNECTION_TIMEOUT_MILLIONS);
       CLIENT.setSocketTimeoutInMillis(SOCKET_TIMEOUT_MILLIONS);
       
       // set config params
        this.config = config;
    }

    @Override
    public QuestionAndAnswer getQuestionAndAnswer(byte[] image) {
        JSONObject res = CLIENT.basicGeneral(image, new HashMap<String, String>());
        
        StringBuilder question = new StringBuilder();
        List<String> answerList = new ArrayList();
        Boolean isQuestionCompleted = false;
        
        JSONArray jsonArray=res.getJSONArray("words_result");
        for( Object element : jsonArray ){
            JSONObject jsonObject = new JSONObject(element.toString());
            String tempStr = jsonObject.get("words").toString();
            
            // if is answer, just add to the 
            if ( isQuestionCompleted ){
                answerList.add(tempStr);
                continue;
            }
            
            // if is question, should judge if is completed
            if ( tempStr.contains("?") || tempStr.length()<config.getQa_txt_max_per_line()){
                isQuestionCompleted = true;
            }
            question.append(tempStr);
        }
       return new QuestionAndAnswer(question.toString(), answerList, config);
    }

    public static void main(String[] args) {
        GameConfig_PeekMeeting config = new GameConfig_PeekMeeting();
        OCR ocr=new BaiDuOCR(config);
        String path = "src/resource/screenshot_after_cut.png";
        byte[] imgBytes = ImageUtil.getByteFromImage(ImageUtil.cutImage(path, config),config);
        QuestionAndAnswer questionAndAnswer = ocr.getQuestionAndAnswer(imgBytes);
        System.out.println(questionAndAnswer.toString());
    }
}
