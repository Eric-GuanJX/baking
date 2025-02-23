package cn.tedu.boot01.mapper;

import cn.tedu.boot01.pojo.entity.User;
import cn.tedu.boot01.pojo.vo.UserVO;
import org.springframework.stereotype.Repository;

@Repository//添加此注解后 自动装配时不需要添加required=false
public interface UserMapper {

    UserVO selectByUsername(String username);

    int insert(User user);

}
