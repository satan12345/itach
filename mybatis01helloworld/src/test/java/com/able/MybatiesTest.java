package com.able;

import com.able.bean.Employee;
import com.able.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class MybatiesTest {

    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;//非线程安全 非单线程环境中不可以作为成员变量

    /**
     * 根据xml配置文件(全局配置文件)创建一个SqlSessionFactory对象
     *
     * @throws Exception
     */
    @Before
    public void init() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);


    }

    @Test
    public void test1() {
        sqlSession = sqlSessionFactory.openSession();
        Employee employee = sqlSession.selectOne("com.able.dao.EmployeeMapper.selectById", 1);
        System.out.println(employee);

    }

    @Test
    public void test2() {
        //会为接口创建一个代理对象 代理对象去执行CRUD方法
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        System.out.println(mapper.getClass());
        Employee employee = mapper.selectById(1);
        System.out.println(employee);
    }

    @After
    public void destory() {
        sqlSession.close();
    }
}
