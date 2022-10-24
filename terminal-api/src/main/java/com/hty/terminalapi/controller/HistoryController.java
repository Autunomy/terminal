package com.hty.terminalapi.controller;

import com.hty.terminalapi.pojo.History;
import com.hty.terminalapi.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("/history/list")
    public List<History> getHistoryList(){
        return historyService.getHistoryList();
    }

    @PostMapping("/history/save/message")
    public Map<String,String> saveConnectionMessage(@RequestParam Map<String,String> data){
        return  historyService.saveConnectionMessage(data);
    }

    @PostMapping("/history/del")
    public Map<String,String> delHistory(Integer id){
        return historyService.delHistory(id);
    }
}
