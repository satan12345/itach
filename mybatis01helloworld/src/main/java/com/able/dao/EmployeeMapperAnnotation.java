package com.able.dao;

import com.able.bean.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapperAnnotation {

    @Select("SELECT * FROM tbl_employee te WHERE te.id =#{id}")
    Employee selectById(Integer id);

}
