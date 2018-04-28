package com.able.dao;

import com.able.bean.Employee;

public interface EmployeeMapper {

    Employee selectById(Integer id);
}
