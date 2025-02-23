package cn.tedu.baking.security;


import cn.tedu.baking.mapper.UserMapper;
import cn.tedu.baking.pojo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper mapper;
    @Override //此方法的username代表用户输入的用户名
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //此方法当Security框架进行登录认证时,自动调用
        //当此方法响应null时 代表用户名不存在, 下面模拟用户输入的用户名是没问题的
        //从数据库中查询正确的信息
        UserVO userVO = mapper.selectByUserName(username);
        if (userVO!=null){
//            UserDetails userDetails = User.builder()
//                    .username(username).password(userVO.getPassword())
//                    .disabled(false)//账号是否禁用
//                    .accountLocked(false)//账号是否锁定
//                    .accountExpired(false)//账号是否过期
//                    .credentialsExpired(false)//登录凭证是否过期
//                    .authorities("权限名") //授予当前登录的用户的权限
//                    .build();
            //针对不同用户创建出两种不同的角色
            String role = userVO.getIsAdmin()==1?"ADMIN":"USER";

            CustomUserDetails userDetails =
                    new CustomUserDetails(
                            userVO.getId(),userVO.getNickName(),
                            userVO.getIsAdmin(),userVO.getImgUrl(),
                            username, userVO.getPassword(),
                            AuthorityUtils.createAuthorityList(role));
            return userDetails;
        }
        return null;//代表用户名不存在
    }
}
