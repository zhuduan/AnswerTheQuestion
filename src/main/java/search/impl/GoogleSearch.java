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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * purpose of this class
 *
 * @author Haifeng.Zhu
 *         created at 1/29/18
 */
public class GoogleSearch implements Search {

    private static String URL_PREFIX = "https://www.google.com/search?q=";
    private static String URL_SUFFIX = "";

    private GameConfig config = null;
    
    private String searchContent = "";

    public GoogleSearch(GameConfig config, String content) {
        this.config = config;
        this.searchContent = content;
    }
    
    public SearchResult search(String searchContent) {
        SearchResult searchResult = new SearchResult();
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(URL_PREFIX + getEncodeContent(searchContent) + URL_SUFFIX);
            System.out.println(url.toString());
            InputStream inputStream = HttpConnectionUtil.getInfoWithProxy(url);
            if ( inputStream==null ){
                return searchResult;
            }

            BufferedReader breaded = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = breaded.readLine();
            while ( line != null ) {
                content.append(line);
                Pattern pattern = Pattern.compile("About ((\\w+)*(,)*)* results");
                Matcher matcher = pattern.matcher(line);
                if ( matcher.find() ){
                    String numString = matcher.group();
                    int startIndex = numString.indexOf("About ")+6;
                    int endIndex = numString.indexOf(" results");
                    Long num = Long.valueOf(numString.substring(startIndex,endIndex).replace(",",""));
                    searchResult.setHitNum(num);
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
        GoogleSearch search = new GoogleSearch(new GameConfig_PeekMeeting(), content);
        SearchResult searchResult = search.search(content);
        System.out.println(searchResult.toString());
    }

    @Override
    public SearchResult call() throws Exception {
        return search(this.searchContent);
    }
}
