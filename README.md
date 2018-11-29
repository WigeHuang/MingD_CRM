**项目说明** 
- mindao-crm是用Spring Boot实现的J2EE快速开发平台
- 使用mindao-crm搭建项目，只需编写30%左右代码，其余的代码交给系统自动生成
- 一个月的工作量，一周就能完成，剩余的时间可以陪家人、朋友、撩妹、钓凯子等，从此踏入高富帅、白富美行业
- 也是接私活的利器，能快速完成项目并交付，轻松赚取外快，实现财务自由，走向人生巅峰（接私活赚了钱，可以给作者打赏点辛苦费，让作者更有动力持续优化、完善）
 


**具有如下特点** 
- 友好的代码结构及注释，便于阅读及二次开发
- 实现前后端完全分离，前端再也不用关注后端技术
- 灵活的权限控制，可控制到页面或按钮，满足绝大部分的权限需求
- 页面交互使用Vue2.x，极大的提高了开发效率
- 完善的代码生成机制，可在线生成entity、xml、dao、service、html、js、sql代码，减少70%以上的开发任务
- 引入quartz定时任务，可动态完成任务的添加、修改、删除、暂停、恢复及日志查看等功能
- 引入API模板，根据token作为登录令牌，极大的方便了APP接口开发
- 引入Hibernate Validator校验框架，轻松实现后端校验
- 引入云存储服务，已支持：七牛云、阿里云、腾讯云等
- 引入swagger文档支持，方便编写API接口文档
- 引入路由机制，刷新页面会停留在当前页



 **技术选型：** 
- 核心框架：Spring Boot 1.5
- 安全框架：Apache Shiro 1.3
- 视图框架：Spring MVC 4.3
- 持久层框架：MyBatis 3.3
- 定时器：Quartz 2.3
- 数据库连接池：Druid 1.0
- 日志管理：SLF4J 1.7、Log4j
- 页面交互：Vue2.x


 **软件需求** 
- JDK1.8+
- MySQL5.5+
- Tomcat7.0+
- Maven3.0+



 **后台本地部署**
- 通过git下载源码
- 创建数据库mindao-crm，数据库编码为UTF-8
- 执行doc/mindao-crm.sql文件，初始化数据
- 修改application.properties文件，更新MySQL账号和密码
- Eclipse、IDEA运行MindaoCrmApplication.java，则可启动项目
- 项目访问路径：http://localhost:8000/crm
- API文档路径：http://localhost:8000/crm/swagger-ui.html
- 账号密码：admin/admin

 **前台NGINX部署**
 - 解压doc/nginx-1.11.10.zip,放到一个常用目录下
 - 修改nginx解压后的nginx.conf,设置root指向自己项目的的mindao-crm\src\main\resources\site实际物理路径
 - 运行nginx.exe
 - 项目访问路径：http://localhost:1000/syslogin.html
 - 账号密码：153277817@qq.com/123456


 

