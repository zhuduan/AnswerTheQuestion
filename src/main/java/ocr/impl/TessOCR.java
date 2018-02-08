package ocr.impl;

import common.GameConfig;
import model.QuestionAndAnswer;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;
import ocr.OCR;

import java.io.File;

/**
 * Created by 618 on 2018/1/8.
 *
 * @author lingfengsan
 */
public class TessOCR implements OCR {
    
    private ITesseract instance;
    
    private GameConfig config = null;

    public TessOCR(GameConfig config) {
        instance = new Tesseract();
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        instance.setLanguage("chi_sim");
        //Set the tessdata path
        instance.setDatapath(tessDataFolder.getAbsolutePath());
        System.out.println("欢迎您使用TessOCR进行文字识别");
    }
    
    @Override
    public QuestionAndAnswer getQuestionAndAnswer(byte[] image) {
        return null;
    }

    public static void main(String[] args) {
//        String path = "src/resource/screenshot.png";
//        TessOCR tessOCR = new TessOCR();
//        System.out.println(tessOCR.getOCR(new File(path)));
    }
}
