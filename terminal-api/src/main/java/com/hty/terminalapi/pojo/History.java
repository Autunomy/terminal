package com.hty.terminalapi.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String host;
    private String port;
    private String username;
    private String password;
    private Date connectTime;
    private String name;
}
