package cn.edu.jxust.sort.service;

import cn.edu.jxust.sort.entity.po.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author: ddh
 * @data: 2020/1/4 21:08
 * @description
 **/
public interface EmployeeService {
    Integer deleteEmployeeByEnterpriseId(String enterpriseId);

    Page<Employee> getAllEmployeeByEnterpriseId(String enterpriseId, Pageable pageable);

    Integer deleteEmployeeByEmployeeCard(String employeeCard);

    Integer updateEmployeeByEmployeeCard(Employee employee);

    Employee createEmployee(Employee employee);
}
