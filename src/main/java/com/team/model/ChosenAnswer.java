package com.team.model;

import com.team.common.Config.*;

/**
 * the chosen answer structure
 *
 * @author Haifeng.Zhu
 *         created at 2/7/18
 */
public class ChosenAnswer {
    
    private Integer chooseIndex = -1;
    private String answer = "未知";
    private AnalysisMethod analysisMethod = AnalysisMethod.DEFAULT;

    public ChosenAnswer() {
        this.chooseIndex = -1;
        this.answer = "未知";
    }

    public ChosenAnswer(Integer chooseIndex, String answer, AnalysisMethod analysisMethod) {
        this.chooseIndex = chooseIndex;
        this.answer = answer;
        this.analysisMethod = analysisMethod;
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

    @Override
    public String toString(){
        return "From " + this.analysisMethod.toString() + "\r\n" 
                + "Answer is " + (chooseIndex+1) + " : " + answer;
    }
}
