package com.able.springboot.controller;

import com.able.springboot.bean.Employee;
import com.able.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("emp/{id}")
    public Employee queryById(@PathVariable("id") Integer id){
        return employeeMapper.getEmpById(id);
    }
}
