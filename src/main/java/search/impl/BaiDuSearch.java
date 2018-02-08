package search.impl;

import common.GameConfig;
import common.GameConfig_PeekMeeting;
import model.SearchResult;
import search.Search;
import utils.HttpConnectionUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class BaiDuSearch implements Search {
    
    
    private static String URL_PREFIX = "http://www.baidu.com/s?wd=";
    private static String URL_SUFFIX = "";

    private GameConfig config = null;
    
    private String searchContent = "";
    
    public BaiDuSearch(GameConfig config, String content) {
        this.config = config;
        this.searchContent = content;
    }
    
    
    public SearchResult search(String searchContent) {
        SearchResult searchResult = new SearchResult();
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(URL_PREFIX + getEncodeContent(searchContent) + URL_SUFFIX);
            InputStream inputStream = HttpConnectionUtil.getInfo(url);
            if ( inputStream==null ){
                return searchResult;
            }
            
            BufferedReader breaded = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = breaded.readLine();
            while ( line != null ) {
                content.append(line);
                if (line.contains("百度为您找到相关结果约")) {
                    int start = line.indexOf("百度为您找到相关结果约") + 11;
                    line = line.substring(start);
                    int end = line.indexOf("个");
                    line = line.substring(0, end);
                    line = line.replace(",", "");
                    searchResult.setHitNum(Long.valueOf(line));
                }
                line = breaded.readLine();
            }
        } catch (MalformedURLException exp){
            
        } catch (IOException exp){
            
        }
        searchResult.setContent(content.toString());
        return searchResult;
    }
    
    // encode the search content
    private String getEncodeContent(String content){
        try {
            return URLEncoder.encode(content, this.config.getQa_char_code());
        } catch (UnsupportedEncodingException exp){
            // error log
        }
        return "";
    }
    
    public static void main(String[] args){
        String content = "以下哪个不是清华大学的代表校花 山茶花";
        BaiDuSearch search = new BaiDuSearch(new GameConfig_PeekMeeting(), content);
        SearchResult searchResult = search.search(content);
        System.out.println(searchResult.toString());
    }

    @Override
    public SearchResult call() throws Exception {
        return search(this.searchContent);
    }
}
