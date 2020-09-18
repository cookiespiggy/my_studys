package cn.it.demo;

public class MyTest {
    public static void main(String[] args) {

        UserDO userDO = new UserDO().setId(1).setUsername("yudaoyuanma").setPassword("buzhidao");

        MyBO myBO = UserConvert.INSTANCE.convertMyBO(userDO);

        System.out.println(myBO);


    }
}
