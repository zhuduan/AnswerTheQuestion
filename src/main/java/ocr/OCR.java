package ocr;

import model.QuestionAndAnswer;


public interface OCR {
    
    QuestionAndAnswer getQuestionAndAnswer(byte[] image);
}
