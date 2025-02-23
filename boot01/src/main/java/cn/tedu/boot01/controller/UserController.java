package cn.tedu.boot01.controller;

import cn.tedu.boot01.mapper.UserMapper;
import cn.tedu.boot01.pojo.dto.UserLoginDTO;
import cn.tedu.boot01.pojo.dto.UserRegDTO;
import cn.tedu.boot01.pojo.entity.User;
import cn.tedu.boot01.pojo.vo.UserVO;
import cn.tedu.boot01.response.JsonResult;
import cn.tedu.boot01.response.StatusCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserController {

    @Autowired
    UserMapper mapper;

    @RequestMapping("/reg")
    public JsonResult reg(@RequestBody UserRegDTO userRegDTO){
        //查询
        UserVO userVO = mapper.selectByUsername(userRegDTO.getUsername());
        if (userVO!=null){
            return new JsonResult(StatusCode.USERNAME_ALREADY_EXISTS);
        }
        //把dto数据装进entity
        User user = new User();
        BeanUtils.copyProperties(userRegDTO,user);
        user.setCreated(new Date());
        mapper.insert(user);
        return JsonResult.ok();
    }

    @Autowired
    AuthenticationManager manager;

    @RequestMapping("/login")
    public JsonResult login(@RequestBody UserLoginDTO userLoginDTO){

        //开启Security框架的认证流程,会自动调用UserDetailsServiceImpl里面的方法
        Authentication result = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDTO.getUsername(),userLoginDTO.getPassword()
                ));
        //把认证完成之后的结果保存到Security框架的上下文中
        SecurityContextHolder.getContext().setAuthentication(result);
        //当顺利执行完上面两行代码时说明登录无异常 代表登录成功
        //如果用户名错误或密码错误时,Security框架会抛出对应的异常,
        // 我们需要在全局异常处理的地方处理这两个异常
        System.out.println("用户信息:"+result.getPrincipal());
        return JsonResult.ok(result.getPrincipal());//登录成功
    }
    @RequestMapping("/logout")
    public JsonResult logout(){
        //从Security的上下文中清除数据
        SecurityContextHolder.clearContext();

        return JsonResult.ok();
    }
}
