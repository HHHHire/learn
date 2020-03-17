package cn.edu.jxust.sort.service;

import cn.edu.jxust.sort.entity.po.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author: ddh
 * @data: 2020/1/4 20:09
 * @description
 **/
public interface DeviceService {
    Integer deleteDeviceByEnterpriseId(String enterpriseId);

    Page<Device> getAlldeviceByEnterpriseId(String enterpriseId, Pageable pageable);

    Device createDevice(Device device);

    Integer deleteDeviceByDeviceId(String deviceId);
}
