package com.team.service;

import com.team.model.ChosenAnswer;

public interface AnswerService {
    
    ChosenAnswer getAnswerByName(String gameName);
}
