package cn.edu.jxust.sort.repository;

import cn.edu.jxust.sort.entity.po.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: ddh
 * @data: 2020/1/3 9:36
 * @description
 **/
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    Integer deleteByEnterpriseId(String enterpriseId);

    Page<Employee> findAllByEnterpriseId(String enterpriseId, Pageable pageable);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    Integer deleteByEmployeeCard(String employeeCard);

    /**
     * 通过员工卡号更新员工信息
     * @param employee 员工信息
     * @return Integer
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "update ss_employee set " +
            "employee_name=:#{#employee.employeeName}, " +
            "employee_age=:#{#employee.employeeAge}, " +
            "employee_phone=:#{#employee.employeePhone}, " +
            "employee_sex=:#{#employee.employeeSex} " +
            "where employee_card=:#{#employee.employeeCard}")
    Integer updateByEmployeeCard(@Param("employee") Employee employee);
}
