package cn.edu.jxust.sort.repository;

import cn.edu.jxust.sort.entity.po.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: ddh
 * @data: 2020/1/3 9:36
 * @description
 **/
public interface DeviceRepository extends JpaRepository<Device, String> {
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    Integer deleteByEnterpriseId(String enterpriseId);

    Page<Device> findAllByEnterpriseId(String enterpriseId, Pageable pageable);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    Integer deleteByDeviceId(String deviceId);
}
