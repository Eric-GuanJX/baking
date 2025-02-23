### 内容列表页面

1. 首页点击查看更多按钮时跳转到contentList.html页面,同时将分类的id传递过去
2. 在contentList.html页面的created方法中得到传递过来的type ,然后发请求获取此type对应的数据
3. 在CommentController中添加selectList方法处理上面的请求, 方法中调用mapper的selectListByType方法
4. 实现mapper中的方法

### 搜索功能

1. 在my-header.js中 让文本框和wd变量进行双向绑定, 给搜索按钮添加点击事件, 点击时跳转到contentList.html页面中, 同时将wd传递过去
2. 在contentList.html页面的created方法中得到传递过来的wd, 然后请求和此wd相关的数据
3. 在ContentController中添加search方法 方法中调用mapper的selectByWd方法 
4. 在mapper中实现方法, 配置SQL语句时 用到CONCAT()函数 进行拼接

### 发评论

1. 在detail.html详情页面中给发布按钮添加点击事件, 让文本框和变量进行双向绑定,点击时向服务器发出添加评论的请求
2. 创建CommentController 添加addNew方法, 在方法中判断是否登录,   然后调用mapper的insert方法
3. 在mapper中实现insert方法

### 评论列表

1. 详情页面的created方法中 发请求获取当前文章的所有评论
2. CommentController里面处理请求, 调用mapper的selectByContentId
3. mapper中实现方法
4. 在页面中让元素和请求到的数组进行绑定

### 后台管理用户列表

1. 在admin.html页面的created方法里面发请求获取用户数据, 让表格和数据进行绑定
2. 在UserController中处理上面的请求
3. 修改是否是管理员, 给el-switch控件添加了change事件, 在事件方法中发出修改管理员状态的请求
4. 在UserContentController里面处理上面的请求
5. 删除用户
### 后台管理页面-轮播图

1. 在admin.html页面中的created方法请求所有轮播图数据
2. 在BannerController里面处理上面的请求
3. 实现删除轮播图功能







### 任务:实现后台管理页面- 食谱/视频/资讯

1. 在点击导航菜单中的食谱/视频/资讯时, 调用了handleSelect方法, 在方法中根据点击的类型请求对应的数据


2. ContentController里面处理上面的请求


3. 修改功能:和稿件管理页面中的修改功能一致
4. 删除功能





