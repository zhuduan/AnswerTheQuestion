package com.team.service.impl;

import com.team.model.AnswerMap;
import com.team.model.ChosenAnswer;
import com.team.service.AnswerService;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    
    @Override
    public ChosenAnswer getAnswerByName(String gameName) {
        ChosenAnswer chosenAnswer =  AnswerMap.get(gameName);
        
        // if no answer here, should return  the default value 
        if ( chosenAnswer==null ){
            chosenAnswer = new ChosenAnswer();
        }
        return chosenAnswer;
    }
}
