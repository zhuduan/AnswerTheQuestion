package com.team.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

public class JsonUtil {

    public static <T extends Object> String write(T beanObject){
        if ( beanObject==null ){
            return StringUtils.EMPTY;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(beanObject);
        } catch (JsonProcessingException exp){
            // do some log
        }
        return StringUtils.EMPTY;
    }
}
