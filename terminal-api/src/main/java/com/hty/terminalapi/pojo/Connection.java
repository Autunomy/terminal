package com.hty.terminalapi.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Connection {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String connectName;
    private Integer userId;
    private String host;
    private String port;
    private String username;
    private String password;
}
