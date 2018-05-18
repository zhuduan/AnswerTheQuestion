package com.team.controller;

import com.team.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.team.utils.ResponseUtils;

import java.util.Map;

@RestController()
@RequestMapping(value = "answer")
public class AnswerController {
    
    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = "game/{id}", method = RequestMethod.GET)
    public Map<String, Object> getAnswer(@PathVariable("id") Integer id) throws Exception{
        return ResponseUtils.toSuccess(answerService.getAnswerByGameID(id));
    }
}
