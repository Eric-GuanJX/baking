### 注册功能:

- 修改reg.html页面,点击注册按钮时发出请求
- 创建User,UserVO,UserRegDTO,UserMapper.java,UserMapper.xml, UserController
- 在工程中把boot01工程里面的Security相关内容复制到baking工程
  - 在pom.xml添加Security依赖
  - 把security包copy到新工程
  - 将SecurityConfig里面的密码编码器改成BCryptPasswordEncoder

### 登录功能:

- 修改login.html页面, 点击登录按钮时发出请求
- 在UserController中添加login方法处理请求,在login方法中开启认证流程
- 把boot01工程中的全局异常处理代码复制到新工程



### 自定义组件my-header

- 通过自定义组件可以将多个页面的顶部进行重用,  这样顶部的功能只需要写一遍即可复用到多个页面
- 代码参见my-header.js



### 个人中心页面

- 将localStorage里面登录成功的用户信息 ,显示到personal.html页面中

- 上传用户头像

  - 在personal.html页面中的上传组件中添加 action/name/limit三个属性

  - 创建UploadController  添加upload 方法处理上传文件的请求

  - 在application.properties里面添加配置信息 设置最大上传文件的大小

  - 在upload方法中,将接收到的文件保存到和日期相关的文件夹中, 并把图片的路径响应给客户端

- 删除图片:
  -  当点击页面中的删除图标时会触发on-remove事件 ,调用handleRemove方法, 在方法中向服务器发出删除请求, 同时把图片的路径(当图片上传完成时服务器给客户端响应的图片路径, 此时路径在file.response中)传递给服务器  
  - 在UploadController中添加remove方法处理删除请求, 在方法中 拼接完成的文件路径 并删除.



​    

​    

​    





