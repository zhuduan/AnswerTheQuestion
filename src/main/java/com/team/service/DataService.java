package com.team.service;

import com.team.common.GameConfig;

/**
 * purpose of this class
 *
 * @author Haifeng.Zhu
 * created at 5/18/18
 */
public interface DataService {
    
    Boolean setAnswer(Integer gameID, GameConfig gameConfig, byte[] imgBytes);
}
