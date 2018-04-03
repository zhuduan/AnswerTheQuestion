package com.team.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * store the all answers from different game
 *      use a class not a map here, for we can expand the functions and can initial the map at the very beginning
 *
 * @author Haifeng.Zhu
 *         created at 4/3/18
 */
public class AnswerMap {
    
    private static volatile Map<String, ChosenAnswer> answers = new ConcurrentHashMap<>();
    
    public static ChosenAnswer get(String key){
        return answers.get(key);
    }
    
    public static void put(String key, ChosenAnswer value){
        answers.put(key, value);
    }
}
