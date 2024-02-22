# SpringBoot 学习笔记

# 1 Springboot入门

## Part 1  JAVA WEB发展史

**1） web的产生**  

静态资源的发布媒介      html   http  url  

![1570591797181](images/1570591797181.png)

动态资源的展示      http  参数的不同   返回不同的信息

**2) CGI的诞生**

协议/标准  把参数写进环境变量里  启动CGI的程序获取参数后返回动态的结果(工作模式)
一笔请求 => 启动一个CGI程序   耗费资源

巨头公司“腾讯”部分业务  c++服务端  php前端页面

**3）java解决方案**

i   applet 浏览器的插件  把服务端传过来的java代码在插件中解释执行 (客户端执行代码)

ii  servlet = server + applet 服务端解决方案

​                 html标签 + 动态代码 + 放到大 servlet类里  去输出

```java
  out.println("<html><title>");
  out.println("hello"+name);
  out.println("</html></title>");
```

iii  jsp = java server pages

​     解决样板代码的问题

```html
 <html><title>hello <%name%> </title></html>
```

iv  mvc分层思想

​     model   javabean 数据层
​     view  jsp 展示层
​     controller 业务控制类  控制层   接收请求  查找返回页面  生成页面的数据   结合一起   返回动态页面

![](images/1570592664741.png)

  v  ajax 渲染部分页面   js整个技术的一个基石

**4）Spring**

   without EJB => spring框架  ioc aop

​    **ioc**  控制反转  di 依赖注入

​            本质：构建对象的技术
​                       单例模式   SprIng 用工厂来创建对象的方式
​                        **容器** 对象之间的依赖关系   A  属性B

​            程序获取对象时   容器会自动注入对象给程序(从容器的角度) =>  依赖注入
​            本应该程序自己来控制的对象，交给容器帮我们控制 （从程序的角度） =>  控制反转

   **aop** 面向切面编程

​            系统中有多个模块   模块A   日志打印  异常获取   事务管理  等等   模块B  C
​            各个模块的交叉关注点    非核心业务逻辑   通用的处理方案来解决

   配置繁杂

**5）SpringBoot**

​       引导你更便捷的使用spring

**6)  未来**

​       总结  技术诞生的规律 ： 新技术的诞生用来解决现存技术的痛点
​       异步化   spring5  响应式编程



## Part 2  搭建hello world

创建maven项目  pom.xml（管理依赖）

总共分三步：

1）依赖放进去

```xml
    <!--超级父pom  是springboot父依赖   声明版本号-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.8.RELEASE</version>
    </parent>

    <dependencies>
        <!--web项目需要使用的-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
     </dependencies>
```

2)  把程序入口开开

```java
/**
 * @EnableConfigurationProperties({FoodConfig.class})
 * 告诉主程序入口类  要自动引入配置文件
 * 配置文件对应的类作为它的参数
 */
@SpringBootApplication
@EnableConfigurationProperties({FoodConfig.class})
public class DemoApplication {

    public static void main(String[] args) {
        //使用SpringApplication类的静态方法  启动springboot程序
        //方法的参数  程序的入口类  main函数的参数
        SpringApplication.run(DemoApplication.class,args);
    }
}
```

3）把业务逻辑放进去

```java
/**
 * @Controller
 * 控制类  业务逻辑  请求分发  组装响应
 */
@Controller
public class HelloController {

    /**
     * @RequestMapping
     * 指定方法和请求之间的映射关系
     * @return
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello Duing";
    }
}
```

作业  https://spring.io/guides/gs/rest-service/



# 2 Springboot配置

## Part 1 常用配置

## Part 2  lombok

**java编译原理**  —— javac命令 （编译器）

编译：将一种语言规范 转化成 另一种语言规范的过程

javac:  将人能理解的java语言  转化成  jvm能理解的二进制字节



javac编译步骤：
词法分析 ->  语法分析 ->  语义分析 ->  字节码生成

词法分析：
检查关键字    分析结果->  规范化的token流  

语法分析：
把检查出来的关键字(一个个代码块)  查看是否符合语言的规范
分析结果 ->  抽象语法树 (结构化的语法表达形式)

语义分析：
把难懂的复杂的语法  转化成简单  
分析结果 ->  注解的抽象语法树

字节码生成
遍历语法树 ->  符合jvm规范的二进制代码



## Part 3 YAML

Linux  =  linux is not unix



# 3 Springboot 与 web开发

## par1 模板引擎

1) jsp

​    html + java代码  =》 Servlet类  =》 放到容器中执行  
​    缺点  繁杂而不利于维护 

​    本质上是“模板”   通过规则和标签抽象出来的

2）模板引擎  
     freemarker  velocity  thymeleaf  beetl(国人开创   据说性能很高)

![1570688131563](images/1570688131563.png)

3）基于api的前后端分离
     web服务器：解析静态资源 如 nginx apache  （前台接待 — 外网访问）
     应用服务器：解析动态+静态资源    如tomcat  jetty  (核心价值提供者 — 内网访问)

​     nginx（前端代码） --> ajax 请求api -->    tomcat（服务端代码）  数据格式json

​         

## par2  FreeMarker

## par3  Thymeleaf

中餐厅Demo  
嘉宾：黄晓明   秦海璐  林述巍   王俊凯(走)  杨紫  仝卓  王鹤棣 

1）嘉宾展示列表；
2）增加、删除嘉宾   更改角色(店长、财务、大堂经理、主厨、帮厨。。。)

交互设计：
1）首先展示列表页，页面中有多个按钮，每一行上面有更改和删除按钮，列表下方有增加按钮。
2）点击增加时，出现新的页面，页面需要填入名字和角色两个基本信息，填好提交返回列表页。
3）点击删除时，列表页更新。
4）点击更改时，出现新的编辑页面，名字不可改，角色可修改，修改提交后返回列表页。

系统设计：
1）实体类Guest   name   role
2）操作Model的方式GuestDao  初始化的数据、数据的增删改查处理
3）业务控制逻辑GuestController  接收请求，逻辑处理，返回相应的页面
4）逻辑处理GuestService去调用相应的GuestDao.

接口设计
1） /guest/list  查询列表   无查询参数   返回结果：List\<Guest>
2）/guest/toAdd 点击增加按钮  返回add.html页面  需填写名字和角色
      /guest/add 增加操作   传参为Guest  返回结果：跳转到list页面
3）/guest/toUpdate 点击修改按钮 传参Guest name  返回update.html页面   名字固定  角色可改
      /guest/update  修改操作  传参Guest  返回结果：跳转到list页面
4）/guest/delete 删除操作   传参Guest name  返回结果：跳转到list页面

## part 4  RESTful

一句话理解：
看url就知道要什么，看http method就知道要做什么(增删改查)，看http code就知道哪里有问题

|        | 传统API                                        | RESTful API                     |
| ------ | ---------------------------------------------- | ------------------------------- |
| 查询   | /guest/list                           <GET>    | /guest                  <GET>   |
| to添加 | /guest/toAdd                      <GET>        | /guest/toAdd      <GET>         |
| 添加   | /guest/add?guest={guest}                <POST> | /guest                  <POST>  |
| to编辑 | /guest/toUpdate?name={name}      <GET>         | /guest/toUpdate/{name}    <GET> |
| 编辑   | /guest/update?guest={guest}           <POST>   | /guest                   <PUT>  |
| 删除   | /guest/delete?name={name}            <GET>     | /guest/{name}     <DELETE>      |

 

编辑功能的更改有几点注意事项：

1）form表单原生只支持get和post方法，可以通过隐藏属性值，实现put以及delete的提交方式。

```html
 <input type="hidden" name="_method" value="put">
```

2)  /guest/toUpdate/{name}路径的属性接收和以往不同。
 通过注解 @PathVariable("name")映射到方法的属性中，直译的意思就是  从路径中获取name的值

```java
    @GetMapping("/toUpdate/{name}")
    public String toUpdate(Model model,@PathVariable("name") String name){
        Guest guest = guestService.get(name);
        model.addAttribute("guest",guest);
        return "update";
    }
```



删除功能的更改有几点注意事项：

1） 通过webjars引入jquery

pom.xml

```xml
        <dependency>
            <groupId>org.webjars</groupId>
            <artifactId>jquery</artifactId>
            <version>3.4.1</version>
        </dependency>
```

list.html

```html
<script type="text/javascript" th:src="@{/webjars/jquery/3.4.1/jquery.js}"></script>
```

2)   如何通过delete的方法提交按钮？  借助form表单

```html
<!--声明删除按钮-->
<button th:attr="del_url=@{/guest/}+${guest.name}" name="del_button">删除</button>

<!--声明删除按钮借助的表单-->
<form method="post" id="del_form">
    <input type="hidden" name="_method" value="delete">
</form>

<!--增加按钮的点击监听，给表单的action赋值，然后提交-->
<script>
    $(function () {
        $("button[name='del_button']").click(function () {
            $("#del_form").prop("action",$(this).attr("del_url")).submit();
        });
    });
</script>
```





# 4 Springboot与数据访问

## Part 1  ORM

## Part 2  JPA

## Part 3 Mybatis





# 5 Springboot启动配置原理

## Part 1 Hello World 探秘

```
@SpringBootApplication
//由三个注解组成的
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
做了三件事儿  声明是配置类、自动注入配置、自动扫描包
```

```
1 @SpringBootConfiguration 是对配置的声明注解  封装了注解@Configuration
2 @EnableAutoConfiguration 自动注入配置的注解
  由@AutoConfigurationPackage和@Import(AutoConfigurationImportSelector.class)组成
  功能是基于已添加的依赖项，猜测你要如何使用Spring的相关配置。
3 @ComponentScan 扫描组件 默认扫描入口类的同级类和同级目录下的所有类。
```



执行过程逻辑

```java
	//创建了SpringApplication实例   执行run方法
	public static ConfigurableApplicationContext run(Class<?>[] primarySources, String[] args) {
		return new SpringApplication(primarySources).run(args);
	}
	
	//创建实例的方法
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
		//加载主配置  确定web应用类型等等
		this.resourceLoader = resourceLoader;
		Assert.notNull(primarySources, "PrimarySources must not be null");
		this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources));
		this.webApplicationType = WebApplicationType.deduceFromClasspath();
		//从SpringFactoriesLoader类中的loadSpringFactories方法
		//加载文件META-INF/spring.factories
		setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
		setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
		//查找主程序的main函数
		this.mainApplicationClass = deduceMainApplicationClass();
	}
	
	
	//run方法的执行过程  
	//加载一系列的配置  准备一系列的环境 打印一系列的日志  开始创建容器启动监听
	public ConfigurableApplicationContext run(String... args) {
        //计时器
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
        //创建应用上下文/容器
		ConfigurableApplicationContext context = null;
		Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
 		//加载配置
		configureHeadlessProperty();
        //获取监听器
		SpringApplicationRunListeners listeners = getRunListeners(args);
		listeners.starting();
		try {
            //创建默认参数
			ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
            //准备环境
			ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
			configureIgnoreBeanInfo(environment);
            //打印banner 
			Banner printedBanner = printBanner(environment);
            //创建容器
			context = createApplicationContext();
			exceptionReporters = getSpringFactoriesInstances(SpringBootExceptionReporter.class,
					new Class[] { ConfigurableApplicationContext.class }, context);
			prepareContext(context, environment, listeners, applicationArguments, printedBanner);
			refreshContext(context);
			afterRefresh(context, applicationArguments);
			stopWatch.stop();
            //计时结束  启动完成
			if (this.logStartupInfo) {
				new StartupInfoLogger(this.mainApplicationClass).logStarted(getApplicationLog(), stopWatch);
			}
			listeners.started(context);
			callRunners(context, applicationArguments);
		}
        ...
        try {
            //启动监听器
			listeners.running(context);
		}
        ...
        //返回容器
		return context;
    }
```

## Part 2 自动配置原理

```java
@EnableAutoConfiguration
//注入自动配置

//有两大注解组成
@AutoConfigurationPackage  查找并注册自动配置包
@Import(AutoConfigurationImportSelector.class)  引入资源，自动配置的包选择器

相关的逻辑都在spring-boot-antoconfigure.jar里
```

a）@AutoConfigurationPackage
@Import(AutoConfigurationPackages.Registrar.class)
//引入资源  自动配置的包注册器

		@Override
		public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
			register(registry, new PackageImport(metadata).getPackageName());
		}
//注册bean定义器 包名是主程序入口的所在目录(默认只扫描入口类的当前目录和子目录)



b) @Import(AutoConfigurationImportSelector.class)
   -1- AutoConfigurationImportSelector类有一个selectImports方法
   -2- selectImports方法执行的逻辑:  加载元数据和具体的配置条目

```java
AutoConfigurationMetadata autoConfigurationMetadata = 
    AutoConfigurationMetadataLoader
				.loadMetadata(this.beanClassLoader);
		AutoConfigurationEntry autoConfigurationEntry = getAutoConfigurationEntry(autoConfigurationMetadata,
				annotationMetadata);
```

   -3- getAutoConfigurationEntry调用了getCandidateConfigurations方法，去加载条件配置
   -4- getCandidateConfigurations方法的逻辑是通过spring工厂加载器 获取名称

```
 List<String> configurations = SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(),
				getBeanClassLoader());
```

   -5- loadFactoryNames方法的逻辑
     加载文件  META-INF/spring.factories 里面的内容
     XXXAutoConfiguration命名的一系列配置类   如ThymeleafAutoConfiguration

  

c） 以ThymeleafAutoConfiguration为例   查看选择注解的机制

   ```java
四大注解
@Configuration   
//声明这是配置类 xml 
@EnableConfigurationProperties(ThymeleafProperties.class)  
//加载ThymeleafProperties这个配置文件类
@ConditionalOnClass({ TemplateMode.class, SpringTemplateEngine.class })
//控制条件注解  当存在TemplateMode和SpringTemplateEngine的时候   才会执行类的逻辑
@AutoConfigureAfter({ WebMvcAutoConfiguration.class, WebFluxAutoConfiguration.class })
//控制执行顺序  在WebMvcAutoConfiguration和WebFluxAutoConfiguration执行之后再执行
   ```



**ThymeleafAutoConfiguration注解的执行逻辑：**

1 先执行AutoConfigureAfter里面的属性类
2 判断是否存在ConditionalOnClass里面的class文件  (取决于pom是否引入依赖)

```xml
    <!--首先引入starter （封装的启动器）-->
	<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
  
     <!-- 启动器引入具体的依赖 -->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
      <version>2.1.8.RELEASE</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.thymeleaf</groupId>
      <artifactId>thymeleaf-spring5</artifactId>
      <version>3.0.11.RELEASE</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.thymeleaf.extras</groupId>
      <artifactId>thymeleaf-extras-java8time</artifactId>
      <version>3.0.4.RELEASE</version>
      <scope>compile</scope>
    </dependency>

   <!--具体的依赖就存在需要的class -->
   <!--thymeleaf-spring5 jar包中存在SpringTemplateEngine.class -->

```

3 判断需要执行后 ，开始加载ThymeleafProperties配置文件类

```
@ConfigurationProperties(prefix = "spring.thymeleaf")
//这是一个配置文件类  并且寻找前缀为spring.thymeleaf开头的配置

spring.thymeleaf.prefix 默认值classpath:/templates/
spring.thymeleaf.suffix 默认值html
spring.thymeleaf.mode   默认值HTML
spring.thymeleaf.encoding 默认值UTF-8
```

4 执行类里的逻辑

```java
        //注入bean  是spring资源模板解析器
        @Bean
		public SpringResourceTemplateResolver defaultTemplateResolver() {
            //是通过spring-thymeleaf这个粘合器，调用thymeleaf里面的核心逻辑
			SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
            //赋值配置文件中的重要属性   
			resolver.setApplicationContext(this.applicationContext);
			resolver.setPrefix(this.properties.getPrefix());
			resolver.setSuffix(this.properties.getSuffix());
			resolver.setTemplateMode(this.properties.getMode());
			if (this.properties.getEncoding() != null) {
				resolver.setCharacterEncoding(this.properties.getEncoding().name());
			}
			resolver.setCacheable(this.properties.isCache());
			Integer order = this.properties.getTemplateResolverOrder();
			if (order != null) {
				resolver.setOrder(order);
			}
			resolver.setCheckExistence(this.properties.isCheckTemplate());
			return resolver;
		}
```

## Part 3 事件监听机制

1）run方法的执行过程  

```java
	//加载一系列的配置  准备一系列的环境 打印一系列的日志  开始创建容器启动监听
	public ConfigurableApplicationContext run(String... args) {
        //计时器
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
        //创建应用上下文/容器
		ConfigurableApplicationContext context = null;
		Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
 		//加载配置
		configureHeadlessProperty();
        //获取监听器  从META-INF/spring.factories文件中获取的
		SpringApplicationRunListeners listeners = getRunListeners(args);
		listeners.starting();
		try {
            //传递命令行参数，创建应用参数类
			ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
            //准备环境
			ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
			configureIgnoreBeanInfo(environment);
            //打印banner 
			Banner printedBanner = printBanner(environment);
            //创建容器
			context = createApplicationContext();
			exceptionReporters = getSpringFactoriesInstances(SpringBootExceptionReporter.class,
					new Class[] { ConfigurableApplicationContext.class }, context);
			prepareContext(context, environment, listeners, applicationArguments, printedBanner);
			refreshContext(context);
			afterRefresh(context, applicationArguments);
			stopWatch.stop();
            //计时结束  启动完成
			if (this.logStartupInfo) {
				new StartupInfoLogger(this.mainApplicationClass).logStarted(getApplicationLog(), stopWatch);
			}
            //广播started方法对应的事件
			listeners.started(context);
            //回调runner的方法  分别是ApplicationRunner  CommandLineRunner
			callRunners(context, applicationArguments);
		}
        ...
        try {
            //启动监听器
			listeners.running(context);
		}
        ...
        //返回容器
		return context;
    }
```



2）准备容器的工作

```java
private void prepareContext(ConfigurableApplicationContext context, ConfigurableEnvironment environment,
			SpringApplicationRunListeners listeners, ApplicationArguments applicationArguments, Banner printedBanner) {
        //设置环境参数
		context.setEnvironment(environment);
		postProcessApplicationContext(context);
        //应用初始化器 
		applyInitializers(context);
    	//广播容器准备方法对应的事件
		listeners.contextPrepared(context);
		if (this.logStartupInfo) {
			logStartupInfo(context.getParent() == null);
			logStartupProfileInfo(context);
		}
		//增加特殊的单例的bean
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		beanFactory.registerSingleton("springApplicationArguments", applicationArguments);
		if (printedBanner != null) {
			beanFactory.registerSingleton("springBootBanner", printedBanner);
		}
		if (beanFactory instanceof DefaultListableBeanFactory) {
			((DefaultListableBeanFactory) beanFactory)
					.setAllowBeanDefinitionOverriding(this.allowBeanDefinitionOverriding);
		}
		//加载sources
		Set<Object> sources = getAllSources();
		Assert.notEmpty(sources, "Sources must not be empty");
    	//容器真正的加载逻辑   比如扫描注解 创建bean  关联bean的关系等等
		load(context, sources.toArray(new Object[0]));
    	//广播  容器加载对应的事件
		listeners.contextLoaded(context);
	}

```



3）以LoggingApplicationListener为例

```java
public class LoggingApplicationListener implements GenericApplicationListener {

//public interface GenericApplicationListener extends //ApplicationListener<ApplicationEvent>, Ordered {

//这是一个 springboot定义的监听器
    	
    //这是它订阅的事件类型  这是容器启动过程中会广播的不同事件
    private static final Class<?>[] EVENT_TYPES = { ApplicationStartingEvent.class,
			ApplicationEnvironmentPreparedEvent.class, ApplicationPreparedEvent.class, ContextClosedEvent.class,
			ApplicationFailedEvent.class };
    
    //接收事件时执行的方法
    public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ApplicationStartingEvent) {
			onApplicationStartingEvent((ApplicationStartingEvent) event);
		}
		else if (event instanceof ApplicationEnvironmentPreparedEvent) {
			onApplicationEnvironmentPreparedEvent((ApplicationEnvironmentPreparedEvent) event);
		}
		else if (event instanceof ApplicationPreparedEvent) {
			onApplicationPreparedEvent((ApplicationPreparedEvent) event);
		}
		else if (event instanceof ContextClosedEvent
				&& ((ContextClosedEvent) event).getApplicationContext().getParent() == null) {
			onContextClosedEvent();
		}
		else if (event instanceof ApplicationFailedEvent) {
			onApplicationFailedEvent();
		}
	}
```



## Part 4 嵌入式web容器

支持三种容器：Tomcat  Jetty  Undertow

springboot2.x的重大更新之一，就是支持响应式web server（1.x只支持servlet  web容器）

**一、 更改容器配置**

方式1：

在application.properties中设置，形如：

```properties
server.port=8090
#localhost:8090/duing
server.servlet.context-path=/duing

server.tomcat.max-connections=5000
```

方式2：

创建自定义器，实现WebServerFactoryCustomizer的customize方法，可以设置端口号等 (优先级高于配置文件)

```java
@Configuration
public class WebConfig {

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> customizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {

            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8899);
            }
        };
    }
}
```

**二、更改使用容器**

从starter-web中移除starter-tomcat，引入新的容器启动器

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jetty</artifactId>
        </dependency>

```

**三、使用响应式web容器**

去掉对starter-web的引用，增加starter-webflux（两者同时存在时，还会servlet容器）

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-undertow</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
```

编写一个响应式编程的demo

```java
 @Bean
    public RouterFunction<ServerResponse> hello(){
        return route(GET("/hello"),
                serverRequest -> ok().body(
                        Mono.just("Hello world"),String.class));
    }
```

**四、查看web应用启动类**

方式一

通过WebServerApplicationContext容器获取类名，在容器启动后回调使用   （当前应用为web应用时可用）

```java
@Configuration
public class WebConfig {

    @Bean
    public ApplicationRunner runner(WebServerApplicationContext context){
        return args -> {
            System.out.println("WebConfig---当前web容器的实现类是："
                    + context.getWebServer().getClass().getName());
        };

    }
}
```

 方式二

通过监听WebServerInitializedEvent初始化事件，在容器启动前触发

```java
@Component
public class ListenerConfig {

    @EventListener(WebServerInitializedEvent.class)
    public void onWebServerReady(WebServerInitializedEvent event){
        System.out.println("ListenerConfig---当前web容器的实现类是："
                + event.getWebServer().getClass().getName());
    }
}
```

# 6 Springboot开发部署实战

## part1 过滤器&监听器

## part2 错误&异常处理机制

**一**、**错误和异常**

Error   Exception
Error经常是程序无法处理的 (用户引入的错误   系统的错误)
Exception 程序员引发的，能够在程序中处理， try catch  / throws

优秀的处理机制

问题：怎么处理？ 如何做到优秀？

**二、处理方式**

统共分为三种：数据验证错误、错误页指派、全局异常处理

**1）数据验证错误**

**a) Hibernate Validator自身的功能**

@NotNull   @NotEmpty  @NotBlank

@NotNull 不为空    广泛用于基础类型的判断   int  double  boolean  

@NotBlank不为空串   广泛用于String类型的判断   string    

@NotEmpty内容不为空   广泛用于集合等的判断    Map map = new HashMap();  map.put("","");

```java
@Data @AllArgsConstructor
public class Guest {

    @NotBlank(message = "嘉宾名字不能为空")
    private String name;
    @NotBlank
    private String role;
}
```



```java
public class GuestValidTest {


    public static void main(String[] args) {
        //通过Validation创建一个validator实例
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        //创建一个不符合要求的bean
        Guest guest = new Guest("","");
        //开始验证  接收验证结果
        Set<ConstraintViolation<Guest>>  violationSet =  validator.validate(guest);
        for(ConstraintViolation violation : violationSet){
            System.out.println(violation.getPropertyPath() + "," + violation.getMessage());
        }
    }
}
```

执行结果：
![1571714297974](images/1571714297974.png)

这是校验框架的模式之一，

“普通模式” (遍历所有属性，不符合校验规则都返回)

“快速失败模式” (遍历属性时，有一个不符合校验规则，即返回)

```java
public class GuestValidTest {


    public static void main(String[] args) {
        //通过Validation创建一个validator实例  （普通模式）
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        //创建一个快速失败模式的实例
        Validator validatorFastFail = Validation.byDefaultProvider().configure()
                .addProperty("hibernate.validator.fail_fast","true").
                        buildValidatorFactory().getValidator();

        //创建一个不符合要求的bean
        Guest guest = new Guest("","");
        //开始验证  接收验证结果
        Set<ConstraintViolation<Guest>>  violationSet =  validator.validate(guest);
        for(ConstraintViolation violation : violationSet){
            System.out.println(violation.getPropertyPath() + "," + violation.getMessage());
        }

        System.out.println("==============================");

        Set<ConstraintViolation<Guest>>  violationFastFailSet =  validatorFastFail.validate(guest);
        for(ConstraintViolation violation : violationFastFailSet){
            System.out.println(violation.getPropertyPath() + "," + violation.getMessage());
        }
    }
}
```



**b）在springboot的集成使用**

使用默认的处理方式

```java
@RestController
public class GuestController {

    /**
     * @Valid  直接放在bean前方
     * 用来校验它是否符合注解规则
     * 校验不通过时  直接返回400 和  失败原因
     * 处理方式是，遍历全部属性，失败结果全部返回
     * @param guest
     * @return
     */
    @PostMapping("/guest")
    public String add(@Valid Guest guest){
        return "Success";
    }
}
```

自定义效果通过BindingResult来处理

```java
    @PostMapping("/guest")
    public String add(@Valid Guest guest, BindingResult result){
        if(result.getErrorCount()>0){
            List<FieldError> fieldErrorList = result.getFieldErrors();
            StringBuffer stringBuffer = new StringBuffer();
            for(FieldError fieldError : fieldErrorList){
                stringBuffer.append(fieldError.getField());
                stringBuffer.append("\t");
                stringBuffer.append(fieldError.getDefaultMessage());
                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        }

        return "Success";
    }
```

springboot支持通过配置文件设置校验信息，文件名为ValidationMessages.properties，位置在resources下，在bean中的注解属性中通过{key}来获取对应的value

```java
    @NotBlank(message = "{guest.name.notblank}")
    private String name;
```

```properties
guest.name.notblank=嘉宾名字不能为空 from properties
```



**2）错误页指派**

a) 错误页？ 形如：https://github.com/400.html

![1571730359420](images/1571730359420.png)

不设定错误页时，springboot会提供一个默认空白页

![1571730489624](images/1571730489624.png)

使用postman请求时，返回json字符串，同样包含以上数据

![1571730548776](images/1571730548776.png)

返回不同的结果，区别在于http请求头中的Accept属性的值

![1571730690322](images/1571730690322.png)

![1571730668728](images/1571730668728.png)

 b）定义错误页

查看文档 [第四章] - [第29.1小节] - [**Custom Error Pages**]

方式1：

​    创建/public/error/404.html页面，注意只支持静态页面

方式2：

​    创建/templates/error/404.html页面，支持动态页面，并且优先级比/public/error目录高。
​    注意： 可以通过4xx.html页面，代表所有以4开头的错误码要查找的页面(400,401,402...)，5xx.html同理。
​                 当404.html和4xx.html同时存在，优先显示精确的错误码页面，即为404.html

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>4xx</title>
</head>
<body>

这里是/templates/error/4xx.html
<br>
现在找不到页面了

</body>
</html>
```

方式3：（优先级高于方式二）

​     创建自定义的错误视图解析器，实现ErrorViewResolver接口 

```java
/**
 * 重要：需要是一个bean  能够被spring容器处理  才会生效
 */
@Component
public class MyErrorViewResolver implements ErrorViewResolver {

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request,
                                         HttpStatus status,
                                         Map<String, Object> model) {
        if(status.equals(HttpStatus.NOT_FOUND)){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/resolver404");
            return modelAndView;
        }

        return null;
    }
}
```

方式4：（优先级高于方式三）

​     使用WebServerFactoryCustomizer来注册bean，通过更改错误码的处理路径，来指定不同的页面。

```java
@Configuration
public class MyCustomizer {

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> customizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                ErrorPage errorPage = new ErrorPage(HttpStatus.NOT_FOUND,"/error404");
                factory.addErrorPages(errorPage);
            }
        };
    }
}
```

```java
@Controller
public class MyErrorController {

    @RequestMapping("/error404")
    public String error404(){
        return "error404";
    }
}
```

**3) 全局异常处理**

通过@ControllerAdvise注解监听所有在controller中出现的异常，执行@ExceptionHandler注解的方法，跳转错误页。

```java
/*
* 切面的处理方式  使用@ControllerAdvice注解(spring3.x提供的)
*/
@ControllerAdvice
public class MyExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ModelAndView handler(Exception e){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/error");
        mv.addObject("message",e.getMessage());
        return mv;
    }

}
```

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>ERROR</title>
</head>
<body>

这里是/templates/error.html
<br>
现在出现异常了

<p th:text="${message}">message</p>

</body>
</html>
```



测试用例

```java
    @RequestMapping("/testError")
    public String error() throws Exception{
        throw new Exception("测试异常");
    }
```



## part3 日志

1996  log4j = log for java  apache 成为了日志标准

2002  jul  =  java util logging   sun

jcl = jakarta commons logging  日志接口

2006  logback = log4j 升级版    slf4j = simple logging facade for java  日志门面接口

2012  log4j 2  apache 

​         Commons logging   ||   slf4j（主流 +logback）



``` java
2019-10-26 20:08:10.873  INFO 19544 --- [           main] c.d.SpringBootDuing06LoggingApplication  : Started SpringBootDuing06LoggingApplication in 5.269 seconds (JVM running for 8.651)
```

通用日志格式：

时间日期+日志级别+进程ID+分隔符(正式开始)+线程名+Logger名(类名)+日志内容



# 7 Groovy与Springboot cli

# 8 Springboot与监控

监控的本质： 收集日志后，通过分析，产生的结果



# 9 Springboot与缓存

# 10 Springboot与微服务





















































































































