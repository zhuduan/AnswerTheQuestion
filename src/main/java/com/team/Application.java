package com.team;

import com.team.common.Config;
import com.team.model.AnswerMap;
import com.team.model.ChosenAnswer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan
public class Application {
    public static void main(String[] args) throws Exception {
        // 1. start the background dealing answer service
        //      todo: use multiple threads to calculate the answer and set the result to global map
        AnswerMap.put("冲顶大会", new ChosenAnswer(1, "这就是答案", Config.AnalysisMethod.DEFAULT, "这就是问题"));
        
        // 2. start the web service
        SpringApplication.run(Application.class, args);
    }    
}
