package com.stableload.Mapper;

import com.stableload.model.ScoringStandard;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface ScoringStandardMapper {
    @Insert("INSERT INTO SCORINGSTANDARD(id,parent_id,name) values(#{id},#{parent_id},#{name})")
    boolean addScoringStandard(ScoringStandard c);

    @Delete("DELETE FROM SCORINGSTANDARD WHERE id = #{id}")
    boolean deleteScoringStandardById(String id);

    @Update("UPDATE SCORINGSTANDARD set parent_id=#{parent_id},name=#{name} where id=#{id}")
    boolean updateScoringStandardById(ScoringStandard c);

    @Select("SELECT * FROM SCORINGSTANDARD WHERE id = #{id}")
    ScoringStandard getScoringStandardById();

    @Select("SELECT * FROM scoringstandard WHERE parent_id=#{id}")
    List<ScoringStandard> getCaseBypreId(String id);

    @Select("SELECT * FROM scoringstandard ")
    List<ScoringStandard> getAllCases();
}
