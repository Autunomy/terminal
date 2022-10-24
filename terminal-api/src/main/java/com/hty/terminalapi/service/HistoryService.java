package com.hty.terminalapi.service;

import com.hty.terminalapi.pojo.History;

import java.util.List;
import java.util.Map;

public interface HistoryService {

    /***
     * 获取全部的历史记录
     * @return
     */
    List<History> getHistoryList();

    /***
     * 保存连接信息
     * @param data
     * @return
     */
    Map<String ,String> saveConnectionMessage(Map<String,String> data);

    /***
     * 删除一条历史
     * @param id
     * @return
     */
    Map<String,String> delHistory(Integer id);
}
