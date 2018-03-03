package com.team.search;

import com.team.model.QuestionAndAnswer;
import com.team.model.SearchResult;

import java.util.concurrent.Callable;

/**
 * Created by 618 on 2018/1/12.
 *
 * @author lingfengsan
 */
public interface Search extends Callable<SearchResult> {
    
}
