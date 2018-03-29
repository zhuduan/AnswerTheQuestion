package com.team.controller;

import com.team.common.GameConfig_Default;
import com.team.model.QuestionAndAnswer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.team.utils.ResponseUtils;

import java.util.ArrayList;
import java.util.Map;

@RestController()
@RequestMapping(value = "answer")
public class AnswerController {

    @RequestMapping(value = "game/{name}", method = RequestMethod.GET)
    public Map<String, Object> getAnswer(@PathVariable("name") String name) throws Exception{
        QuestionAndAnswer questionAndAnswer = new QuestionAndAnswer("hello", new ArrayList<>(), new GameConfig_Default());
        return ResponseUtils.toSuccess(questionAndAnswer);
    }
}
