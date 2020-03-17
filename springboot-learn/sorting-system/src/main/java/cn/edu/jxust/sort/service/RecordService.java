package cn.edu.jxust.sort.service;

import cn.edu.jxust.sort.entity.po.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author: ddh
 * @data: 2020/1/8 15:59
 * @description
 **/
public interface RecordService {
    Page<Record> getRecord(String enterpriseId, String categoryId, String startTime, String endTime, Pageable pageable);

    Record createRecord(Record record);

    List<Record> getOutRecord(String enterpriseId);

    List<Record> getOutRecordByEmployeeCard(String enterpriseId, String employeeCard);
}
