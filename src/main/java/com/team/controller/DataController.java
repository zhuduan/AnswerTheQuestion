package com.team.controller;

import com.team.utils.ResponseUtils;

import java.util.Map;

@RestController()
@RequestMapping(value = "data")
public class DataController {

    @RequestMapping(value = "upload/image", method = RequestMethod.POST)
    public Map<String, Object> inputQuestionImg(@RequestParam("file") MultipartFile file,
                                                @RequestParam("gameName") String gameName) throws Exception{
        // deal the img

        byte[] imgData = null;
        return ResponseUtils.toSuccess("");
    }
}
