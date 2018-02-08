import analysis.Analysis;
import analysis.HitNumAnalysis;
import analysis.QuestionBankAnalysis;
import common.GameConfig;
import common.GameConfig_PeekMeeting;
import model.ChosenAnswer;
import utils.Factories;
import utils.ImageUtil;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * purpose of this class
 *
 * @author Haifeng.Zhu
 *         created at 1/29/18
 */
public class Main {
    
    public static void main(String[] args){
        GameConfig_PeekMeeting config = new GameConfig_PeekMeeting();
        String path = "src/resource/screenshot_after_cut.png";
        byte[] imgBytes = ImageUtil.getByteFromImage(ImageUtil.cutImage(path, config),config);

        // use the answer:
        ChosenAnswer chosenAnswer = new Main().getAnswer(imgBytes, config);
        System.out.println(chosenAnswer.toString());
    }

    // get the answer due to different method,
    // and choose one from them
    private ChosenAnswer getAnswer(byte[] imgData, GameConfig gameConfig){
        Analysis hitNumAnalysis = new HitNumAnalysis(imgData, gameConfig);
        Analysis questionBankAnalysis = new QuestionBankAnalysis(imgData, gameConfig);

        Future<ChosenAnswer> hitNumFuture = Factories.getThreadPool().submit(hitNumAnalysis);
        Future<ChosenAnswer> questionBankFuture = Factories.getThreadPool().submit(questionBankAnalysis);

        // according to the strategy, choose the most valuable answer
        //    todo: may be weight average can be used here
        try{
            ChosenAnswer questionBankAnswer = questionBankFuture.get(gameConfig.getChosen_answer_timeout(), TimeUnit.MILLISECONDS);
            if ( questionBankAnswer.isValidAnswer() ){
                return  questionBankAnswer;
            }
        } catch (Exception exp){
            // log some info
        }
        try{
            ChosenAnswer hitNumBankAnswer = hitNumFuture.get(gameConfig.getChosen_answer_timeout(), TimeUnit.MILLISECONDS);
            return hitNumBankAnswer;
        } catch (Exception exp){

        }
        return new ChosenAnswer();
    }
    
}
