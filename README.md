# jzwbxx-admin
基于layui&layuimini + Freemarker + SSH 后台管理系统框架

## 功能

- 后台管理
    - 登录/注销
    - 管理员管理
    - 角色管理
    - 菜单管理
    - 存储插件管理
    - 系统操作日志
    - 权限校验

## 项目结构
```shell
├── src/main/java
│   ├── com.jzwbxx
│   │    ├── aspect                             //aop
│   │    ├── common                             //通用类
│   │    ├── config                             //配置
│   │    ├── controller                         //controller相关
│   │    ├── converter                          //转换器
│   │    ├── dao                                //dao相关
│   │    ├── directive                          //freemark自定义指令
│   │    ├── exception                          //自定义异常
│   │    ├── filter                             //过滤器
│   │    ├── interceptor                        //拦截器
│   │    ├── listener                           //监听器
│   │    ├── modal                              //对象实体
│   │    ├── plugin                             //插件相关
│   │    ├── service                            //service相关
│   │    ├── shiro                              //shiro权校
│   │    ├── util                               //工具类
│   │    └── MainApplication.java               //启动类
│   ├── resources
│   │    ├── config                             //项目相关配置
│   │    ├── db.migration                       //flywaydb
│   │    ├── static                             //静态资源
│   │    ├── template                           //视图模板
│   ├── application.yml
│   ├── application-dev.yml
│   ├── application-prod.yml
│   ├── setting.properties
├── pom.xml                                    //Maven依赖
├── README.md
```

## 运行说明

### 准备环境

jdk8、mysql、redis

### 创建数据库

执行resources/config目录下jzwbxx-admin.sql创建数据库、表以及初始化相关数据

### 修改项目数据库连接、redis连接

修改application.yml对应配置

### 运行项目

执行MainApplication

## 依赖版本说明

groupId | artifactId |  version  
-|-|-
org.springframework.boot | spring-boot-starter-web | 2.0.0.RELEASE |
org.springframework.boot | spring-boot-starter-data-jpa | 2.0.0.RELEASE |
org.springframework.boot | spring-boot-starter-freemarker | 2.0.0.RELEASE |
org.springframework.boot | spring-boot-starter-cache | 2.0.0.RELEASE |
net.sf.ehcache | ehcache | 2.10.4 |
org.apache.shiro | shiro-spring | 1.4.0 |
mysql | mysql-connector-java | 5.1.45 |
com.alibaba | druid-spring-boot-starter | 1.1.21 |
com.alibaba | fastjson | 1.2.61 |
commons-io | commons-io | 2.5 |
commons-codec | commons-codec | 1.10 |
commons-fileupload | commons-fileupload | 1.3.1 |
commons-lang | commons-lang | 2.6 |
org.apache.commons | commons-lang3 | 3.9 |