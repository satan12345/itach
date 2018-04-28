package com.able.springboot;

import com.able.springboot.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

/* *    
 *   
 * @author jipeng
 * @Description SpringBoot 单元测试
 * @date 2018/4/27 15:47  
 * @param
 * @return   
 */ 
@RunWith(SpringRunner.class)
@SpringBootTest

public class SpringBoot02ConfigApplicationTests {
	@Autowired
	Person person;

	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void contextLoads() {
		int a=5/3;
		System.out.println(a);
		System.out.println(person);
	}

	@Test
	public void test1(){
		boolean bool = applicationContext.containsBean("helloController");
		System.out.println(bool);
	}

}
