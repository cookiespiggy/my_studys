package cn.it.demo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper // <1>
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class); // <2>

    UserBO convert(UserDO userDO);

    @Mappings({
            @Mapping(source = "id", target = "userId")
    })
    UserDetailBO convertDetail(UserDO userDO);

    @Mapping(source = "id", target = "myIp")
    MyBO convertMyBO(UserDO userDO);

}