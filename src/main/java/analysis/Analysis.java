package analysis;

import common.Config;
import common.GameConfig;
import common.GameConfig_PeekMeeting;
import model.ChosenAnswer;
import model.QuestionAndAnswer;
import ocr.OCR;
import utils.Factories;
import utils.ImageUtil;

import java.util.concurrent.Callable;

/**
 * get the answer with different method
 *      :  use template pattern to allow various implementations
 *
 * @author Haifeng.Zhu
 *         created at 1/26/18
 */
public abstract class Analysis implements Callable<ChosenAnswer> {

    protected byte[] imgBytes = null;
    protected GameConfig gameConfig = null;


    public Analysis(byte[] imgBytes, GameConfig gameConfig) {
        this.imgBytes = imgBytes;
        this.gameConfig = gameConfig;
    }

    public abstract ChosenAnswer getAnswer(QuestionAndAnswer questionAndAnswer);

    //
    @Override
    public final ChosenAnswer call() throws Exception {
        OCR ocrMethod = Factories.getOcrMethod(Config.OCR_MODE, gameConfig);
        QuestionAndAnswer questionAndAnswer = ocrMethod.getQuestionAndAnswer(imgBytes);

        // TODO: think if here need some hook

        return getAnswer(questionAndAnswer);
    }

    public static void main(String[] args) throws Exception {
        GameConfig_PeekMeeting config = new GameConfig_PeekMeeting();
        String path = "src/resource/screenshot_after_cut.png";
        byte[] imgBytes = ImageUtil.getByteFromImage(ImageUtil.cutImage(path, config),config);
        HitNumAnalysis analysis = new HitNumAnalysis(imgBytes, config);
        ChosenAnswer chosenAnswer = analysis.call();
        System.out.println(chosenAnswer.toString());
    }
}
