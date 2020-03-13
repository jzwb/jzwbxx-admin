# jzwbxx-admin
基于layui&layuimini + Freemarker + SSH 后台管理系统框架

## 功能
- 登录/注销
- 用户管理
- 角色管理
- 菜单管理
- 存储插件管理
- 系统操作日志
- 权限校验

## 项目结构
```shell
├── src/main/java                               //源代码
│   ├── com.jzwbxx
│   │    ├── aspect                             //Aop切面
│   │    ├── common                             //通用对象
│   │    ├── config                             //配置
│   │    ├── controller                         //接口请求
│   │    │    └── admin                      
│   │    │    └── api                        
│   │    ├── converter                          //转换器
│   │    ├── dao                                //db操作
│   │    ├── directive                          //freemark自定义指令
│   │    ├── exception                          //自定义异常
│   │    ├── filter                             //过滤器
│   │    ├── interceptor                        //拦截器
│   │    ├── listener                           //监听器
│   │    ├── modal                              //对象实体
│   │    ├── plugin                             //插件相关
│   │    ├── service                            //业务相关处理
│   │    ├── shiro                              //shiro权校
│   │    ├── util                               //相关工具类
│   │    └── MainApplication.java               //启动类
│   ├── resources
│   │    ├── db.migration                       //表语句
│   │    ├── static.static                      //静态资源
│   │    │    └── admin                      
│   │    ├── template                           //视图模板
│   │    │    └── admin
│   ├── application.yml
│   ├── application-dev.yml
│   ├── application-prod.yml
│   ├── setting.properties
├── pom.xml                                    //Maven依赖
├── README.md
```

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
org.flywaydb | flyway-core | 4.2.0 |
