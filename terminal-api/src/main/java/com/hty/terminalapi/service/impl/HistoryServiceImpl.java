package com.hty.terminalapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hty.terminalapi.dao.HistoryMapper;
import com.hty.terminalapi.pojo.History;
import com.hty.terminalapi.pojo.User;
import com.hty.terminalapi.service.HistoryService;
import com.hty.terminalapi.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public List<History> getHistoryList() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = userDetails.getUser();
        QueryWrapper<History> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getId());
        queryWrapper.orderByDesc("id");
        List<History> historyList = historyMapper.selectList(queryWrapper);
        return historyList;
    }

    @Override
    public Map<String, String> saveConnectionMessage(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = userDetails.getUser();
        String host = data.get("host");
        String port = data.get("port");
        String username = data.get("username");
        String password = data.get("password");
        String name = data.get("name");
        History history = new History(null,user.getId(),host,port,username,password,new Date(),name);
        historyMapper.insert(history);
        Map<String,String> map = new HashMap<>();
        map.put("message","success");
        return map;
    }

    @Override
    public Map<String, String> delHistory(Integer id) {
        historyMapper.deleteById(id);
        Map<String,String> map = new HashMap<>();
        map.put("message","success");
        return map;
    }
}
