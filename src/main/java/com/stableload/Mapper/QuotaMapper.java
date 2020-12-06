package com.stableload.Mapper;

import com.stableload.model.Quota;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface QuotaMapper {
    boolean addCase(Quota c);
    boolean deleteCaseById(String id);
    boolean updateCaseById(Quota c);
    @Select("SELECT * FROM quota WHERE parent_id=#{id}")
    List<Quota> getCaseBypreId(String id);
    @Select("SELECT * FROM quota")
    List<Quota> getAllCases();
}
