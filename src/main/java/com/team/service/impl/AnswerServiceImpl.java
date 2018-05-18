package com.team.service.impl;

import com.team.model.AnswerMap;
import com.team.model.ChosenAnswer;
import com.team.service.AnswerService;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    
    @Override
    public ChosenAnswer getAnswerByGameID(Integer gameID) {
        ChosenAnswer chosenAnswer =  AnswerMap.get(gameID);
        
        // if no answer here, should return  the default value 
        if ( chosenAnswer==null ){
            chosenAnswer = new ChosenAnswer();
        }
        return chosenAnswer;
    }
}
