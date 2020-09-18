package cn.it.demo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserBO {

    /**
     * 用户编号
     **/
    private Integer id;
    /**
     * 用户名
     **/
    private String username;
    /**
     * 密码
     **/
    private String password;

    // ... 省略 setter/getter 方法
}