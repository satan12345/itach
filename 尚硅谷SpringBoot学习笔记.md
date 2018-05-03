



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







































