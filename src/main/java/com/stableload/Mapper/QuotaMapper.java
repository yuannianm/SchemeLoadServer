package com.stableload.Mapper;

import com.stableload.model.Quota;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface QuotaMapper {
    @Insert("INSERT INTO quota(id,parent_id,name,grade) values(#{id},#{parent_id},#{name},#{grade})")
    boolean addQuota(Quota c);

    @Delete("DELETE FROM QUOTA WHERE id = #{id}")
    boolean deleteQuotaById(String id);

    @Update("UPDATE QUOTA set parent_id=#{parent_id},body=#{body},grade=#{grade} where id=#{id}")
    boolean updateQuotaById(Quota c);

    @Select("SELECT * FROM QUOTA WHERE id = #{id}")
    Quota getQuotaById();

    @Select("SELECT * FROM quota WHERE parent_id=#{id}")
    List<Quota> getCaseBypreId(String id);
    @Select("SELECT * FROM quota")
    List<Quota> getAllCases();
}
