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
    SqlSession sqlSession;

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
        sqlSession = sqlSessionFactory.openSession();

    }

    @Test
    public void test1() {

        Employee employee = sqlSession.selectOne("com.able.dao.EmployeeMapper.selectById", 1);
        System.out.println(employee);

    }

    @Test
    public void test2() {
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.selectById(1);
        System.out.println(employee);
    }

    @After
    public void destory() {
        sqlSession.close();
    }
}
