package com.team.model;

import com.team.common.Config.*;

/**
 * the chosen answer structure
 *
 * @author Haifeng.Zhu
 *         created at 2/7/18
 */
public class ChosenAnswer {

    private String question = "no question for now";
    private Integer chooseIndex = -1;
    private String answer = "no answer for now";
    private AnalysisMethod analysisMethod = AnalysisMethod.DEFAULT;

    public ChosenAnswer() {
        this.chooseIndex = -1;
        this.answer = "no answer for now";
        this.question = "no question for now";
    }

    public ChosenAnswer(Integer chooseIndex, String answer, AnalysisMethod analysisMethod, String question) {
        this.chooseIndex = chooseIndex;
        this.answer = answer;
        this.analysisMethod = analysisMethod;
        this.question = question;
    }

    public ChosenAnswer(AnalysisMethod analysisMethod) {
        this.analysisMethod = analysisMethod;
    }

    public Boolean isValidAnswer(){
        return (chooseIndex>=0);
    }

    public Integer getChooseIndex() {
        return chooseIndex;
    }

    public void setChooseIndex(Integer chooseIndex) {
        this.chooseIndex = chooseIndex;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public AnalysisMethod getAnalysisMethod() {
        return analysisMethod;
    }

    public void setAnalysisMethod(AnalysisMethod analysisMethod) {
        this.analysisMethod = analysisMethod;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString(){
        return "From " + this.analysisMethod.toString() + "\r\n" 
                + "Answer is " + (chooseIndex+1) + " : " + answer + "\r\n"
                + "Question is : " + question ;
    }
}
