package com.team.controller;

import com.team.common.Config;
import com.team.common.GameConfig_Default;
import com.team.common.ScheduleConfig;
import com.team.utils.JsonUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.team.utils.ResponseUtils;

import java.util.Map;

@RestController()
@RequestMapping(value = "config")
public class ConfigController {

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Map<String, Object> getScheduleList() throws Exception{
        return ResponseUtils.toSuccess(JsonUtil.write(ScheduleConfig.values()));
    }
}
