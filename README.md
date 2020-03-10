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
