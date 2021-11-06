# jthink

#### 介绍
Jthink内容管理系统，采用Springboot+sa-token权限框架+mybatis，平时开发工作较少了，主要为了平时练手，避免忘记怎么写代码

博客部分参照了wordpress的表结构并进行了适当调整

前端模板引擎使用thymeleaf模板引擎并使用了thymeleaf 自定义标签

前端界面全部采用 [笔下光年](https://gitee.com/yinqi)开发的bootstrap模板[ Light Year Admin](https://gitee.com/yinqi/Light-Year-Admin-Template)  

#### 软件架构
软件架构说明

SpringBoot 2.5.0

Sa-token 1.19.0

thymeleaf3

mysql8数据库


#### 安装教程

1.  创建数据库jthink, charset:utf8 collation:utf8_bin,导入根项目根目录下sql目录下的jthink.sql文件导入并恢复,修改application-dev.yml数据库链接密码
2.  本项目采用maven构建直接用IDE导入maven工程进行编译
3.  启动JthinkApplication类
4.  测试帐号:admin  123456
5.  前台访问地址: http://localhost:8081/
6.  后台登录: http://localhost:8081/system/adminlogin


#### 使用说明

代码生成指南

右击 项目 Run As ---> Maven build...

在Goals：mybatis-generator:generate -e

最后点击 Run

#### 功能开发进度

1.  后台用户管理(完成)
2.  权限管理(完成)
3.  文章/页面发布管理(完成)
4.  主题管理(完成)
5.  文章评论(完成)
6.  前台用户管理(完成)
7.  前台个人中心(待开发)
8.  数据缓存(待开发)
9.  系统监控(完成)
10. 站内信(待开发)
11. 前后台用户分离(完成)

#### 部分截图

  
![输入图片说明](https://images.gitee.com/uploads/images/2021/0326/235842_e6f587a3_496.png "localhost_8081_post_24.png")

![输入图片说明](https://images.gitee.com/uploads/images/2021/0327/000004_d3448713_496.png "localhost_8081_system.png")

![输入图片说明](https://images.gitee.com/uploads/images/2021/0327/000035_8a89b980_496.png "localhost_8081_system (1).png")



#### 欢迎参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request
