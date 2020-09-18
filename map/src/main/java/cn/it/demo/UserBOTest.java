package cn.it.demo;

// https://cloud.tencent.com/developer/article/1630174
// 在对象转换时，我们可能会存在属性不是完全映射的情况，例如说属性名不同。此时，我们可以使用 MapStruct 提供的 @Mapping 注解，配置相应的映射关系。
public class UserBOTest {

    public static void main(String[] args) {
        // 创建 UserDO 对象
        UserDO userDO = new UserDO()
                .setId(1).setUsername("yudaoyuanma").setPassword("buzhidao");
        // <X> 进行转换
        UserBO userBO = UserConvert.INSTANCE.convert(userDO);
        System.out.println(userBO.getId());
        System.out.println(userBO.getUsername());
        System.out.println(userBO.getPassword());
    }

}