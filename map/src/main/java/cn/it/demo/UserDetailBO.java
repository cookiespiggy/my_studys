package cn.it.demo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDetailBO {

    private Integer userId;

    // ... 省略 setter/getter 方法
}