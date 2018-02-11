package utils;

import common.GameConfig;
import common.Config.*;
import ocr.OCR;
import ocr.impl.BaiDuOCR;
import ocr.impl.TessOCR;
import search.Search;
import search.impl.BaiDuSearch;
import search.impl.GoogleSearch;

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
}
