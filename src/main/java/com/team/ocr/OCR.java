package com.team.ocr;

import com.team.model.QuestionAndAnswer;


public interface OCR {
    
    QuestionAndAnswer getQuestionAndAnswer(byte[] image);
}
