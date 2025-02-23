### 修改文章步骤:

1. 给稿件管理页面中的三个表格里面的删除和编辑按钮添加点击事件,在修改事件中跳转到postArticle.html页面中, 同时把需要修改的文章id传递到页面中
2. 在postArticle.html页面中的created方法里面得到传递过来的id, 通过id查询文章的所有数据,把查询到的数据显示到页面中
3. 在ContentController里面添加方法处理上面的请求, 创建ContentUpdateVO,  调用mapper的selectByIdForUpdate查询方法通过id查询详情
4. 实现mapper里面的方法
5. 在ContentController里面的addNew方法中判断 传递过来的参数中是否包含了id ,如果包含id代表是修改,否则是添加,  如果是修改,则调用mapper的update方法
6. 实现mapper中的update方法

### 删除文章步骤:

1. 在点击删除按钮时向服务发出删除请求,把id传递过去
2. 在ContentController里面添加delete方法处理上面的请求, 在方法中通过id查询到商品的信息, 得到封面路径并删除对应的文件,  判断删除的是否是视频类型,如果是的话得到视频路径并删除,最后调用mapper里面的delete方法
3. 实现mapper中的delete方法

