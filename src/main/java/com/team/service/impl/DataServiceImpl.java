package com.team.service.impl;

import com.team.Main;
import com.team.analysis.Analysis;
import com.team.analysis.HitNumAnalysis;
import com.team.analysis.QuestionBankAnalysis;
import com.team.common.GameConfig;
import com.team.model.AnswerMap;
import com.team.model.ChosenAnswer;
import com.team.service.DataService;
import com.team.utils.Factories;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * purpose of this class
 *
 * @author Haifeng.Zhu
 * created at 5/18/18
 */
@Service
public class DataServiceImpl implements DataService {

    @Override
    public Boolean setAnswer(Integer gameID, GameConfig gameConfig, byte[] imgBytes) {
        // use a-syn method to do the analysis, 
        Factories.getThreadPool().submit(() -> {
            ChosenAnswer chosenAnswer = getAnswer(imgBytes, gameConfig);
            AnswerMap.put(gameID, chosenAnswer);
        });
        return true;
    }

    // get the answer due to different method,
    // and choose one from them
    private ChosenAnswer getAnswer(byte[] imgData, GameConfig gameConfig) {
        Analysis hitNumAnalysis = new HitNumAnalysis(imgData, gameConfig);
        Analysis questionBankAnalysis = new QuestionBankAnalysis(imgData, gameConfig);

        Future<ChosenAnswer> hitNumFuture = Factories.getThreadPool().submit(hitNumAnalysis);
        Future<ChosenAnswer> questionBankFuture = Factories.getThreadPool().submit(questionBankAnalysis);

        // according to the strategy, choose the most valuable answer
        //    todo: may be weight average can be used here
        try {
            ChosenAnswer questionBankAnswer = questionBankFuture.get(gameConfig.getChosen_answer_timeout(), TimeUnit.MILLISECONDS);
            if (questionBankAnswer.isValidAnswer()) {
                return questionBankAnswer;
            }
        } catch (Exception exp) {
            // log some info
        }
        try {
            ChosenAnswer hitNumBankAnswer = hitNumFuture.get(gameConfig.getChosen_answer_timeout(), TimeUnit.MILLISECONDS);
            return hitNumBankAnswer;
        } catch (Exception exp) {

        }
        return new ChosenAnswer();
    }
}
