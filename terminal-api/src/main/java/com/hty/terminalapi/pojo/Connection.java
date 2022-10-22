package com.hty.terminalapi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Connection {
    private Integer id;
    private String connectName;
    private Integer userId;
    private String host;
    private String port;
    private String username;
    private String password;
}
