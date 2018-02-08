package analysis;

import common.GameConfig;
import model.ChosenAnswer;
import model.QuestionAndAnswer;

public class QuestionBankAnalysis extends Analysis {

    public QuestionBankAnalysis(byte[] imgBytes, GameConfig gameConfig) {
        super(imgBytes, gameConfig);
    }

    @Override
    public ChosenAnswer getAnswer(QuestionAndAnswer questionAndAnswer) {
        return new ChosenAnswer();
    }
}
