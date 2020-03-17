package cn.edu.jxust.sort.repository;


import cn.edu.jxust.sort.entity.po.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: ddh
 * @data: 2020/1/8 15:29
 * @description
 **/
public interface RecordRepository extends JpaRepository<Record, String> {
    Page<Record> findAllByEnterpriseId(String enterpriseId, Pageable pageable);

    Page<Record> findByCreateTimeBetweenAndEnterpriseIdOrderByCreateTimeDesc(Long start, Long end, String enterpriseId);

    Page<Record> findByCreateTimeBetweenAndEnterpriseIdAndCategoryIdOrderByCreateTimeDesc(Long start, Long end, String enterpriseId, String categoryId);

    @Query(value = "select * from ss_record sr where sr.enterprise_id = ?1 and sr.counts < 0 order by sr.create_time desc", nativeQuery = true)
    List<Record> findOutRecord(String enterpriseId);

    @Query(value = "select * from ss_record sr where sr.enterprise_id = ?1 and sr.employee_card = ?2 and sr.counts < 0 order by sr.create_time desc", nativeQuery = true)
    List<Record> findOutRecordByEmployeeCard(String enterpriseId, String employeeCard);
}
