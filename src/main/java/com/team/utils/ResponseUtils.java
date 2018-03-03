package com.team.utils;

import com.google.common.collect.Maps;

import java.util.Map;

public class ResponseUtils {

    public static final String SUCCESS = "success";

    public static final String FAILURE = "failure";

    public static final int SuccessStatus = 200;

    public static final int FailureStatus = 500;

    public static final int NeedLoginStatus = 100;

    private static <T> Map<String, Object> wrap(T data, int status, String msg) {

        Map<String, Object> modal = Maps.newHashMap();
        modal.put("status", status);
        modal.put("data", data);
        modal.put("msg", msg);
        return modal;
    }

    public static <T> Map<String, Object> toSuccess(T data) {

        return wrap(data, SuccessStatus, SUCCESS);
    }

    public static <T> Map<String, Object> toFailure(T data, int status) {

        return wrap(data, status, FAILURE);
    }

    public static <T> Map<String, Object> toFailure(T data) {

        return toFailure(data, FailureStatus);
    }
}
