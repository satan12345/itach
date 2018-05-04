package com.able.dao;

import com.able.bean.Employee;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {

    Employee selectById(Integer id);

    Employee selectByIdAndName(Integer id,String name);
}
