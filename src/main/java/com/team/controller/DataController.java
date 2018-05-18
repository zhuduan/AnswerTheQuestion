package com.team.controller;

import com.team.common.GameConfig;
import com.team.service.DataService;
import com.team.utils.Factories;
import com.team.utils.ResponseUtils;
import net.bytebuddy.asm.Advice;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping(value = "data")
public class DataController {
    
    @Autowired
    private DataService dataService;

    @RequestMapping(value = "upload/image", method = RequestMethod.POST)
    public Map<String, Object> inputQuestionImg(@RequestParam("file") MultipartFile file,
                                                @RequestParam("gameID") Integer gameID) throws Exception{
        // deal the img
        String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
        GameConfig gameConfig = Factories.getGameConfig(gameID);
        if( !suffix.equals(gameConfig.getImg_suffix()) ){
            return ResponseUtils.toFailure("the suffix [" + suffix + "] is not valid");
        }
        
        Map<String, Boolean> resMap = new HashMap<>();
        resMap.put("result", dataService.setAnswer(gameID, gameConfig, file.getBytes()));
        return ResponseUtils.toSuccess(resMap);
    }
}
