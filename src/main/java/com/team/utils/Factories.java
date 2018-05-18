package com.team.utils;

import com.team.common.Config;
import com.team.common.GameConfig;
import com.team.common.Config.*;
import com.team.common.GameConfig_Default;
import com.team.common.GameConfig_MillionHero;
import com.team.common.GameConfig_PeekMeeting;
import com.team.common.ScheduleConfig;
import com.team.ocr.OCR;
import com.team.ocr.impl.BaiDuOCR;
import com.team.ocr.impl.TessOCR;
import com.team.search.Search;
import com.team.search.impl.BaiDuSearch;
import com.team.search.impl.GoogleSearch;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * purpose of this class
 *
 * @author Haifeng.Zhu
 *         created at 1/29/18
 */
public class Factories {
    
    public static OCR getOcrMethod(OcrMethod type, GameConfig config){
        switch (type){
            case BAIDU:
                return new BaiDuOCR(config);
            case TESS:
                return new TessOCR(config);
            default:
                return new TessOCR(config);
        }
    }
    
    
    public static Search getSearchMethod(SearchMethod type, GameConfig config, String searchContent){
        switch (type){
            case BAIDU:
                return new BaiDuSearch(config, searchContent);
            case GOOGLE:
                return new GoogleSearch(config, searchContent);
            default:
                return new BaiDuSearch(config, searchContent);
        }
    }
    
    
    public static ThreadPoolExecutor getThreadPool(){
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>(100);
        int corePoolSize = 6;
        int maxPoolSize = 12;
        Long keepAliveMillions = 1000L;
        
        // use the default handler for reject
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 
                keepAliveMillions, TimeUnit.MILLISECONDS, workQueue);
        return threadPoolExecutor;
    }
    
    
    public static GameConfig getGameConfig(Integer gameConfigID){
        switch (gameConfigID){
            case Config.GAME_PEEK_MEETING:
                return new GameConfig_PeekMeeting();
            case Config.GAME_MILLION_HERO:
                return new GameConfig_MillionHero();
            default:
                return new GameConfig_Default();
        }
    }
}
