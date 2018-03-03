package com.team.analysis;

import com.team.common.Config.*;
import com.team.common.GameConfig;
import com.team.model.ChosenAnswer;
import com.team.model.QuestionAndAnswer;
import com.team.model.SearchResult;
import com.team.utils.Factories;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.team.common.Config.SEARCH_MODE;

/**
 * com.team.search the question + answer and then get the most/least hit num value as the answer
 *      future to do : if the difference of the hit number is not significant, should treat it as no answer
 *
 * @author Haifeng.Zhu
 *         created at 1/29/18
 */
public class HitNumAnalysis extends Analysis {

    public HitNumAnalysis(byte[] imgBytes, GameConfig gameConfig) {
        super(imgBytes, gameConfig);
    }

    @Override
    public ChosenAnswer getAnswer(QuestionAndAnswer questionAndAnswer) {
        // use multi thread com.team.model to com.team.search result
        List<Future<SearchResult>> futureList = new ArrayList<>();
        ThreadPoolExecutor threadPool = Factories.getThreadPool();
        questionAndAnswer.getAnswers().forEach( (answer) -> {
            String searchKey = questionAndAnswer.getQuestion() + " " + answer;
            futureList.add(threadPool.submit(Factories.getSearchMethod(SEARCH_MODE,  gameConfig, searchKey)));
        });
        
        // get all answer, and chose the one have most or least hit num
        int chosenAnswerIndex = -1;
        Long lastHitNum = -1L;
        boolean isNegativeQuestion = isNegativeQuestion(questionAndAnswer);
        for ( int index=0; index<futureList.size(); index++ ){
            try {
                Future<SearchResult> future = futureList.get(index);
                SearchResult result = future.get(gameConfig.getChosen_answer_timeout(), TimeUnit.MILLISECONDS);
                
                // if no valid result find, should return no answer
                if ( result.getHitNum()<0 ){
                    // log something
                    chosenAnswerIndex = -1;
                    break;
                }
                
                // initial the value
                if ( lastHitNum==-1 ){
                    lastHitNum = result.getHitNum();
                    chosenAnswerIndex = index;
                }
                
                // negative com.team.model, should choose the least hit num one
                if ( isNegativeQuestion && result.getHitNum()<lastHitNum ){
                    lastHitNum = result.getHitNum();
                    chosenAnswerIndex = index;
                }
                
                // positive com.team.model, should choose the most hit num one
                if ( !isNegativeQuestion && result.getHitNum()>lastHitNum ){
                    lastHitNum = result.getHitNum();
                    chosenAnswerIndex = index;
                }
                
            } catch (Exception exp){
                // if any exception occur, should just return no answer
                // log something
                chosenAnswerIndex = -1;
                break;
            }
        }
        
        ChosenAnswer chosenAnswer = new ChosenAnswer(AnalysisMethod.HIT_NUM);
        chosenAnswer.setChooseIndex(chosenAnswerIndex);
        chosenAnswer.setAnswer(questionAndAnswer.getAnswers().get(chosenAnswerIndex));
        return chosenAnswer;
    }
    
    // whether this is a "not" question
    private Boolean isNegativeQuestion(QuestionAndAnswer questionAndAnswer){
        // this judgement is simple, may use Tokenizer to do this in future
        if ( questionAndAnswer.getQuestion().contains("不是") || questionAndAnswer.getQuestion().contains("不属于") ){
            return true;
        }
        return false;
    }
}
