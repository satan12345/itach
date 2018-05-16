package com.able.springboot.controller;

import com.able.springboot.dao.DepartmentDao;
import com.able.springboot.dao.EmployeeDao;
import com.able.springboot.entities.Department;
import com.able.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;
    /***    
       
      @author jipeng
      @Description 查询所有员工返回列表页
      @date 2018/5/15 9:55  
      @param
      @return java.lang.String  
     */
    @GetMapping("emps")
    public ModelAndView list(){
        ModelAndView mv=new ModelAndView("emp/list");
        Collection<Employee> all = employeeDao.getAll();
        mv.addObject("emps",all);
        return mv;
    }
    @GetMapping("emp")
    public ModelAndView toaddPage(){
        ModelAndView modelAndView=new ModelAndView("emp/add");
        Collection<Department> departments = departmentDao.getDepartments();
        modelAndView.addObject("depts",departments);
        return modelAndView;
    }
    @PostMapping("emp")
    public String addEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    @GetMapping("emp/{id}")
    public ModelAndView queryById(@PathVariable("id") Integer id){
        ModelAndView modelAndView=new ModelAndView("emp/add");
        Employee employee = employeeDao.get(id);
        modelAndView.addObject("emp",employee);
        Collection<Department> departments = departmentDao.getDepartments();
        modelAndView.addObject("depts",departments);
        return modelAndView;
    }

    @PutMapping("emp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("emp/{id}")
    public String deleteById(@PathVariable("id")Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
