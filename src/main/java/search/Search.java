package search;

import model.QuestionAndAnswer;
import model.SearchResult;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.Callable;

/**
 * Created by 618 on 2018/1/12.
 *
 * @author lingfengsan
 */
public interface Search extends Callable<SearchResult> {
    
}
