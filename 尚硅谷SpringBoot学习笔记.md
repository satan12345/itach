



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



## 3配置文件值注入

配置文件

```yaml
person:
  age: 18
  boss: false
  birth: 2017/12/12
  map: {k1: v1,k2: 12 ,skill: huodun }
  list: [xiaoying,mingren,yuzhibozuozhu]
  dog:
       name: 小狗
       age: 2
  last-name: 卡卡西

```

javaBean:

```java
/**
 * 将配置文件中的每一个属性的值 映射到每个组件中
 * @ConfigurationProperties: 将本类中的所有属性和配置文件中的相关的配置进行绑定
 *  prefix="person":配置文件中哪个下面的所有属性进行一一映射
 *  只有这个组件是容器的组件 才能使用容器提供的@ConfigurationProperties功能
 *  默认从全局配置文件中获取值
 *
 *  <bean class="Person">
 *      <property name="lastName" value="字面量/${key}从环境变量,配置文件中获取值/#{Spel}"/>
 *  </bean>
 */
@ConfigurationProperties(prefix = "person")
/**
 * 加载指定的配置文件
 */
@PropertySource({"classpath:person.properties"})
@Component
public class Person {
    //@Value("${person.last-name}")
    private String lastName;
    //@Value("#{11*2}")
    private Integer age;
   // @Value("true")
    private Boolean boss;
    private Date birth;

    private Map<String,Object> map;

    private List<Object> list;

    private Dog dog;
```

我们可以导入配置文件处理器，以后编写配置就有提示了

```xml
<!--导入配置文件处理器,配置文件进行绑定就会有提示-->

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-configuration-processor</artifactId>
			<optional>true</optional>
		</dependency>
```

### 2、@Value获取值和@ConfigurationProperties获取值比较

|            | @ConfigurationProperties | @Value |
| ---------- | ------------------------ | ------ |
| 功能         | 批量注入配置文件属性               | 一个个指定  |
| 松散绑定       | 支持                       | 不支持    |
| Spel       | 不支持                      | 支持     |
| Jsr303数据校验 | 支持                       | 不支持    |
| 复杂类型封装     | 支持                       | 不支持    |



如果说 我们只是在某个业务逻辑中需要获取一下配置文件中的某项值,使用@Value

如果说,我们专门编写了一个javaBean来和配置文件进行映射,我们就直接使用@ConfigurationProperties



### 3 配置文件注入数据校验值

```java
@Component
@ConfigurationProperties(prefix = "person")
@Validated
public class Person {
/**
* <bean class="Person">
* <property name="lastName" value="字面量/${key}从环境变量、配置文件中获取值/#
{SpEL}"></property>
* <bean/>
*/
//lastName必须是邮箱格式
@Email
//@Value("${person.last‐name}")
private String lastName;
//@Value("#{11*2}")
private Integer age;
//@Value("true")
private Boolean boss;
private Date birth;
private Map<String,Object> maps;
private List<Object> lists;
private Dog dog;
```

### 4 、@PropertySource&@ImportResource&@Bean

@PropertySource：加载指定的配置文件；

```java
/**
* 将配置文件中配置的每一个属性的值，映射到这个组件中
* @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
* prefix = "person"：配置文件中哪个下面的所有属性进行一一映射
*
* 只有这个组件是容器中的组件，才能容器提供的@ConfigurationProperties功能；
* @ConfigurationProperties(prefix = "person")默认从全局配置文件中获取值；
*
*/
@PropertySource(value = {"classpath:person.properties"})
@Component
@ConfigurationProperties(prefix = "person")
//@Validated
public class Person {
/**
* <bean class="Person">
* <property name="lastName" value="字面量/${key}从环境变量、配置文件中获取值/#
{SpEL}"></property>
* <bean/>
*/
//lastName必须是邮箱格式
// @Email
//@Value("${person.last‐name}")
private String lastName;
//@Value("#{11*2}")
private Integer age;
//@Value("true")
private Boolean boss;
```

@ImportResource：导入Spring的配置文件，让配置文件里面的内容生效；
Spring Boot里面没有Spring的配置文件，我们自己编写的配置文件，也不能自动识别；
想让Spring的配置文件生效，加载进来；@ImportResource标注在一个配置类上

```java
@ImportResource(locations = {"classpath:beans.xml"})
导入Spring的配置文件让其生效
```

```xml
<?xml version="1.0" encoding="UTF‐8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema‐instance"
xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring‐beans.xsd">
<bean id="helloService" class="com.atguigu.springboot.service.HelloService"></bean>
</beans>
```



SpringBoot推荐给容器中添加组件的方式；推荐使用全注解的方式
1、配置类@Configuration------>Spring配置文件
2、使用@Bean给容器中添加组件

```java
/**
* @Configuration：指明当前类是一个配置类；就是来替代之前的Spring配置文件
*
* 在配置文件中用<bean><bean/>标签添加组件
*
*/
@Configuration
public class MyAppConfig {
//将方法的返回值添加到容器中；容器中这个组件默认的id就是方法名
@Bean
public HelloService helloService02(){
System.out.println("配置类@Bean给容器中添加组件了...");
return new HelloService();
```





##4 配置文件占位符

### 1用随机数

```prope
${random.value}、${random.int}、${random.long}
${random.int(10)}、${random.int[1024,65536]}
```



```properties
person.last-name=张三${random.uuid}
person.age=${random.int}
person.boss=false
person.birth=2017/12/18
person.map.k1=v1
person.map.skill=huodun
person.list=a,b,c
person.dog.name=${person.last-name}dog
person.dog.age=15

```



###2 属性配置占位符

可以再配置文件中引用前面配置过的属性(优先级前面配置过的这里都能用)

${app.name:默认值} 来指定找不到属性时的默认值



## 5 Profile

profile是Spring对不同 环境提供不同配置功能的支持 可以通过激活 指定参数等方式快速切换环境

1.多profile文件形式

​	我们在主配置文件编写的时候 文件名可以是

 application-{profile}.properties/yml:

​	application-dev.properties application-prod.properties

默认使用application.properties

3 激活方式：

​	命令行 --spring.profiles.active=dev

​	配置文件 spring.profiles.active=dev

​	jvm参数: -Dspring.profiles.active=dev

2 yml 支持文档块模式

```yaml
server:
  port: 8081
spring:
  profiles:
    active: prod

---
spring:
  profiles: dev
server:
  port: 8082


---
spring:
  profiles: prod
server:
  port: 8083
```





## 6 spring boot 配置文件加载位置

spring boot  启动会扫描一下位置的application.properties 或者application.yml 文件作为Spring Boot 的默认配置文件

–file:./config/
–file:./
–classpath:/config/
–classpath:/
–以上是按照优先级从高到低的顺序，所有位置的文件都会被加载，高优先级配置内容会覆盖低优先级配置内容。
–我们也可以通过配置spring.config.location来改变默认配置

Spring Boot 会从这四个位置全部加载主配置文件：**互补配置**



##8自动配置原理

配置文件到底能写什么? 怎么写?自动配置原理;

[配置文件属性参照](https://docs.spring.io/spring-boot/docs/1.5.12.RELEASE/reference/htmlsingle/)

**自动配置原理**

	>1 spring boot 启动的时候加载主配置类，开启了自动配置功能 @EnableAutoConfiguration
	>
	>2 @EnableAutoConfiguration作用: 
	>
	>1. ​
	>
	>2. ​	利用EnableAutoConfigurationImportSelector给容器中导入一些组件
	>
	>3. ​	详细可以查看AutoConfigurationImportSelector.selectImports()方法
	>
	>4. ​
	>
	>5. ​	List<String> configurations = getCandidateConfigurations(annotationMetadata,      attributes);//获取候选的配置
	>
	>   ```java
	>   SpringFactoriesLoader.loadFactoryNames(
	>   				getSpringFactoriesLoaderFactoryClass(), getBeanClassLoader());
	>   扫描所有jar包类路径下 META-INF/spring.factories
	>   把扫描到的这些文件的内容包装成properties对象 
	>   从properties 中获取到EnableAutoConfiguration.class(类名)对应的值
	>   然后把他们添加到容器中
	>   ```
	>
	>   **将类路径下META-INF/spring.factories里面配置的所有EnableAutoConfiguration的值加入到容器中**
	>
	>   ```xml
	>   # Auto Configure
	>   org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
	>   org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.cloud.CloudAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.couchbase.CouchbaseAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.cassandra.CassandraRepositoriesAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.couchbase.CouchbaseDataAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.couchbase.CouchbaseRepositoriesAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.ldap.LdapDataAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.ldap.LdapRepositoriesAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.solr.SolrRepositoriesAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.elasticsearch.jest.JestAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.hazelcast.HazelcastJpaDependencyAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.ldap.LdapAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.mail.MailSenderValidatorAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.mobile.DeviceResolverAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.mobile.DeviceDelegatingViewResolverAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.mobile.SitePreferenceAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.reactor.ReactorAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.security.SecurityFilterAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.security.FallbackWebSecurityAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.security.oauth2.OAuth2AutoConfiguration,\
	>   org.springframework.boot.autoconfigure.sendgrid.SendGridAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.session.SessionAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.social.SocialWebAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.social.FacebookAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.social.LinkedInAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.social.TwitterAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.web.HttpEncodingAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.websocket.WebSocketMessagingAutoConfiguration,\
	>   org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration
	>   ```
	>
	>   每一个这样的xxxAutoConfiguration类都是容器中的一个组件 都加入到容器中;用他们来做自动配置
	>
	>   3每一个自动配置类进行自动配置功能
	>
	>   4.以**HttpEncodingAutoConfiguration**（http编码自动配置）为例解释自动配置原理:
	>
	>   ```java
	>   @Configuration//表示这是一个配置类 也可以给容器中添加组件
	>   @EnableConfigurationProperties(HttpEncodingProperties.class)//启用指定类的ConfigurationProperties功能 将配置文件中的值和HttpEncodingProperties 绑定起来，并将HttpEncodingProperties注册为spring容器的一个bean  
	>   @ConditionalOnWebApplication//Spring底层@Condition注解 根据不同的条件 如果满足指定的条件 整个配置类里面的配置就会生效：判断当前的应用是否是web应用 如果是 当前配置类生效，如果不是 则不生效
	>   @ConditionalOnClass(CharacterEncodingFilter.class)//判断当前想有没有这个类 CharacterEncodingFilter:解决乱码的过滤器
	>   @ConditionalOnProperty(prefix = "spring.http.encoding", value = "enabled", matchIfMissing = true)//判断配置文件中是否存在某个配置  spring.http.encoding.enabled 如果不存在 判断也是成立的 即使配置文件中不配置spring.http.encoding.enabled=true这个值 ,也默认为生效的
	>   //根据当前不同的条件判断 决定这个类是否生效  如果生效 则配置类生效 则会向Spring容器中注入组件
	>   public class HttpEncodingAutoConfiguration {
	>     @Bean
	>   	@ConditionalOnMissingBean(CharacterEncodingFilter.class)
	>   	public CharacterEncodingFilter characterEncodingFilter() {
	>   		CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
	>   		filter.setEncoding(this.properties.getCharset().name());
	>   		filter.setForceRequestEncoding(this.properties.shouldForce(Type.REQUEST));
	>   		filter.setForceResponseEncoding(this.properties.shouldForce(Type.RESPONSE));
	>   		return filter;
	>   	}
	>   }
	>
	>   ```
	>
	>   ```java
	>   @ConfigurationProperties(prefix = "spring.http.encoding")//从配置文件中获取指定的值和bean的属性进行绑定
	>   public class HttpEncodingProperties {
	>   ```
	>
	>   5 所有能再配置文件中配置的属性都是在xxxProperties类中封装着  配置文件中能配置什么就可以参照某一个功能对应的属性类
	>
	>6. **精髓：**
	>
	>   **spring boot启动的时候会加载大量的自动配置类**
	>
	>   **我们看我们需要的功能有没有spring boot 默认写好的自动配置类**
	>
	>   **我们再来看这个自动配置类到底配置了哪些组件（只要我们要用的组件由 我们就不需要再来配置）**
	>
	>   **给容器中自动添加组件的时候 会从properties类中获取某些属性 我们就可以再配置文件中指定这些属性的值**
	>
	>7. xxxAutoConfiguration:自动配置类 
	>
	>   1. 给容器中添加组件
	>
	>   2. xxx properties封装配置文件中的相关属性;
	>
	>      ​
	
		### 2 细节
	
	### 1 @Condition派生注解(Spring注解版原生的@Condition作用)

作用:必须是@condition指定的条件成立 才给容器中添加组件,配置文件里面的所有内容才能生效

![QQ截图20180428114043](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\QQ截图20180428114043.png)

自动配置类必须在一定的条件下才能生效

我们可以通过启用debug=true属性 来让控制台打印自动配置报告 这样我们就可以很方便的知道哪些自动配置类生效了

## 三 日志框架

市面上的日志框架；
JUL、JCL、Jboss-logging、logback、log4j、log4j2、slf4j....



市场上存在非常多的日志框架。JUL（java.util.logging），JCL（Apache Commons Logging），Log4j，Log4j2，Logback、SLF4j、jboss-logging等。Spring Boot在框架内容部使用JCL，spring-boot-starter-logging采用了slf4j+logback的形式，Spring Boot也能自动适配（jul、log4j2、logback）并简化配置

| 日志门面                                  | 日志实现                   |
| ------------------------------------- | ---------------------- |
| JCL（Jakarta Commons Logging）          | Log4j                  |
| SLF4j（Simple Logging Facade for Java） | JUL（java.util.logging） |
| jboss-logging                         | Log4j2   Logback       |

左边选一个门面（抽象层）、右边来选一个实现；
日志门面： SLF4J；
日志实现：Logback；
SpringBoot：底层是Spring框架，Spring框架默认是用JCL；‘
**SpringBoot选用 SLF4j和logback；**



### 2 SLF4J的使用

### 1，如何在系统中使用SLF4j https://www.slf4j.org

	>以后开发的时候，日志记录方法的调用，不应该来直接调用日志的实现类，而是调用日志抽象层里面的方法；
	>给系统里面导入slf4j的jar和 logback的实现jar
	>
	>

![QQ截图20180502152110](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\QQ截图20180502152110.png)

每一个日志的实现框架都有自己的配置文件。**使用slf4j以后，配置文件还是做成日志实现框架自己本身的配置文**
**件；**

### 2 ，遗留问题

a（slf4j+logback）: Spring（commons-logging）、Hibernate（jboss-logging）、MyBatis、xxxx
统一日志记录，即使是别的框架和我一起统一使用slf4j进行输出？

![QQ截图20180502153221](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\QQ截图20180502153221.png)



如何让系统中所有的日志都统一到slf4j；
1、将系统中其他日志框架先排除出去；
2、用中间包来替换原有的日志框架；
3、我们导入slf4j其他的实现

![QQ截图20180502164619](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\QQ截图20180502164619.png)



总结：
1）、SpringBoot底层也是使用slf4j+logback的方式进行日志记录
2）、SpringBoot也把其他的日志都替换成了slf4j；
3）、中间替换包?

```java
@SuppressWarnings("rawtypes")
public abstract class LogFactory {
static String UNSUPPORTED_OPERATION_IN_JCL_OVER_SLF4J =
"http://www.slf4j.org/codes.html#unsupported_operation_in_jcl_over_slf4j";
static LogFactory logFactory = new SLF4JLogFactory();
```

![QQ截图20180503143541](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\QQ截图20180503143541.png)

4）、如果我们要引入其他框架？一定要把这个框架的默认日志依赖移除掉？

SpringBoot能自动适配所有的日志，而且底层使用slf4j+logback的方式记录日志，引入其他框架的时候，只需要
把这个框架依赖的日志框架排除掉即可；



### 4，日志使用

SpringBoot默认帮我们配置好了日志；

```java
//记录器
Logger logger = LoggerFactory.getLogger(getClass());
@Test
public void contextLoads() {
//System.out.println();
//日志的级别；
//由低到高 trace<debug<info<warn<error
//可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
logger.trace("这是trace日志...");
logger.debug("这是debug日志...");
//SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root
级别
logger.info("这是info日志...");
logger.warn("这是warn日志...");
logger.error("这是error日志...");
}
```



日志配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--
scan：当此属性设置为true时，配置文件如果发生改变，将会被重新加载，默认值为true。
scanPeriod：设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒当scan为true时，此属性生效。默认的时间间隔为1分钟。
debug：当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。
-->
<configuration scan="false" scanPeriod="60 seconds" debug="false">
    <!-- 定义日志的根目录 -->
    <property name="LOG_HOME" value="/app/log" />
    <!-- 定义日志文件名称 -->
    <property name="appName" value="atguigu-springboot"></property>
    <!-- ch.qos.logback.core.ConsoleAppender 表示控制台输出 -->
    <appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <!--
        日志输出格式：
			%d表示日期时间，
			%thread表示线程名，
			%-5level：级别从左显示5个字符宽度
			%logger{50} 表示logger名字最长50个字符，否则按照句点分割。 
			%msg：日志消息，
			%n是换行符
        -->
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </layout>
    </appender>

    <!-- 滚动记录文件，先将日志记录到指定文件，当符合某个条件时，将日志记录到其他文件 -->  
    <appender name="appLogAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!-- 指定日志文件的名称 -->
        <file>${LOG_HOME}/${appName}.log</file>
        <!--
        当发生滚动时，决定 RollingFileAppender 的行为，涉及文件移动和重命名
        TimeBasedRollingPolicy： 最常用的滚动策略，它根据时间来制定滚动策略，既负责滚动也负责出发滚动。
        -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--
            滚动时产生的文件的存放位置及文件名称 %d{yyyy-MM-dd}：按天进行日志滚动 
            %i：当文件大小超过maxFileSize时，按照i进行文件滚动
            -->
            <fileNamePattern>${LOG_HOME}/${appName}-%d{yyyy-MM-dd}-%i.log</fileNamePattern>
            <!-- 
            可选节点，控制保留的归档文件的最大数量，超出数量就删除旧文件。假设设置每天滚动，
            且maxHistory是365，则只保存最近365天的文件，删除之前的旧文件。注意，删除旧文件是，
            那些为了归档而创建的目录也会被删除。
            -->
            <MaxHistory>365</MaxHistory>
            <!-- 
            当日志文件超过maxFileSize指定的大小是，根据上面提到的%i进行日志文件滚动 注意此处配置SizeBasedTriggeringPolicy是无法实现按文件大小进行滚动的，必须配置timeBasedFileNamingAndTriggeringPolicy
            -->
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>100MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <!-- 日志输出格式： -->     
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [ %thread ] - [ %-5level ] [ %logger{50} : %line ] - %msg%n</pattern>
        </layout>
    </appender>

    <!-- 
		logger主要用于存放日志对象，也可以定义日志类型、级别
		name：表示匹配的logger类型前缀，也就是包的前半部分
		level：要记录的日志级别，包括 TRACE < DEBUG < INFO < WARN < ERROR
		additivity：作用在于children-logger是否使用 rootLogger配置的appender进行输出，
		false：表示只用当前logger的appender-ref，true：
		表示当前logger的appender-ref和rootLogger的appender-ref都有效
    -->
    <!-- hibernate logger -->
    <logger name="com.atguigu" level="debug" />
    <!-- Spring framework logger -->
    <logger name="org.springframework" level="debug" additivity="false"></logger>



    <!-- 
    root与logger是父子关系，没有特别定义则默认为root，任何一个类只会和一个logger对应，
    要么是定义的logger，要么是root，判断的关键在于找到这个logger，然后判断这个logger的appender和level。 
    -->
    <root level="info">
        <appender-ref ref="stdout" />
        <appender-ref ref="appLogAppender" />
    </root>
</configuration> 
```

![QQ截图20180503145234](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\QQ截图20180503145234.png)



| Logging System          | Customization                            |
| ----------------------- | ---------------------------------------- |
| Logback                 | `logback-spring.xml`, `logback-spring.groovy`, `logback.xml`, or `logback.groovy` |
| Log4j2                  | `log4j2-spring.xml` or `log4j2.xml`      |
| JDK (Java Util Logging) | `logging.properties`                     |

logback.xml：直接就被日志框架识别了；
logback-spring.xml：日志框架就不直接加载日志的配置项，由SpringBoot解析日志配置，可以使用SpringBoot
的高级Profile功能



## 四，WEB开发

> 使用Spring Boot 
>
> ​	1创建Spring Boot 应用 选用我们需要的模块
>
> ​	2 Spring Boot 已经将这些场景配置好了 只需要在配置文件中配置少量配置就可以运行起来
>
> ​	3 自己编写业务代码
>
> 

自动配置原理?

​	这个场景Spring Boot 帮我们配置了什么 能不能修改 能修改哪些配置  能不能扩展

​	

```java
xxxAutoConfiguration:帮我们给容器中自动配置组件
xxxProperties:配置类来封装配置文件中的内容
```





### 2 Spring Boot 对静态资源的映射规则

​	WebMvcAutoConfiguration

```java
@ConfigurationProperties(prefix = "spring.resources", ignoreUnknownFields = false)
public class ResourceProperties {
  //可以设置和资源相关的参数  缓存时间等
```



```java
@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			if (!this.resourceProperties.isAddMappings()) {
				logger.debug("Default resource handling disabled");
				return;
			}
			Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
			CacheControl cacheControl = this.resourceProperties.getCache()
					.getCachecontrol().toHttpCacheControl();
			if (!registry.hasMappingForPattern("/webjars/**")) {
				customizeResourceHandlerRegistration(registry
						.addResourceHandler("/webjars/**")
						.addResourceLocations("classpath:/META-INF/resources/webjars/")
						.setCachePeriod(getSeconds(cachePeriod))
						.setCacheControl(cacheControl));
			}
			String staticPathPattern = this.mvcProperties.getStaticPathPattern();
			if (!registry.hasMappingForPattern(staticPathPattern)) {
				customizeResourceHandlerRegistration(
						registry.addResourceHandler(staticPathPattern)
								.addResourceLocations(getResourceLocations(
										this.resourceProperties.getStaticLocations()))
								.setCachePeriod(getSeconds(cachePeriod))
								.setCacheControl(cacheControl));
			}
		}
```

1.所有/webjars/** 都去classpath:/META-INF/resources/webjars/下找资源



http://www.webjars.org/

![jquery20180503155635](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\jquery20180503155635.png)

localhost:8080/webjars/jquery/2.1.4/jquery.js

```xml
<!--jquery的webjars-->
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>jquery</artifactId>
			<version>2.1.4</version>
		</dependency>
在访问的时候只需要写webjars下面资源名称即可
```



2) /** :访问当前项目的任何资源 (静态资源文件夹)

```java
"classpath:/META-INF/resources/",
"classpath:/resources/",
"classpath:/static/",
"classpath:/public/"
```

![1](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\1.png)

http://localhost:8080/asserts/img/1.jpg



```java
//欢迎页映射
@Bean
		public WelcomePageHandlerMapping welcomePageHandlerMapping(
				ApplicationContext applicationContext) {
			return new WelcomePageHandlerMapping(
					new TemplateAvailabilityProviders(applicationContext),
					applicationContext, getWelcomePage(),
					this.mvcProperties.getStaticPathPattern());
		}

	private Optional<Resource> getWelcomePage() {
			String[] locations = getResourceLocations(
					this.resourceProperties.getStaticLocations());
			return Arrays.stream(locations).map(this::getIndexHtml)
					.filter(this::isReadable).findFirst();
		}
	private Resource getIndexHtml(String location) {
			return this.resourceLoader.getResource(location + "index.html");
		}


```

欢迎页：静态资源文件夹下的所有的index.html页面;被/**映射

配置喜欢的图标

​	所有的**/favicon.ico都是在静态资源文件夹下找

```java
@Configuration
		@ConditionalOnProperty(value = "spring.mvc.favicon.enabled", matchIfMissing = true)
		public static class FaviconConfiguration implements ResourceLoaderAware {

			private final ResourceProperties resourceProperties;

			private ResourceLoader resourceLoader;

			public FaviconConfiguration(ResourceProperties resourceProperties) {
				this.resourceProperties = resourceProperties;
			}

			@Override
			public void setResourceLoader(ResourceLoader resourceLoader) {
				this.resourceLoader = resourceLoader;
			}

			@Bean
			public SimpleUrlHandlerMapping faviconHandlerMapping() {
				SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
				mapping.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
				mapping.setUrlMap(Collections.singletonMap("**/favicon.ico",
						faviconRequestHandler()));
				return mapping;
			}

			@Bean
			public ResourceHttpRequestHandler faviconRequestHandler() {
				ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
				requestHandler.setLocations(resolveFaviconLocations());
				return requestHandler;
			}

			private List<Resource> resolveFaviconLocations() {
				String[] staticLocations = getResourceLocations(
						this.resourceProperties.getStaticLocations());
				List<Resource> locations = new ArrayList<>(staticLocations.length + 1);
				Arrays.stream(staticLocations).map(this.resourceLoader::getResource)
						.forEach(locations::add);
				locations.add(new ClassPathResource("/"));
				return Collections.unmodifiableList(locations);
			}

		}
```



### 3 模板引擎

jsp  velocity Freemarker Thymeleaf

![0](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\0.png)



Spring Boot 推荐thymeleaf ： 语法更简单 功能更强大

### 1，引入thymeleaf

```java
	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
```

### 2，Thymeleaf使用&语法

```java
@ConfigurationProperties(prefix = "spring.thymeleaf")
public class ThymeleafProperties {

	private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;

	public static final String DEFAULT_PREFIX = "classpath:/templates/";

	public static final String DEFAULT_SUFFIX = ".html";
  //只要我们把HTML页面放在classpath:/templates/下,thymeleaf就能自动渲染
```



使用 ：

1导入thymeleaf的名称空间

```html
<html xmlns:th="http://www.thymeleaf.org" lang="en">
```

2 使用thymeleaf的语法

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>成功</h1>
<div th:text="${name}">这里显示欢迎信息</div>
</body>
</html>
```

### 3，语法规则

​	1.  th:text ；改变当前元素里面的文本内容

​		th:任意html属性;替换原生属性

![-1](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\-1.png)



2 表达式

```properties
Simple expressions:(表达式语法)
Variable Expressions: ${...} 获取变量值:OGNL表达式
		1.获取对象的属性 调用方法
		2.使用内置的基本对象
            #ctx : the context object.
            #vars: the context variables.
            #locale : the context locale.
            #request : (only in Web Contexts) the HttpServletRequest object.
            #response : (only in Web Contexts) the HttpServletResponse object.
            #session : (only in Web Contexts) the HttpSession object.
            #servletContext : (only in Web Contexts) the ServletContext object.
         3.内置一些工具对象
         	#execInfo : information about the template being processed.
            #messages : methods for obtaining externalized messages inside variables expressions, in the same way as they
            would be obtained using #{…} syntax.
            #uris : methods for escaping parts of URLs/URIs
            #conversions : methods for executing the configured conversion service (if any).
            #dates : methods for java.util.Date objects: formatting, component extraction, etc.
            #calendars : analogous to #dates , but for java.util.Calendar objects.
            #numbers : methods for formatting numeric objects.
            #strings : methods for String objects: contains, startsWith, prepending/appending, etc.
            #objects : methods for objects in general.
            #bools : methods for boolean evaluation.
            #arrays : methods for arrays.
            #lists : methods for lists.
            #sets : methods for sets.
            #maps : methods for maps.
            #aggregates : methods for creating aggregates on arrays or collections.
            #ids : methods for dealing with id attributes that might be repeated (for example, as a result of an iteration).
Selection Variable Expressions: *{...} 选择表达式 和${}在功能上是一样的
	配合 th:object进行使用
		<div th:object="${session.user}">
            <p>Name: <span th:text="*{firstName}">Sebastian</span>.</p>
            <p>Surname: <span th:text="*{lastName}">Pepper</span>.</p>
            <p>Nationality: <span th:text="*{nationality}">Saturn</span>.</p>
         </div>
Message Expressions: #{...} 获取国际化内容
Link URL Expressions: @{...} 定义超链接：
#<a href="details.html" th:href="@{/order/{orderId}/details(orderId=${o.id})}">view</a>
Fragment Expressions: ~{...} 片段引用表达式
	#<div th:insert="~{commons :: main}">...</div>
Literals
Text literals: 'one text' , 'Another one!' ,…
Number literals: 0 , 34 , 3.0 , 12.3 ,…
Boolean literals: true , false
Null literal: null
Literal tokens: one , sometext , main ,…
Text operations:
String concatenation: +
Literal substitutions: |The name is ${name}|
Arithmetic operations:
Binary operators: + , - , * , / , %
Minus sign (unary operator): -
Boolean operations:
Binary operators: and , or
Boolean negation (unary operator): ! , not
Comparisons and equality:
Comparators: > , < , >= , <= ( gt , lt , ge , le )
Equality operators: == , != ( eq , ne )
Conditional operators:
If-then: (if) ? (then)
If-then-else: (if) ? (then) : (else)
Default: (value) ?: (defaultvalue)
Special tokens:No-Operation: _
```



### 4 ，SpringMVC自动配置

https://docs.spring.io/spring-boot/docs/2.0.1.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications

### 27.1.1 Spring MVC Auto-configuration

​	Spring Boot自动配置好了SpringMVC 

​	以下是Spring Boot对Springmvc的默认配置

Spring Boot provides auto-configuration for Spring MVC that works well with most applications.

The auto-configuration adds the following features on top of Spring’s defaults:

- Inclusion of `ContentNegotiatingViewResolver` and `BeanNameViewResolver` beans.

  ​	自动配置了视图解析器(视图解析器:根据方法的返回值得到视图对象View ,视图对象决定如何渲染(转发? 重定向?))

  ​	ContentNegotiatingViewResolver:组合所有的视图解析器 

  ​	如何定制:我们可以自己给容器中添加一个视图解析器 ；自动将其组合进来

- Support for serving static resources, including support for WebJars (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.0.1.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-static-content))).

  ​	静态资源

- Automatic registration of `Converter`, `GenericConverter`, and `Formatter` beans.

  ​	自动注册转换器 格式化器 

  我们自己添加的格式化器 转换器 我们只要放到容器中即可

- Support for `HttpMessageConverters` (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.0.1.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-message-converters)).

  ​	SpringMVC中用来转换Http请求与响应的

  ​	

- Automatic registration of `MessageCodesResolver` (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.0.1.RELEASE/reference/htmlsingle/#boot-features-spring-message-codes)).

  ​	定义错误代码生成规则

- Static `index.html` support. 静态首页 

- Custom `Favicon` support (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.0.1.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-favicon)). favicon.ico

- Automatic use of a `ConfigurableWebBindingInitializer` bean (covered [later in this document](https://docs.spring.io/spring-boot/docs/2.0.1.RELEASE/reference/htmlsingle/#boot-features-spring-mvc-web-binding-initializer)).

  ​

  ​

  org.springframework.boot.autoconfigure.web	:WEB 的所有的自动场景;

If you want to keep Spring Boot MVC features and you want to add additional [MVC configuration](https://docs.spring.io/spring/docs/5.0.5.RELEASE/spring-framework-reference/web.html#mvc) (interceptors, formatters, view controllers, and other features), you can add your own `@Configuration` class of type `WebMvcConfigurer` but **without** `@EnableWebMvc`. If you wish to provide custom instances of `RequestMappingHandlerMapping`, `RequestMappingHandlerAdapter`, or `ExceptionHandlerExceptionResolver`, you can declare a `WebMvcRegistrationsAdapter` instance to provide such components.

If you want to take complete control of Spring MVC, you can add your own `@Configuration` annotated with `@EnableWebMvc`.



### 5，如何修改SpringBoot的默认配置

模式：

​	1SpringBoot在自动配置很多组件的时候 先看容器中有没有用户自己的配置的组件（@Bean @Component）如果有 就用用户自己配置的;如果没有 才自动配置;如果有些组件由多个(ViewResolver) ;将用户配置和默认的组合起来;

​	在SpringBoot中会有非常多的xxxConfiguration帮助我们进行扩展配置

扩展SpringMVC 

**编写一个配置类(@Configuration) 是WebMvcConfigurationAdapter类型 不能标注@EnableWebMvc** 

既保留了所有的自动配置 也能用我们扩展的配置

```java
/*
扩展SpringMVC的功能
 */
@Configuration
public class MVCConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/able").setViewName("success");

    }
}
```

原理:

​	1 WebMvcAutoConfiguration是SpringMVC自动配置类

​	2 在做其他自动配置时 会导入@Import(EnableWebMvcConfigutation.class)

​	3 容器中所有的WebMvcConfiguration都会一起起作用 

​	4 我们的配置类也会起作用





```java
	@Configuration
	@Import(EnableWebMvcConfiguration.class)
	@EnableConfigurationProperties({ WebMvcProperties.class, ResourceProperties.class })
	@Order(0)
	public static class WebMvcAutoConfigurationAdapter
			implements WebMvcConfigurer, ResourceLoaderAware {
/**
	 * Configuration equivalent to {@code @EnableWebMvc}.
	 */
	@Configuration
	public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration {
      
    }
      @Configuration
public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {

	private final WebMvcConfigurerComposite configurers = new WebMvcConfigurerComposite();

	//从容器中获取所有的WebMvcConfigurer
	@Autowired(required = false)
	public void setConfigurers(List<WebMvcConfigurer> configurers) {
		if (!CollectionUtils.isEmpty(configurers)) {
			this.configurers.addWebMvcConfigurers(configurers);
		}
	}
      
```



### 3 全面接管SpringMVC

​	springBoot 对SpringMVC自动配置不需要了 所有都是我们自己配  只需要我们在配置类中添加@EnableWebMVC

原理:

​	为什么@EnableWebMVC自动配置就失效了

​	1 @EnableWebMvc的核心

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(DelegatingWebMvcConfiguration.class)
public @interface EnableWebMvc {
}
```

```java
@Configuration
public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {
```

```java
@Configuration
@ConditionalOnWebApplication(type = Type.SERVLET)
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class })
//容器中没有这个组件的时候 自动配置类才生效
@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
@AutoConfigureAfter({ DispatcherServletAutoConfiguration.class,
		ValidationAutoConfiguration.class })
public class WebMvcAutoConfiguration {
```



@EnableWebMvc将WebMvcConfigurationSupport组件导入进来；导入的只是springmvc最基本的功能



## 6 Restful CRUD

### 1 访问默认首页

```java
/*
扩展SpringMVC的功能
 */
@Configuration
public class MVCConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/able").setViewName("success");

    }

    /**所有的WebMvcAdapter都会一起起作用
     *
     * @return
     */
    @Bean
    WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
            }
        };
    }
}
```

### 2 国际化

​	1 编写国际化配置文件

​	2 使用ResourceBundleMessageSource管理国际化资源文件

​	3 在页面使用fmtLmessage取出国际化内容

步骤

​	1.编写国际化配置文件抽取页面需要显示的国际化消息

```properties
login.btn=Sign In
login.password=Password
login.remeber=Remeber Me
login.tip=Please sign in
login.username=UserName
```

​	2 Spring Boot 自动配置好了管理国际化资源文件的组件

```java
@Configuration
@ConditionalOnMissingBean(value = MessageSource.class, search = SearchStrategy.CURRENT)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Conditional(ResourceBundleCondition.class)
@EnableConfigurationProperties
public class MessageSourceAutoConfiguration {
  	@Bean
	@ConfigurationProperties(prefix = "spring.messages")
	public MessageSourceProperties messageSourceProperties() {
		return new MessageSourceProperties();
	}
}


public class MessageSourceProperties {

	/**
	 * Comma-separated list of basenames (essentially a fully-qualified classpath
	 * location), each following the ResourceBundle convention with relaxed support for
	 * slash based locations. If it doesn't contain a package qualifier (such as
	 * "org.mypackage"), it will be resolved from the classpath root.
	 */
  //我们的配置文件可以直接放在类路径下叫Messages.properties 
	private String basename = "messages";
  
```

原理：

​	国际化Local(区域信息对象);LocalResolver(获取区域信息对象)

```java
		@Bean
		@ConditionalOnMissingBean
		@ConditionalOnProperty(prefix = "spring.mvc", name = "locale")
		public LocaleResolver localeResolver() {
			if (this.mvcProperties
					.getLocaleResolver() == WebMvcProperties.LocaleResolver.FIXED) {
				return new FixedLocaleResolver(this.mvcProperties.getLocale());
			}
			AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
			localeResolver.setDefaultLocale(this.mvcProperties.getLocale());
			return localeResolver;
		}
//默认就是根据请求头带来的区域信息获取local进行国际化
AcceptHeaderLocaleResolver
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		Locale defaultLocale = getDefaultLocale();
		if (defaultLocale != null && request.getHeader("Accept-Language") == null) {
			return defaultLocale;
		}
		Locale requestLocale = request.getLocale();
		List<Locale> supportedLocales = getSupportedLocales();
		if (supportedLocales.isEmpty() || supportedLocales.contains(requestLocale)) {
			return requestLocale;
		}
		Locale supportedLocale = findSupportedLocale(request, supportedLocales);
		if (supportedLocale != null) {
			return supportedLocale;
		}
		return (defaultLocale != null ? defaultLocale : requestLocale);
	}
```



### 3登录

​	开发期间模板引擎页面修改以后 要实时生效

​	 1 禁用模板引擎的缓存

```prope
spring.thymeleaf.cache=false
```



​	2 页面修改完成以后**ctrl+f9** 重新编译



​	登录错误消息的显示:

```html
<p style="color:red" th:text="${msg}" th:if="${not #strings.isEmpty(msg)}"></p>
```

​	3 拦截器进行登录检查

### CRUD 员工列表

​	实验要求：RestfulCRUD: CRUD满足Rest风格

URI:/资源名称/资源标识    HTTP请求方式区分对资源的CRUD操作

|      | 普通的CRUD操作（URI）来区分操作      | RestFulCRUD      |
| ---- | ------------------------ | ---------------- |
| 查询   | getEmp                   | emp--get         |
| 添加   | addEmp?xxx               | emp--post        |
| 修改   | updateEmp?id=xxx&xxx=xxx | emp/{id}--put    |
| 删除   | deleteEmp?id=1           | emp/{id}--delete |

实验的请求架构

|              | 请求URI    | 请求方式   |
| ------------ | -------- | ------ |
| 查询所有员工       | emps     | get    |
| 查询某个员工       | emp/{id} | get    |
| 来到添加页面       | emp      | get    |
| 添加员工         | emp      | post   |
| 来到修改页面(查出员工) | emp/{id} | get    |
| 修改员工         | emp      | put    |
| 删除员工         | emp/{id} | delete |

员工列表：

	### tyymeleaf公共页面元素抽取

```html
抽取公共片段
<div th:fragment="copy">
&copy; 2011 The Good Thymes Virtual Grocery
</div>
引入公共片段:
<div th:insert="~{footer :: copy}"></div>
~{templatename::selector}:模板名::选择器名
~{templatename::fragmentname}模板名::片段名
默认效果：
insert的功能片段在div标签中
如果使用th:insert 等属性进行引入 ，可以不用写~{};
行内写法：[[~{}]]  [(~{})]必须加上

```

三种引入公共片段的th属性

​	th:insert    ：将公共片段整个插入到指定元素中

​	th:replace   :将公共片段替换到指定的元素中

​	th:include ：将被引入的片段的内容包含进标签中

```html
<footer th:fragment="copy">
&copy; 2011 The Good Thymes Virtual Grocery
</footer>
引入方式
<div th:insert="footer :: copy"></div>
<div th:replace="footer :: copy"></div>
<div th:include="footer :: copy"></div>
效果:
<div>
<footer>
&copy; 2011 The Good Thymes Virtual Grocery
</footer>
</div>
<footer>
&copy; 2011 The Good Thymes Virtual Grocery
</footer>
<div>
&copy; 2011 The Good Thymes Virtual Grocery
</
```

在引入片段的时候传入参数:

提交的数据格式不对：生日日期

2017-12-12;2017/12/12;2017.12.12

日期的格式化:SpringMVC将页面提交的值需要转换为指定的类型

2017-12-12--Date:类型转换  格式化

默认日期是按照/的方式



## 7 错误处理机制

	### 1 spring boot 默认的错误处理机制

​	返回一个默认的错误页面



![2](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\2.png)

![4](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\4.png)

浏览器发送请求的请求头

如果是其他客户端访问 默认相应一个 json数据

![3](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\3.png)

![5](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\5.png)



原理:

​	可以参照ErrorMvcAutoConfiguration：错误处理的自动配置

​	给容器中添加了以下组件:

​		DefaultErrorAttributes

```java
@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest,
			boolean includeStackTrace) {
		Map<String, Object> errorAttributes = new LinkedHashMap<>();
		errorAttributes.put("timestamp", new Date());
		addStatus(errorAttributes, webRequest);
		addErrorDetails(errorAttributes, webRequest, includeStackTrace);
		addPath(errorAttributes, webRequest);
		return errorAttributes;
	}
```



​		BasicErrorController 处理默认/error请求

```java
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class BasicErrorController extends AbstractErrorController {
  //产生Html类型的数据  浏览器发送的请求来到这个方法处理
  @RequestMapping(produces = "text/html")
	public ModelAndView errorHtml(HttpServletRequest request,
			HttpServletResponse response) {
		HttpStatus status = getStatus(request);
		Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
				request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
		response.setStatus(status.value());
      //去哪个页面作为错误页面;包含页面地址和页面内容
		ModelAndView modelAndView = resolveErrorView(request, response, status, model);
		return (modelAndView == null ? new ModelAndView("error", model) : modelAndView);
	}

  //产生json数据  其他客户端来到这个方法处理
	@RequestMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> body = getErrorAttributes(request,
				isIncludeStackTrace(request, MediaType.ALL));
		HttpStatus status = getStatus(request);
		return new ResponseEntity<>(body, status);
	}
```



​		

​		ErrorPageCustomizer

```java
@Value("${error.path:/error}")
	private String path = "/error";
系统出现错误以后 来到error请求进行处理请求(web.xml注册的错误页面规则)
```





​		DefaultErrorViewResolverConfiguration



​		DefaultErrorViewResolver

```java
@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status,
			Map<String, Object> model) {
		ModelAndView modelAndView = resolve(String.valueOf(status), model);
		if (modelAndView == null && SERIES_VIEWS.containsKey(status.series())) {
			modelAndView = resolve(SERIES_VIEWS.get(status.series()), model);
		}
		return modelAndView;
	}

	private ModelAndView resolve(String viewName, Map<String, Object> model) {
      //默认springBoot可以去找到一个页面? error/404
		String errorViewName = "error/" + viewName;
      //模板引擎可以解析这个地址 就用模板引擎解析
		TemplateAvailabilityProvider provider = this.templateAvailabilityProviders
				.getProvider(errorViewName, this.applicationContext);
		if (provider != null) {
          	//模板引擎可用 的情况下就返回到errorViewName指定的视图地址
			return new ModelAndView(errorViewName, model);
		}
      //模板引擎不可用 ,在静态资源文件夹下找errorViewName对应的页面 error/404.html 静态资源文件夹下有就返回页面 没有就饭后null
		return resolveResource(errorViewName, model);
	}
private ModelAndView resolveResource(String viewName, Map<String, Object> model) {
		for (String location : this.resourceProperties.getStaticLocations()) {
			try {
				Resource resource = this.applicationContext.getResource(location);
				resource = resource.createRelative(viewName + ".html");
				if (resource.exists()) {
					return new ModelAndView(new HtmlResourceView(resource), model);
				}
			}
			catch (Exception ex) {
			}
		}
		return null;
	}
```



步骤：

​	一旦系统出现4xx 或者5xx之类的错误:ErrorPageCustomizer就会生效(定制的错误的响应规则);就会来到/error请求;就会被BaseErrorController处理；

​		1响应页面;去哪个页面是由DefaultErrorViewResolver解析的

```java
protected ModelAndView resolveErrorView(HttpServletRequest request,
			HttpServletResponse response, HttpStatus status, Map<String, Object> model) {
  //所有的ErrorViewResolver得到ModelAndView 
		for (ErrorViewResolver resolver : this.errorViewResolvers) {
			ModelAndView modelAndView = resolver.resolveErrorView(request, status, model);
			if (modelAndView != null) {
				return modelAndView;
			}
		}
		return null;
	}
```



​		

​	如何定制错误响应：

​		如何定制错误页面

​			1有模板引擎的情况下,error/状态码，[将错误页面命名为错误状态码.html 放在模板引擎文件里的error文件夹下]

​			2我们可以使用4xx 与5xx作为错误页面的文件名来匹配这种类型的所有错误;精确优先（优先寻找精确状态码.html）

​			页面能获取的信息

```java
errorAttributes.put("timestamp", new Date());//时间戳
addStatus(errorAttributes, webRequest);//状态码
addErrorDetails(errorAttributes, webRequest, includeStackTrace);//Error错误提示
addPath(errorAttributes, webRequest);
```

​	没有模板引擎(模板引擎找不到这个错误页面) 在静态资源文件夹下寻找

​	以上都没有错误页面 就默认来到Spring Boot默认的错误提示页面

​		如何定制错误的json数据

​	1）、自定义异常处理&返回定制json数据； 

```java
@ResponseBody
    @ExceptionHandler(UserNotFondException.class)
    public Map<String,Object> handleException(Exception e){
        Map<String,Object> map=new HashMap<>(3);
        map.put("code","user not exist");
        map.put("message",e.getMessage());
        return map;

    }
//没有自适应效果...
```

2）、转发到/error进行自适应响应效果处理 

```java
 @ExceptionHandler(UserNotFondException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>(3);
        //Integer statusCode = (Integer) request
        //				.getAttribute("javax.servlet.error.status_code");
        //传入我们自己的错误状态码 4xx  5xx

        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user not exist");
        map.put("message",e.getMessage());
        request.setAttribute("ext",map);
        return "forward:/error";
    }
```

3）、将我们的定制数据携带出去； 

出现错误以后，会来到/error请求，会被BasicErrorController处理，响应出去可以获取的数据是由
getErrorAttributes得到的（是AbstractErrorController（ErrorController）规定的方法）；
1、完全来编写一个ErrorController的实现类【或者是编写AbstractErrorController的子类】，放在容器中；
2、页面上能用的数据，或者是json返回能用的数据都是通过errorAttributes.getErrorAttributes得到；
容器中DefaultErrorAttributes.getErrorAttributes()；默认进行数据处理的；
自定义ErrorAttributes 

```java
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        errorAttributes.put("company","muye");
        Map<String,Object> map = (Map<String, Object>) webRequest.getAttribute("ext", RequestAttributes.SCOPE_REQUEST);
        errorAttributes.putAll(map);
        return  errorAttributes;
    }
}
```

最终的效果：响应是自适应的，可以通过定制ErrorAttributes改变需要返回的内容， 

### 8、配置嵌入式Servlet容器 

SpringBoot默认使用Tomcat作为嵌入式的Servlet容器； 

问题？
1）、如何定制和修改Servlet容器的相关配置；
1、修改和server有关的配置（ServerProperties【也是EmbeddedServletContainerCustomizer】）； 

```properties
server.port=8081
server.context‐path=/crud
server.tomcat.uri‐encoding=UTF‐8
//通用的Servlet容器设置
server.xxx
//Tomcat的设置
server.tomcat.xxx
```

2、编写一个EmbeddedServletContainerCustomizer：嵌入式的Servlet容器的定制器；来修改Servlet容器的
配置 

```java
@Bean //一定要将这个定制器加入到容器中
public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
return new EmbeddedServletContainerCustomizer() {
//定制嵌入式的Servlet容器相关的规则
@Override
public void customize(ConfigurableEmbeddedServletContainer container) {
container.setPort(8083);
}
};
}
```



### 2）、注册Servlet三大组件【Servlet、Filter、Listener】 

由于SpringBoot默认是以jar包的方式启动嵌入式的Servlet容器来启动SpringBoot的web应用，没有web.xml文
件。
注册三大组件用以下方式
ServletRegistrationBean    FilterRegistrationBean        ServletListenerRegistrationBean 

```java
@Configuration
public class MyServerConfig {

    /**注册三大组件 servlet/filter/listener
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean= new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new MyServlet());
        servletRegistrationBean.setUrlMappings(Arrays.asList("/myservlet"));
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/myservlet","/hello"));
        return filterRegistrationBean;
    }
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        return new ServletListenerRegistrationBean(new MyListener());
    }


}
```





SpringBoot帮我们自动SpringMVC的时候，自动的注册SpringMVC的前端控制器；DIspatcherServlet；
DispatcherServletAutoConfiguration中： 

```java
@Bean(name = DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME)
		@ConditionalOnBean(value = DispatcherServlet.class, name = DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
		public ServletRegistrationBean<DispatcherServlet> dispatcherServletRegistration(
				DispatcherServlet dispatcherServlet) {
			ServletRegistrationBean<DispatcherServlet> registration = new ServletRegistrationBean<>(
              //默认拦截： / 所有请求；包静态资源，但是不拦截jsp请求； /*会拦截jsp
//可以通过server.servletPath来修改SpringMVC前端控制器默认拦截的请求路径
					dispatcherServlet,
					this.serverProperties.getServlet().getServletMapping());
			registration.setName(DEFAULT_DISPATCHER_SERVLET_BEAN_NAME);
			registration.setLoadOnStartup(
					this.webMvcProperties.getServlet().getLoadOnStartup());
			if (this.multipartConfig != null) {
				registration.setMultipartConfig(this.multipartConfig);
			}
			return registration;
		}

```



2）、SpringBoot能不能支持其他的Servlet容器；
3）、替换为其他嵌入式Servlet容器 

默认支持 

tomcat 

jetty

underrow



### 4 嵌入式容器的自动配置原理

EmbeddedServletContainerAutoConfiguration:嵌入式的Servlet容器自动配置

```java
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@ConditionalOnWebApplication
//BeanPostProcessorsRegistrar 给容器中导入这个组件
//导入了EmbeddedServletContainerCustomizerBeanPostProcessor
//后置处理器：bean初始化前后执行(创建完对象 还没有属性赋值)一些逻辑
@Import(BeanPostProcessorsRegistrar.class)
public class EmbeddedServletContainerAutoConfiguration {
  /**
	 * Nested configuration if Tomcat is being used.
	 */
	@Configuration
	@ConditionalOnClass({ Servlet.class, Tomcat.class })//判断当前是否引入tomcat依赖
  //判断当前容器中没有用户自定义的 EmbeddedServletContainerFactory；嵌入式的Servlet容器工厂
  //作用 创建嵌入式的Servlet容器
	@ConditionalOnMissingBean(value = EmbeddedServletContainerFactory.class, search = SearchStrategy.CURRENT)
	public static class EmbeddedTomcat {

		@Bean
		public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory() {
			return new TomcatEmbeddedServletContainerFactory();
		}

	}
  /**
	 * Nested configuration if Jetty is being used.
	 */
	@Configuration
	@ConditionalOnClass({ Server.class, Loader.class, WebAppContext.class })
	public static class JettyWebServerFactoryCustomizerConfiguration {

		@Bean
		public JettyWebServerFactoryCustomizer jettyWebServerFactoryCustomizer(
				Environment environment, ServerProperties serverProperties) {
			return new JettyWebServerFactoryCustomizer(environment, serverProperties);
		}

	}

	/**
	 * Nested configuration if Undertow is being used.
	 */
	@Configuration
	@ConditionalOnClass({ Undertow.class, SslClientAuthMode.class })
	public static class UndertowWebServerFactoryCustomizerConfiguration {

		@Bean
		public UndertowWebServerFactoryCustomizer undertowWebServerFactoryCustomizer(
				Environment environment, ServerProperties serverProperties) {
			return new UndertowWebServerFactoryCustomizer(environment, serverProperties);
		}

	}
```



EmbeddedServletContainerFactory(嵌入式Servlet容器工厂)

```java
public interface EmbeddedServletContainerFactory {
	//获取嵌入式的Servlet容器
	EmbeddedServletContainer getEmbeddedServletContainer(
			ServletContextInitializer... initializers);

}
```

![8](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\8.png)



3 以tomcatEmbeddedServletContainerFactory为例

```java
@Override
	public EmbeddedServletContainer getEmbeddedServletContainer(
			ServletContextInitializer... initializers) {
      	//创建一个tomcat容器
		Tomcat tomcat = new Tomcat();
      	//配置tomcat的基本环节
		File baseDir = (this.baseDirectory != null ? this.baseDirectory
				: createTempDir("tomcat"));
		tomcat.setBaseDir(baseDir.getAbsolutePath());
		Connector connector = new Connector(this.protocol);
		tomcat.getService().addConnector(connector);
		customizeConnector(connector);
		tomcat.setConnector(connector);
		tomcat.getHost().setAutoDeploy(false);
		configureEngine(tomcat.getEngine());
		for (Connector additionalConnector : this.additionalTomcatConnectors) {
			tomcat.getService().addConnector(additionalConnector);
		}
		prepareContext(tomcat.getHost(), initializers);
      	//将配置好的tomcat传入进去 返回一个EmbeddedServletContainer容器 并且启动tomcat服务器
		return getTomcatEmbeddedServletContainer(tomcat);
	}
```

我们对嵌入式容器的配置修改是怎么生效的?

```java
ServerProperties   EmbededServletContainerCustomerizer
```



EmbededServletContainerCustomerizer: 定制器帮我们修改Servlet容器的配置

怎么修改的原理:

​	容器中导入了EmbeddedServletContainerCustomerizer-BeanPostProcessor 

````java
/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.context.embedded;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.util.Assert;

/**
 * {@link BeanPostProcessor} that applies all {@link EmbeddedServletContainerCustomizer}s
 * from the bean factory to {@link ConfigurableEmbeddedServletContainer} beans.
 *
 * @author Dave Syer
 * @author Phillip Webb
 * @author Stephane Nicoll
 */
public class EmbeddedServletContainerCustomizerBeanPostProcessor
		implements BeanPostProcessor, BeanFactoryAware {

	private ListableBeanFactory beanFactory;

	private List<EmbeddedServletContainerCustomizer> customizers;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		Assert.isInstanceOf(ListableBeanFactory.class, beanFactory,
				"EmbeddedServletContainerCustomizerBeanPostProcessor can only be used "
						+ "with a ListableBeanFactory");
		this.beanFactory = (ListableBeanFactory) beanFactory;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean instanceof ConfigurableEmbeddedServletContainer) {
			postProcessBeforeInitialization((ConfigurableEmbeddedServletContainer) bean);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	private void postProcessBeforeInitialization(
			ConfigurableEmbeddedServletContainer bean) {
      //获取所有的定制器  调用每一个定制器的customize方法来给Servlet容器进行属性赋值
		for (EmbeddedServletContainerCustomizer customizer : getCustomizers()) {
			customizer.customize(bean);
		}
	}

	private Collection<EmbeddedServletContainerCustomizer> getCustomizers() {
      	//从容器中获取所有的这个类型的组件:EmbeddedServletContainerCustomizer
      	//定制Servlet容器 给容器中可以添加一个EmbededServletContainerCustomizer类型的组件
		if (this.customizers == null) {
			// Look up does not include the parent context
			this.customizers = new ArrayList<EmbeddedServletContainerCustomizer>(
					this.beanFactory
							.getBeansOfType(EmbeddedServletContainerCustomizer.class,
									false, false)
							.values());
			Collections.sort(this.customizers, AnnotationAwareOrderComparator.INSTANCE);
			this.customizers = Collections.unmodifiableList(this.customizers);
		}
		return this.customizers;
	}

}
ServerProperties也是定制器


````

Spring Boot根据导入依赖的情况  给容器中添加相应的嵌入式容器工厂：如TomcatEmbeddedServletContainerFactory

2 容器中某个组件要创建对象就会惊动后置处理器:EmbeddedServletContainerCustomizerBeanPostProcessor

只要是嵌入式的Servlet容器工厂 后置处理器就工作；

后置处理器从容器中获取所有的EmbeddedServletContainerCustomizer 调用定制器的的定制方法 ：如ServerProperties

```java
@ConfigurationProperties(prefix = "server", ignoreUnknownFields = true)
public class ServerProperties
		implements EmbeddedServletContainerCustomizer, EnvironmentAware, Ordered {
  @Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		if (getPort() != null) {
			container.setPort(getPort());
		}
		if (getAddress() != null) {
			container.setAddress(getAddress());
		}
		if (getContextPath() != null) {
			container.setContextPath(getContextPath());
		}
		if (getDisplayName() != null) {
			container.setDisplayName(getDisplayName());
		}
		if (getSession().getTimeout() != null) {
			container.setSessionTimeout(getSession().getTimeout());
		}
		container.setPersistSession(getSession().isPersistent());
		container.setSessionStoreDir(getSession().getStoreDir());
		if (getSsl() != null) {
			container.setSsl(getSsl());
		}
		if (getJspServlet() != null) {
			container.setJspServlet(getJspServlet());
		}
		if (getCompression() != null) {
			container.setCompression(getCompression());
		}
		container.setServerHeader(getServerHeader());
		if (container instanceof TomcatEmbeddedServletContainerFactory) {
			getTomcat().customizeTomcat(this,
					(TomcatEmbeddedServletContainerFactory) container);
		}
		if (container instanceof JettyEmbeddedServletContainerFactory) {
			getJetty().customizeJetty(this,
					(JettyEmbeddedServletContainerFactory) container);
		}

		if (container instanceof UndertowEmbeddedServletContainerFactory) {
			getUndertow().customizeUndertow(this,
					(UndertowEmbeddedServletContainerFactory) container);
		}
		container.addInitializers(new SessionConfiguringInitializer(this.session));
		container.addInitializers(new InitParameterConfiguringServletContextInitializer(
				getContextParameters()));
	}
```

### 嵌入式Servlet容器启动原理:

什么时候创建嵌入式的Servlet容器工厂？什么时候获取嵌入式的Servlet容器并启动tomcat

 1 SpringBoot 应用启动运行Run方法

2 Spring Boot 刷新IOC容器（创建IOC容器对象 并初始化容器 创建容器中的每一个组件）refreshContext(context);

如果是web环境，则创建**AnnotationConfigEmbeddedWebApplicationContext** 否则创建**AnnotationConfigApplicationContext**

3.refresh(context)刷新刚才创建好的IOC容器

4 web的IOC容器重写了onRefresh方法

5 webIOC容器会创建嵌入式的Servlet容器 createEmbeddedServletContainer();

6 获取嵌入式的Servlet容器工厂:EmbeddedServletContainerFactory containerFactory = getEmbeddedServletContainerFactory();

​	从IOC容器中获取EmbeddedServletContainerFactory组件

**TomcatEmbeddedServletContainerFactory**创建对象 后置处理器一看是这个对象 就获取所有的定制器 来定制Servlet容器的相关配置

7 使用容器工厂获取嵌入式的Servlete容器 this.embeddedServletContainer = containerFactory.getEmbeddedServletContainer(getSelfInitializer());

8嵌入式的Servlet容器创建对象并启动Servlet容器

先启动嵌入式Servlet容器 再将IOC容器中剩下没有创建的对象创建出来



IOC容器启动的时候创建嵌入式的Servlet容器



### 9 使用外置的Servlet容器

嵌入式Servlet容器：

​	优点：简单  便捷

​	缺点:默认不支持jsp  优化 定制比较复杂(使用定制器[ServerProperties  自定义**EmbeddedServletContainerCustomizer**] 自己编写嵌入式Servlet容器的创建工厂 EmbeddedServletContainerFactory）



外置的Servlert容器：外部安装tomcat--应用war包的方式打包

​	步骤:

​	必须创建一个war项目(创建好目录结构)

​	将嵌入式的Tomcat指定为provided

​	必须编写一个SpringBootServletInitializer的子类  并调用configure 

```java
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
      	//传入SpringBoot应用的主程序
		return application.sources(SpringBoot04WebJspApplication.class);
	}

}
```

​	4 启动服务器就可以使用

原理:

​	jar包：执行SpringBoot的主类的main方法 启动IOC容器  创建嵌入式的Servlet容器;

​	war包: 启动服务器 服务器启动SpringBoot 应用【SpringBootServletInitializer】，启动IOC容器

servlet3.0 

8.2.4 Shared libraries / runtimes pluggability 

规则:

​	1 服务器启动（web应用启动）会创建当前web应用里面的每一个jar包里面ServletContainerInitializer的实例

​	2 ServletContainerInitializer 的实现 放在jar包的META-INF/services 文件夹下有一个名为javax.servlet.ServletContainerInitializer 的文件 内容就是 ServletContainerInitializer的实现类的全类名

3 还可以使用@HandlerTypes   在应用启动的时候加载我们感兴趣的类;

流程:

​	1 启动tomcat 

​	2 spring-web-4.3.17.RELEASE.jar!/META-INF/services/javax.servlet.ServletContainerInitializer

 org.springframework.web.SpringServletContainerInitializer

```java
@HandlesTypes(WebApplicationInitializer.class)
public class SpringServletContainerInitializer implements ServletContainerInitializer {
  @Override
	public void onStartup(Set<Class<?>> webAppInitializerClasses, ServletContext servletContext)
			throws ServletException {
```

3 SpringServletContainerInitializer 将@HandlesTypes(WebApplicationInitializer.class)标注的所有这个类型的类都传入到onStartup方法的集合里面Set<Class<?> ;为这个（WebApplicationInitializer）类型的类创建实例

4.每一个WebApplicationInitializer都调用自己的onStartup方法;

![9](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\9.png)



5 相当于我们的SpringBootServletInitializer的类会被创建对象并执行onStartup方法

6 SpringBootServletInitializer实例执行onStartup的时候会调用  createRootApplicationContext( servletContext);	

```java
protected WebApplicationContext createRootApplicationContext(
			ServletContext servletContext) {
  		//创建SpringApplicationBuilder
		SpringApplicationBuilder builder = createSpringApplicationBuilder();
		StandardServletEnvironment environment = new StandardServletEnvironment();
		environment.initPropertySources(servletContext, null);
		builder.environment(environment);
		builder.main(getClass());
		ApplicationContext parent = getExistingRootWebApplicationContext(servletContext);
		if (parent != null) {
			this.logger.info("Root context already created (using as parent).");
			servletContext.setAttribute(
					WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, null);
			builder.initializers(new ParentContextApplicationContextInitializer(parent));
		}
		builder.initializers(
				new ServletContextApplicationContextInitializer(servletContext));
		builder.contextClass(AnnotationConfigEmbeddedWebApplicationContext.class);
  		//调用Configure方法 子类重写了这个方法 将SpringBoot的主程序类传入进来
		builder = configure(builder);
  		//使用builder创建一个Spring应用
		SpringApplication application = builder.build();
		if (application.getSources().isEmpty() && AnnotationUtils
				.findAnnotation(getClass(), Configuration.class) != null) {
			application.getSources().add(getClass());
		}
		Assert.state(!application.getSources().isEmpty(),
				"No SpringApplication sources have been defined. Either override the "
						+ "configure method or add an @Configuration annotation");
		// Ensure error pages are registered
		if (this.registerErrorPageFilter) {
			application.getSources().add(ErrorPageFilterConfiguration.class);
		}
  		//启动spring 应用
		return run(application);
	}
```

7 Spring的应用就启动了 并且创建IOC容器

```java
public ConfigurableApplicationContext run(String... args) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ConfigurableApplicationContext context = null;
		FailureAnalyzers analyzers = null;
		configureHeadlessProperty();
		SpringApplicationRunListeners listeners = getRunListeners(args);
		listeners.starting();
		try {
			ApplicationArguments applicationArguments = new DefaultApplicationArguments(
					args);
			ConfigurableEnvironment environment = prepareEnvironment(listeners,
					applicationArguments);
			Banner printedBanner = printBanner(environment);
			context = createApplicationContext();
			analyzers = new FailureAnalyzers(context);
			prepareContext(context, environment, listeners, applicationArguments,
					printedBanner);
			refreshContext(context);
			afterRefresh(context, applicationArguments);
			listeners.finished(context, null);
			stopWatch.stop();
			if (this.logStartupInfo) {
				new StartupInfoLogger(this.mainApplicationClass)
						.logStarted(getApplicationLog(), stopWatch);
			}
			return context;
		}
		catch (Throwable ex) {
			handleRunFailure(context, listeners, analyzers, ex);
			throw new IllegalStateException(ex);
		}
	}
```

![10](F:\BaiduNetdiskDownload\springboot尚硅谷\图片\10.png)



## Docker

###1 简介

​	**Docker** 是一个开源的应用容器引擎;

### 2核心概念

​	

​	docker主机(Host) 安装了Docker程序的机器(Docker直接安装在操作系统上的)

​	docker客户端(Client):连接docker主机进行操作

​	docker仓库(Registry):用来保存各种打包扣的软件镜像

​	docker镜像:软件打包好的镜像 放在docker仓库中

​	docker容器(container)：镜像启动后的实例称为一个容器;容器是独立运行的一个或者一组应用 ；

使用Docker的步骤

​	安装Docker

​	去Docker仓库拿到这个软件对应的镜像

​	使用Docker运行这个镜像  这个镜像就会生成一个 Docker容器

​	对Docker容器的启动停止就是对软件的启动停止



在Linux是上安装Docker

````shell
1 检查内核版本
uname -r
安装docker 
	yum -y install docker 
启动docker 
	systemctl start docker
开机启动dockedr
	systemctl enable docker
Created symlink from /etc/systemd/system/multi-user.target.wants/docker.service to /usr/lib/systemd/system/docker.service.
停止docker
	systemctl stop docker
	
````



### 常用操作

####1 镜像操作:

0 v: docker search mysql     

​	拉取: docker pull 镜像名:tag

​			:tag 是可选的 tag表标签  多为软件的版本 默认是latest

​	查看镜像列表：docker images 

​	删除：docker rmi  image-id

https://hub.docker.com/explore/	



#### 2容器操作

​	软件镜像--运行镜像--产生一个容器(正在运行的软件)

```shell
1.搜索镜像
	docker search tomcat 
2.拉取镜像
	docker pull tomcat
3.根据镜像启动容器
	docker run --name mytomcat -d tomcat:latest
		--name: 自定义容器名
		-d :后台运行
		image-name:指定镜像模板
	
4 查看运行中的容器
	docker ps 
5 停止运行中的容器
 	docker stop id
6 查看所有的容器
	docker ps -a 
7 启动容器
	docker start id
8 删除一个容器 
	docker rm 容器id
9 启动一个做了端口映射的tomcat 
	docker run --name mytomcat  -d -p 8080:8080 tomcat
	-d 后台运行
	-p 将主机端口 映射到docker容器的端口
	
10 查看日志
	docker logs 容器id
```



## 六 SpringBoot与数据访问

### 1 JDBC 

```xml
<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
```

```yaml
spring:
  datasource:
    url: jdbc:mysql://118.25.49.75:3306/jdbc
    password: 123456
    username: root
    driver-class-name: com.mysql.jdbc.Driver
```

效果：

​	默认是使用class org.apache.tomcat.jdbc.pool.DataSource作为数据源

​	数据源的相关配置都在DataSourceProperties里面

自动配置原理:

​	1.参考DataSourceConfiguration 根据配置创建数据源  默认使用tomcat连接池 可以使用spring.datasource.type指定自定义的数据源类型

​	2 Spring Boot默认可以支持

```java
org.apache.tomcat.jdbc.pool.DataSource
com.zaxxer.hikari.HikariDataSource
org.apache.commons.dbcp.BasicDataSource
org.apache.commons.dbcp2.BasicDataSource
```

3 自定义数据源类型

```java
/**
	 * Generic DataSource configuration.
	 */
	@ConditionalOnMissingBean(DataSource.class)
	@ConditionalOnProperty(name = "spring.datasource.type")
	static class Generic {
		@Bean
		public DataSource dataSource(DataSourceProperties properties) {
          	//使用DataDSourceBuilder创建数据源 利用反射创建响应type的数据源 并绑定相关属性
			return properties.initializeDataSourceBuilder().build();
		}
	}
```

4 DataSourceAutoConfiguration中 DataSourceInitializer

是一个ApplicationListener

作用:

​	runSchemaScripts();运行建表语句

​	runDataScripts();运行插入数据的语句

默认只需要将文件命名为 

```java
schema-*.sql

data-*.sql

默认规则:schema-all.sql   data-all.sql

可以使用
	schema:
      - classpath:department.sql
      - classpath:employee.sql
   来指定位置
```



5 操作数据库

​	自动配置了jdbcTemplate操作数据库





### 2 整合Druid

```java
@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    DataSource dataSource() {
        return new DruidDataSource();
    }

    //配置Druid的监控

    /**配置一个管理后台的Servlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet());
        servletRegistrationBean.setUrlMappings(Arrays.asList("/druid/*"));
        Map<String,String> initParam=new HashMap<>(0);
        initParam.put(StatViewServlet.PARAM_NAME_USERNAME,"admin");
        initParam.put(StatViewServlet.PARAM_NAME_PASSWORD,"123456");
        //默认运行所有
        initParam.put(StatViewServlet.PARAM_NAME_ALLOW,"localhost");
        initParam.put(StatViewServlet.PARAM_NAME_DENY,"192.168.15.21");
        servletRegistrationBean.setInitParameters(initParam);
        return servletRegistrationBean;
    }
    /**配置一个监控的filter
     *
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        Map<String,String> initParam=new HashMap<>(0);
        initParam.put(WebStatFilter.PARAM_NAME_EXCLUSIONS,"*.js,*.css,/druid/*");
        filterRegistrationBean.setInitParameters(initParam);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/"));
        return filterRegistrationBean;

    }


}
```





​	









​	



​	

​	

