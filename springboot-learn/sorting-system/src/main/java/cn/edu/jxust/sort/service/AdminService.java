package cn.edu.jxust.sort.service;

import cn.edu.jxust.sort.entity.po.Admin;

import java.util.List;

/**
 * @author: ddh
 * @data: 2020/1/3 11:21
 * @description
 **/
public interface AdminService {
    Admin createAdmin(Admin admin);

    Admin getAdmin(String adminId);

    List<Admin> getAllAdmin();

    Integer deleteAdminById(String adminId);
}
