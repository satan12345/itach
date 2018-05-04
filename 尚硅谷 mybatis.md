# 			尚硅谷 mybatis

## 1为什么要使用Mybatis

	>mybiatis是一个半自动化的的持久层框架
	>
	>jdbc:
	>
	>	sql夹在java代码块里 耦合度高度导致硬编码内伤
	>
	>	维护不易切实际开发需求中sql是有变化 频繁修改的情况多见
	>
	>Hibernate 和jpa
	>
	>- 	长难复杂的sql 对于hibernate 而言处理也不容器
	>
	>- 	内部自动生产的sql 不容易做特殊优化
	>
	>- 	基于全映射的全自动框架 大量字段的POJO进行部分映射时比较困难 导致数据库性能下降
	>
	>  对开发人员而言 核心sql 还是需要自己优化
	>
	>  sql和java编码分开 功能边界清晰 一个专注业务 一个专注数据
	>
	>  

Mybatis 运行增加 删除 修改定义以下类型的返回值 :Integer Long Boolean  void 

### mybatis 参数处理

单个参数:mybatis 不会做特殊处理

​	 #{参数名}:取出参数值

多个参数：mabatis会做特殊处理.

​	多个参数会被封装成一个map

​	key:param1,param2...   或者参数使用索引

​	value:传入的参数值

​	#{} 就是从map中取出指定的key的值

命名参数 @Param



POJO 如果多个参数正好是我们业务逻辑的数据模型 我们就可以直接传入POJO

​	#{属性名}:取出传入POJO的属性值



MAP:如果多个参数不是业务模型中的数据 没有对应的pojo 为了方便 我们也可以传入map



​	#{key}:取出map中对应的值



如果多个参数不是业务模型中的数据 但是要经常使用 推荐编写一个(TO transfer Object)数据传输对象



========================思考================================	

public Employee getEmp(@Param("id")Integer id,String lastName);

	取值：id==>#{id/param1}   lastName==>#{param2}

public Employee getEmp(Integer id,@Param("e")Employee emp);
	取值：id==>#{param1}    lastName===>#{param2.lastName/e.lastName}

##特别注意：如果是Collection（List、Set）类型或者是数组，
		 也会特殊处理。也是把传入的list或者数组封装在map中。
			key：Collection（collection）,如果是List还可以使用这个key(list)
				数组(array)
public Employee getEmpById(List<Integer> ids);
	取值：取出第一个id的值：   #{list[0]}

========================结合源码，mybatis怎么处理参数==========================

总结：参数多时会封装map，为了不混乱，我们可以使用@Param来指定封装时使用的key；

#{key}就可以取出map中的值；

(@Param("id")Integer id,@Param("lastName")String lastName);
ParamNameResolver解析参数封装map的；
//1、names：{0=id, 1=lastName}；构造器的时候就确定好了

	确定流程：
	1.获取每个标了param注解的参数的@Param的值：id，lastName；  赋值给name;
	2.每次解析一个参数给map中保存信息：（key：参数索引，value：name的值）
		name的值：
			标注了param注解：注解的值
			没有标注：
				1.全局配置：useActualParamName（jdk1.8）：name=参数名
				2.name=map.size()；相当于当前元素的索引
	{0=id, 1=lastName,2=2}


args【1，"Tom",'hello'】:

public Object getNamedParams(Object[] args) {
    final int paramCount = names.size();
    //1、参数为null直接返回
    if (args == null || paramCount == 0) {
      return null;
     
    //2、如果只有一个元素，并且没有Param注解；args[0]：单个参数直接返回
    } else if (!hasParamAnnotation && paramCount == 1) {
      return args[names.firstKey()];
      
    //3、多个元素或者有Param标注
    } else {
      final Map<String, Object> param = new ParamMap<Object>();
      int i = 0;
      
      //4、遍历names集合；{0=id, 1=lastName,2=2}
      for (Map.Entry<Integer, String> entry : names.entrySet()) {
      
      	//names集合的value作为key;  names集合的key又作为取值的参考args[0]:args【1，"Tom"】:
      	//eg:{id=args[0]:1,lastName=args[1]:Tom,2=args[2]}
        param.put(entry.getValue(), args[entry.getKey()]);


        // add generic param names (param1, param2, ...)param
        //额外的将每一个参数也保存到map中，使用新的key：param1...paramN
        //效果：有Param注解可以#{指定的key}，或者#{param1}
        final String genericParamName = GENERIC_NAME_PREFIX + String.valueOf(i + 1);
        // ensure not to overwrite parameter named with @Param
        if (!names.containsValue(genericParamName)) {
          param.put(genericParamName, args[entry.getKey()]);
        }
        i++;
      }
      return param;
    }
  }
}

```java
  public Object getNamedParams(Object[] args) {
    final int paramCount = names.size();
    if (args == null || paramCount == 0) {
      return null;
    } else if (!hasParamAnnotation && paramCount == 1) {
      return args[names.firstKey()];
    } else {
      final Map<String, Object> param = new ParamMap<Object>();
      int i = 0;
      for (Map.Entry<Integer, String> entry : names.entrySet()) {
        param.put(entry.getValue(), args[entry.getKey()]);
        // add generic param names (param1, param2, ...)
        final String genericParamName = GENERIC_NAME_PREFIX + String.valueOf(i + 1);
        // ensure not to overwrite parameter named with @Param
        if (!names.containsValue(genericParamName)) {
          param.put(genericParamName, args[entry.getKey()]);
        }
        i++;
      }
      return param;
      //{arg0=xxx,arg1=xxx,param0=xxx,param1=xxx}
    }
  }
```

```java
 public ParamNameResolver(Configuration config, Method method) {
    final Class<?>[] paramTypes = method.getParameterTypes();
    final Annotation[][] paramAnnotations = method.getParameterAnnotations();
    final SortedMap<Integer, String> map = new TreeMap<Integer, String>();
    int paramCount = paramAnnotations.length;
    // get names from @Param annotations
    for (int paramIndex = 0; paramIndex < paramCount; paramIndex++) {
      if (isSpecialParameter(paramTypes[paramIndex])) {
        // skip special parameters
        continue;
      }
      String name = null;
      for (Annotation annotation : paramAnnotations[paramIndex]) {
        if (annotation instanceof Param) {
          hasParamAnnotation = true;
          name = ((Param) annotation).value();
          break;
        }
      }
      if (name == null) {
        // @Param was not specified.
        if (config.isUseActualParamName()) {
          name = getActualParamName(method, paramIndex);
        }
        if (name == null) {
          // use the parameter index as the name ("0", "1", ...)
          // gcode issue #71
          name = String.valueOf(map.size());
        }
      }
      map.put(paramIndex, name);
    }
    names = Collections.unmodifiableSortedMap(map);
   //  names={0=args0,1=args1}
  }
```

​         # {} 预编译的形式 只能将参数设置到sql语句中    PreparedStatement:防止sql注入

​	${}: SQL拼串 容易导致 sql注入

​	原生jdbc不支持展位的的地方我们就可以使用${}进行取值

​	比如分表 排序 :按照年份分表

​		select * from tbl_xxx_${year} where xxx

##{}:更丰富的用法：
	规定参数的一些规则：
	javaType、 jdbcType、 mode（存储过程）、 numericScale、
	resultMap、 typeHandler、 jdbcTypeName、 expression（未来准备支持的功能）；
	
	jdbcType通常需要在某种特定的条件下被设置：
		在我们数据为null的时候，有些数据库可能不能识别mybatis对null的默认处理。比如Oracle（报错）；
		
		JdbcType OTHER：无效的类型；因为mybatis对所有的null都映射的是原生Jdbc的OTHER类型，oracle不能正确处理;
		
		由于全局配置中：jdbcTypeForNull=OTHER；oracle不支持；两种办法
		1、#{email,jdbcType=NULL};
		2、jdbcTypeForNull=NULL
			<setting name="jdbcTypeForNull" value="NULL"/>