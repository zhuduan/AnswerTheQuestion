package com.team.analysis;

import com.team.common.Config.*;
import com.team.common.GameConfig;
import com.team.model.ChosenAnswer;
import com.team.model.QuestionAndAnswer;
import com.team.model.SearchResult;
import com.team.search.Search;
import com.team.utils.Factories;

/****
 * 
 * get the answer from question bank in the internet
 *      can return answer only when question and answer have the exactly same value
 * 
 */
public class QuestionBankAnalysis extends Analysis {
    
    private static final int ANSWER_EXTRA_CHAR_LEN = 20;
    private static final String ANSWER_KEYWORD = "答案";

    public QuestionBankAnalysis(byte[] imgBytes, GameConfig gameConfig) {
        super(imgBytes, gameConfig);
    }

    @Override
    public ChosenAnswer getAnswer(QuestionAndAnswer questionAndAnswer) {
        ChosenAnswer chosenAnswer = new ChosenAnswer(AnalysisMethod.QUESTION_BANK);
        Search searchMethod = Factories.getSearchMethod(SearchMethod.BAIDU, gameConfig, questionAndAnswer.getQuestion());
        try {
            // for only one com.team.search will be used, no multi thread needed here
            SearchResult searchResult = searchMethod.call();
            chosenAnswer = getAnswerInBank(questionAndAnswer, searchResult);
        } catch (Exception exp){
            return chosenAnswer;
        }
        return chosenAnswer;
    }

    /***
     * 
     * @return valid chosenAnswer, or invalid chosenAnswer if not hit the bank
     */
    private ChosenAnswer getAnswerInBank(QuestionAndAnswer questionAndAnswer, SearchResult searchResult){
        ChosenAnswer chosenAnswer = new ChosenAnswer();
        String question = questionAndAnswer.getQuestion();
        String content = searchResult.getContent();
        if ( !content.contains(question) ){
            return chosenAnswer;
        }
        
        // get the possible length of question + answer, and used this to cut the key info in content
        int questionAnswerLength = question.length();
        int longestAnswer = 0;
        for ( String answer : questionAndAnswer.getAnswers() ){
            questionAnswerLength = questionAnswerLength + answer.length();
            longestAnswer = longestAnswer<answer.length() ? answer.length() : longestAnswer;
        }
        questionAnswerLength = questionAnswerLength + longestAnswer + ANSWER_EXTRA_CHAR_LEN;
        
        // extract the key info we are interested
        // TODO: some bug here
        content = content.substring(content.indexOf(question),questionAnswerLength);
        if ( !content.contains(ANSWER_KEYWORD) ){
            return chosenAnswer;
        }
        
        // if the answer is contain in the Str means it is the right we found
        String answerStr = content.substring(content.indexOf(ANSWER_KEYWORD));
        for ( String answer : questionAndAnswer.getAnswers() ){
            if ( answerStr.contains(answer) ){
                chosenAnswer.setAnswer(answer);
                chosenAnswer.setChooseIndex(questionAndAnswer.getAnswers().indexOf(answer));
                chosenAnswer.setQuestion(questionAndAnswer.getQuestion());
                break;
            }
        }
        return chosenAnswer;
    }
}
