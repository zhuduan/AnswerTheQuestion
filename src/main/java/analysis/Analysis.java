package analysis;

import common.GameConfig;
import model.ChosenAnswer;

/**
 * purpose of this class
 *
 * @author Haifeng.Zhu
 *         created at 1/26/18
 */
public interface Analysis {
    
    ChosenAnswer getAnswer(byte[] imgBytes, GameConfig config);
}
