package cn.edu.jxust.sort.service;

import cn.edu.jxust.sort.entity.po.Enterprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author: ddh
 * @data: 2020/1/4 20:08
 * @description
 **/
public interface EnterpriseService {
    Enterprise getEnterpriseByName(String enterpriseName);

    Enterprise createEnterprise(Enterprise enterprise);

    Page<Enterprise> getAllEnterprise(Pageable page);

    Enterprise getEnterpriseById(String enterpriseId);

    Integer deleteEnterpriseById(String enterpriseId);
}
