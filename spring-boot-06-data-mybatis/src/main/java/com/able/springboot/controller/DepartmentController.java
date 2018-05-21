package com.able.springboot.controller;

import com.able.springboot.bean.Department;
import com.able.springboot.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentMapper departmentMapper;

    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id) {
        return departmentMapper.queryById(id);
    }

    @GetMapping("/dept")
    public Department insert(Department department) {
        departmentMapper.insert(department);
        return department;
    }

}
