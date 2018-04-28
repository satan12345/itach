



# 一 SpringBoot入门



## 1SpringBoot简介

> 简化spring应用开发的一个框架
>
> 整个Spring技术栈的大整合
>
> J2EE 开发的一站式解决方案

## 2微服务

> 2014 martin fowler
>
> 微服务：一种架构风格
>
> 一个应用应该是一组小型服务;可以通过Http的方式进行沟通
>
> 每一个功能袁术最终都是一个可独立替换和独立升级的软件单元
>
> [详细参照微服务文档](https://mp.weixin.qq.com/s?__biz=MjM5MjEwNTEzOQ==&mid=401500724&idx=1&sn=4e42fa2ffcd5732ae044fe6a387a1cc3#rd)

## 3环境准备

> 环境约束
>
> –jdk1.8：Spring Boot 推荐jdk1.7及以上；java version "1.8.0_112"
> –maven3.x：maven 3.3以上版本；Apache Maven 3.3.9
> –IntelliJIDEA2017：IntelliJ IDEA 2017.2.2 x64、STS
> –SpringBoot 1.5.9.RELEASE：1.5.9；
> 统一环境；

MAVEN的设置

​	给Maven的settings.xml配置文件的profiles标签添加

```xml
<profile>
	<id>jdk‐1.8</id>
    <activation>
        <activeByDefault>true</activeByDefault>
        <jdk>1.8</jdk>
    </activation>
  <properties>
      <maven.compiler.source>1.8</maven.compiler.source>
      <maven.compiler.target>1.8</maven.compiler.target>
      <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
  </properties>
</profile>
```



## 4 SpringBoot HelloWorld

一个功能：

浏览器发送hello请求,服务端接收请求并处理 相应Hello World字符串

## 1 创建一个maven工程



## 2 导入相关的依赖	

```xml
 <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.1.RELEASE</version>
    </parent>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
```



## 3 编写主程序;启动Spring Boot

```java
/* *
 @SpringBootApplication:标注一个主程序 说明这是一个spring boot应用
 */
@SpringBootApplication
public class HelloWorldMainApplication {
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HelloWorldMainApplication.class, args);
    }

}


```



### 4简化部署



在pom文件添加这个坐标

```xml
<!--可以将应用打包成一个可执行的jar包-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```

将这个应用打成jar包 直接使用java -jar的命令进行执行

![img](file:///D:\Documents\Tencent Files\935846371\Image\C2C\Image1\231{8(A535V[T)@U1WF4XUF.png)



## 5 Hello World探究

### 1,POM 文件

```xml
  <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.1.RELEASE</version>
    </parent>

他的父项目
 <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>2.0.1.RELEASE</version>
        <relativePath>../../spring-boot-dependencies</relativePath>
    </parent>
他来管理SpringBoot应用里面的所有的依赖
```

Spring Boot的版本仲裁中心 

以后我们导入依赖默认是不需要些版本(没有在dependencies里面管理的依赖自然要声明版本号)



### 2 导入的依赖

```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
 </dependency>
```

### 1spring-boot-start-web

Spring-boot-starter:spring-boot 场景启动器，帮助我们导入了一个web模块正常运行所依赖的组件

Spring Boot将所有的功能场景都抽取出来 做成一个个的starters(启动器) 只需要在项目里面引入这些startes相关场景的所有依赖都会导入进来 要用什么就导入什么场景启动器

### 2 主程序类，主入口类

```java
/* *
 @SpringBootApplication:标注一个主程序 说明这是一个spring boot应用
 */
@SpringBootApplication
public class HelloWorldMainApplication {
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HelloWorldMainApplication.class, args);
    }

}


```

**@SpringBootApplication:**Spring Boot应用标准在某个类上 说明这个类是 Spring Boot的主配置类 SpringBoot就应该允许这个类的main方法来启动SpringBoot应用

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
```

**@SpringBootConfiguration** Spring Boot的配置类 标注在某个类上表名这个是一个Spring Boot的配置类

![img](file:///D:\Documents\Tencent Files\935846371\Image\C2C\Image1\H~0Y%CJ{1A}ZPHKSD5$K%~P.png)

@Configuration:配置类上来标注这个注解

配置类==配置类；配置类也是容器中的一个组件@Component

@EnableAutoConfiguration:开启自动配置功能;

​	以前我们需要配置的东西 SpringBoot帮我们自动配置；@EnableAutoConfiguration 告诉Spring Boot开启自动配置功能 这样自动配置才能生效

```java
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {
```



**@AutoConfigurationPackage** ：自动配置包

```java
@Import(AutoConfigurationPackages.Registrar.class)
public @interface AutoConfigurationPackage {
```

**Spring的底层注解@Import，给容器中导入一个组件**，导入的组件由AutoConfigurationPackages.Registrar.class

​	**将主配置类（@SpringBootApplication标注的类）所在的包及下面所有子包里面所有的组件扫描到Spring容器中**

```java
@Import(AutoConfigurationImportSelector.class)
开启自动导入组件的选择器
将所有需要导入的组件以全类名的方式返回 这些组件就会被添加到容器中
会给容器中导入非常多的自动配置类 (xxxAutoConfiguration);就是给这个容器中导入这个场景所有组件 并配置好这些组件 
```



![自动配置类](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\QQ截图20180425193638.png)

有了自动配置类 免去了我们手动编写配置 注入功能组件等的工作

**SpringBoot 在启动的时候从类路径下的META-INF/spring.factories中获取EnableAutoConfiguration指定的值，将这些值作为自动配置类导入到容器中,自动配置类就生效，帮我们进行自动配置工作 我们需要自己配置的东西 自动配置类都帮我们做了**

![导入的数据](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\QQ截图20180425194513.png)

J2EE的整体整合解决方案和自动配置都在org.springframework.boot.autoconfigure包下

## 6 使用Spring Initializer快速创建Spring Boot项目

IDE都致辞使用Spring的项目创建想到快速创建一个Spring Boot项目

选择我们需要的模块 想到会联网创建Spring Boot项目

默认生成的SpringBoot项目;

​	主程序已经生成好了 我们只需要实现我们自己的逻辑

​	resources文件夹中的目录结构

​		**static: 保存所有的静态资源 js css img**

​		**templates: 保存所有的模板页面 (Spring Boot 默认jar包使用嵌入式tomcat 默认不支持jsp) 可以使用模板引擎 freemark thymeleaf** 

​		**application.properties:Spring Boot的应用配置文件** 可以修改一些默认设置



# 二 配置文件

## 1. 配置文件

> spring boot 使用一个全局配置文件，配置文件的名称是固定的
>
> application.properties
>
> application.yml
>
> 配置文件的作用:修改Spring Boot 自动配置的默认值；Spring Boot 在底层都给我们自动配置好

​	

yaml:

```yaml
server:
  port: 8081
```



## 2.YAML语法

 ### 1.基本语法

k: v   表示一对键值对(空格必须要有)

以空格的缩进来控制层级关系;只要是左对齐的的一列数据；都是同一个层级的

属性和值也都是大小写敏感的

```yaml
server:
	prot: 8081
	path: /hello
```



### 2 值的写法

字面量：普通的值(数字 字符串 布尔)

k: v 字面量直接来写；

​	字符串不用加上单引号或者双引号

​	“”双引号 ，不会转义字符串里面的特殊字符；特殊字符会作为本身想表示的意思

​		nane: "zhangsan\n 李四" ；输出  zhangsan 换行 李四；特殊字符不会被转义，只是会作为本身想表示的意思

​	‘’ 单引号 :会转义特殊字符，特殊字符最终会被转义成一个普通的字符串

​		nane: 'zhangsan\n 李四'；输出  zhangsan\n 李四；

对象  map(属性和值)(键值对)

k: v:在下一行来写对象的属性和值的关系；注意缩进

对象还是k: v的方式

```yaml
friends:
	lastName: zhansan
	age: 20
```

行内写法

```yaml
friends: {lastname: zhangsan, age: 18}
```



数组(List Set )

用- 值表示数组中的一个元素

```yaml
pets:
- cat
- dog
- pig
```

行内写法

```yaml
pets: [cat,dog,pig]
```









































#



















