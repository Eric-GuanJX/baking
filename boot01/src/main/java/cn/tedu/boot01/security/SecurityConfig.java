package cn.tedu.boot01.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //配置认证管理器
    @Bean //添加此注解是为了能够在Controller中自动装配
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //配置密码的加密方式
    @Bean
    public PasswordEncoder passwordEncoder(){
        //下面代码是获取了一个不加密的实例
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //配置自己的登录页面 当判断没有登录的时候 自动跳转到自己的登录页面
        http.formLogin().loginPage("/login.html");

        //配置白名单(无需登录也可以访问的资源)
        String[] urls = {"/reg.html","/login.html","/reg","/login"};
        http.authorizeRequests()
                .mvcMatchers(urls)//匹配某些路径
                .permitAll() //直接放行(不需要登录可以访问)
                .anyRequest()//其它请求
                .authenticated();  //需要登录认证才能访问

        //关闭默认开启的跨域攻击防御
        http.csrf().disable();
    }
}
