# springboot2.0
springboot2.0、Mybatis-Plus、encache......

Typora

1、热部署：
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<optional>true</optional>
			<!--<scope>runtime</scope>-->
		</dependency>
		
		idea默认是没有自动编译的,我们这里需要添加修改配置.打开设置
		（1）File-Settings-Compiler-Build Project automatically
		（2）ctrl + shift + alt + /,选择Registry,勾上 Compiler autoMake allow when app running

2、Whitelabel Error Page

	This application has no explicit mapping for /error, so you are seeing this as a fallback.
	Thu Aug 09 11:16:51 CST 2018
	There was an unexpected error (type=Not Found, status=404).
	No message available

 index.html  放在static资源文件夹下

3、html  ajax请求

		var formData = new FormData();
	    formData.append('productName', productName);
	    formData.append('amount',amount );
	    formData.append('num', num);
	    $.ajax({
	        url: 'http://localhost:9001/addBill',
	        type: 'POST',
	        dataType: 'json',
	        cache: false,
	        data: formData,
	        processData: false,
	        contentType: false,
	        success: function(r){
	            if(r.code === 0){
	                alert('保存成功', function(){
	                });
	            }else{
	                alert(r.msg);
	            }
	        },
	        error: function(err) {
	
	            alert("网络错误");
	        }
	    });

4、mybatis

Caused by: java.lang.ClassNotFoundException: org.springframework.boot.autoconfigure.jdbc.metadata.DataSourcePoolMetadataProvider
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:335)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	... 29 common frames omitted
	
解决：springboot2.0 要升级<druid.version>1.1.9</druid.version>

***************************
APPLICATION FAILED TO START
***************************

Description:

The dependencies of some of the beans in the application context form a cycle:

   testIndexController (field private com.example.demo.service.IBillService com.example.demo.controller.TestIndexController.billService)
      ↓
   billServiceImpl (field private com.example.demo.dao.BillDao com.example.demo.service.impl.BillServiceImpl.billDao)
      ↓
   billDao defined in file [D:\projectTest\demo1\target\classes\com\example\demo\dao\BillDao.class]
      ↓
   sqlSessionFactory defined in class path resource [org/mybatis/spring/boot/autoconfigure/MybatisAutoConfiguration.class]
┌─────┐
|  dataSource defined in class path resource [com/example/demo/conf/dataSource/DynamicDataSourceConfig.class]
↑     ↓
|  firstDataSource defined in class path resource [com/example/demo/conf/dataSource/DynamicDataSourceConfig.class]
↑     ↓
|  org.springframework.boot.autoconfigure.jdbc.DataSourceInitializerInvoker
└─────┘

解决：@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})

5、mybatis plus

		<!-- mybatis-plus -->
		<dependency>
			<groupId>com.baomidou</groupId>
			<artifactId>mybatis-plus-boot-starter</artifactId>
			<version>${mybatisplus.spring.boot.version}</version>
		</dependency>
		
		<!-- mybatis-plus  代码生成器VelocityTemplateEngine 中依赖 VelocityEngine-->
		<dependency>
			<groupId>org.apache.velocity</groupId>
			<artifactId>velocity</artifactId>
			<version>1.7</version>
		</dependency>
		
		源码解读：
		初始阶段  SqlSessionFactory、2 Configuration、 3 sqlSession
		代理阶段  mapper -- sqlSession
		数据获取  executor 反射会bo对象
		 
		加载配置文件信息 、
		Mapper接口无实现类: 通过动态代理MapperProxy (InvocationHandler)、加载配置信息，
		通过sqlSession 内部通过 executor(通过jdbc)执行查询。


# mybatis-plus配置
mybatis-plus:
  # 如果是放在src/main/java目录下 classpath:/com/yourpackage/*/mapper/*Mapper.xml
  # 如果是放在resource目录 classpath:/mapper/*Mapper.xml
  mapper-locations: classpath:/mapper/*Mapper.xml
  #实体扫描，多个package用逗号或者分号分隔
  typeAliasesPackage: com.example.demo.entity
  global-config:
    #主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
    id-type: 3
    #字段策略 0:"忽略判断",1:"非 NULL 判断"),2:"非空判断"
    field-strategy: 2
    #驼峰下划线转换
    db-column-underline: true
    #mp2.3+ 全局表前缀 mp_
    #table-prefix: mp_
    #刷新mapper 调试神器
    refresh-mapper: true
    #数据库大写下划线转换
    #capital-mode: true
    # Sequence序列接口实现类配置
    key-generator: com.baomidou.mybatisplus.incrementer.OracleKeyGenerator
    #逻辑删除配置（下面3个配置）
    logic-delete-value: 1
    logic-not-delete-value: 0
    sql-injector: com.baomidou.mybatisplus.mapper.LogicSqlInjector
    #自定义填充策略接口实现
    meta-object-handler: com.baomidou.mybatisplus.entity.DefaultMetaObjectHandler
  configuration:
    #配置返回数据库(column下划线命名&&返回java实体是驼峰命名)，自动匹配无需as（没开启这个，SQL需要写as： select user_id as userId）
    map-underscore-to-camel-case: true
	
	sql报错：
	<if test="user.userName != null">
		and u.user_name like "%"#{user.userName}"%"  
	</if>
	日志
	2018-08-14 11:10:41.634 [http-nio-9001-exec-7] ERROR c.a.druid.filter.stat.StatFilter - merge sql error, dbType mysql, druid-1.1.10, sql : SELECT COUNT(1) FROM ( SELECT
	        t.productName,t.num,t.amount,u.user_name,u.tel_phone
	    FROM
	    t_user as  u
	    LEFT JOIN t_bill as t ON t.userId = u.id
	    WHERE 1=1
	        and u.user_name like "%"?"%" ) TOTAL
		com.alibaba.druid.sql.parser.ParserException: syntax error, error in :'name like "%"?"%" ) TOTAL', expect RPAREN, actual QUES pos 243, line 8, column 38, token QUES
	
	解决：like concat('%',#{user.userName},'%')


6、事务控制
	@Transactional
	声明式事务底层原理
	spring事务处理机制
	事务的传播与监控

7、cache
一级缓存
二级缓存
		1、
		<!-- 6、ehcache -->
		<dependency>
			<groupId>net.sf.ehcache</groupId>
			<artifactId>ehcache</artifactId>
			<version>${ehcache.version}</version>
		</dependency>
		2、
		ehcache.xml
		3、
		CacheConfig
		
​		
	1	
		<cache name="users"
	       maxElementsInMemory="10000"
	       eternal="true"
	       overflowToDisk="false"
	       timeToIdleSeconds="100"
	       timeToLiveSeconds="1200"
	       diskPersistent="false"
	       memoryStoreEvictionPolicy="LRU"
	       diskExpiryThreadIntervalSeconds="120"/>
	
	eternal="true" 才起作用？
	
	解决：@Cacheable(cacheNames = "users",key="'user_'+#username", unless = "#result == null")
	
	<!--		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-cache</artifactId>
		</dependency>
		-->   
		换成：
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context-support</artifactId>
			<version>4.1.4.RELEASE</version>
		</dependency>
		
		核心逻辑在CacheInterceptor这个类（及其父类CacheAspectSupport）
8、redis
	
	@Bean
	public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate) {
	    return new RedisCacheManager(redisTemplate);  //不兼容
	}
	
	解决：
	@Bean
	public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate) {
	    RedisCacheWriter redisCacheWriter = RedisCacheWriter.lockingRedisCacheWriter(factory);
	    RedisCacheConfiguration defaultRedisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
	    RedisCacheConfiguration redisCacheConfiguration = defaultRedisCacheConfiguration
	            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
	    return new RedisCacheManager(redisCacheWriter,redisCacheConfiguration);
	}

9、freemarker+ITextRenderer
	itext:
	对html标签严格，少一个结束标签就会报错；2、后端实现复杂，服务器需要安装字体；3、图片渲染比较复杂(暂时还没解决)

	思路：
	freemarker+ITextRenderer 生成html转pdf
	
	8.1、 word模板转freemarker模板问题
		8.1.1、word另存为 word xml 格式
		${variables}会被分割，很难处理。
		 </w:t></w:r><w:proofErr w:type="spellStart"/><w:r><w:rPr><w:rFonts w:hint="eastAsia"/></w:rPr><w:t> 
		 替换为空格  把变量放在 ${}中。测试替换后的文件为无格式。
		 
		8.1.2 word另存为赛选过的html 
		8.1.3 模板中编码改成UTF-8,替换后生成文件没问题；
		8.1.4 转pdf时报错 : 
			 ERROR: "meta" 相关联的属性 "http-equiv" 应有左引号。'
			 ERROR:  '元素类型 "table" 必须后跟属性规范 ">" 或 "/>"。'
			 ERROR:  '元素类型 "br" 必须由匹配的结束标记 "</br>" 终止。'
				lang=EN-US  -->   lang='EN-US'
				class=MsoNormal  --> class='MsoNormal'
				align=center    --> align='center'
				class=MsoTableGrid  -->  class='MsoTableGrid'
				width=238 ......
			 
			 ERROR:  org.xml.sax.SAXParseException; 引用了实体 "nbsp", 但未声明它。
			 
				 <!DOCTYPE html [
					<!ENTITY nbsp "&#160;"> 
					]>	
				或者 <!DOCTYPE your_root_name [
						<!ENTITY nbsp " ">
					]>	
				html中开头会多出一个]>
			
	8.2、 html转pdf问题
		8.2.1、模板头部添加
		<?xml version="1.0" encoding="UTF-8"?>
		<!DOCTYPE html
			PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
			"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
		使其可以被Document转化为文档格式。
		
		8.2.2、中文不显示
		模板中的字体要与服务中添加的字体一致，font-family 要删掉，否则中文不显示。在body中统一配置。
		style中 添加body{
					font-family: SimSun;
				}


10、quartz定时器
       <dependency>
            <groupId>org.quartz-scheduler</groupId>
            <artifactId>quartz</artifactId>
            <version>2.2.1</version>
        </dependency>
        <dependency>
            <groupId>org.quartz-scheduler</groupId>
            <artifactId>quartz-jobs</artifactId>
            <version>2.2.1</version>
        </dependency>
	
	 使用：
	https://www.cnblogs.com/teach/p/5675960.html
11、Swagger

12、前端框架
