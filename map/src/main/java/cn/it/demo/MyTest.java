package cn.it.demo;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

import java.lang.reflect.Method;

public class MyTest {
    public static void main(String[] args) {

//        UserDO userDO = new UserDO().setId(1).setUsername("yudaoyuanma").setPassword("buzhidao");
//
//        MyBO myBO = UserConvert.INSTANCE.convertMyBO(userDO);
//
//        System.out.println(myBO);

        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.addProperty("id", Integer.class);


        BeanMap beanMap = BeanMap.create(beanGenerator);
//        beanMap.

        beanGenerator.setSuperclass(UserBO.class);
        Object o = beanGenerator.create();
        Class<?> aClass = o.getClass();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }
}
