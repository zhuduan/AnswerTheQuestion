package model;

import common.GameConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * purpose of this class
 *
 * @author Haifeng.Zhu
 *         created at 1/26/18
 */
public class QuestionAndAnswer {
    
    private String question;
    
    private List<String> answers;
    
    private GameConfig config;       // the config used for this Q&A

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public GameConfig getConfig() {
        return config;
    }

    public void setConfig(GameConfig config) {
        this.config = config;
    }

    public QuestionAndAnswer(String question, List<String> answers, GameConfig config) {
        this.question = question;
        this.config = config;
        this.answers = adjustAnswers(answers, config);
    }

    // to adjust the answer list, to avoid answer exceeding the max char per line limit
    private List<String> adjustAnswers(List<String> orinAnswerList, GameConfig config){
        if ( orinAnswerList.size()<=config.getQa_answer_max_num() ){
            return orinAnswerList;
        }
        
        // if answer size is large than max line, means must have long answer
        List<String> adjustedAnswerList = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder();
        Integer currentAnswerNum = orinAnswerList.size();
        for ( String answer : orinAnswerList ){
            if ( answer.length()>=config.getQa_txt_max_per_line() ){
                // if the current answer num == max num, means it is a just no linefeed ans
                if ( currentAnswerNum==config.getQa_answer_max_num() ){
                    adjustedAnswerList.add(answer);
                } else {
                    stringBuilder.append(answer);
                }
                continue;
            }
            
            if ( stringBuilder.length()<=0 ) {
                adjustedAnswerList.add(answer);
            } else {
                stringBuilder.append(answer);
                adjustedAnswerList.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                currentAnswerNum--;
            }
        }
        return adjustedAnswerList;
    }
    
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.question + "\r\n");
        for ( String ans : answers ){
            stringBuilder.append(ans + "\r\n");
        }
        return  stringBuilder.toString();
    }
}
