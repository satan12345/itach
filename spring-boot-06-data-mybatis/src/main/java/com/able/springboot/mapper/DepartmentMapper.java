package com.able.springboot.mapper;

import com.able.springboot.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * 指定这是一个操作数据库的mapper
 */

public interface DepartmentMapper {
    @Select("select * from department where id=#{id}")
    Department queryById(@Param("id") Integer id);

    @Delete("delete from department where id=#{id}")
    int deleteById(@Param("id") Integer id);
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(`department_name`) values(#{departmentName})")
    int insert(Department department);
    @Update("update department set department_name=#{department_name} where id=#{id}")
    int updateDeaprt(Department department);
}
