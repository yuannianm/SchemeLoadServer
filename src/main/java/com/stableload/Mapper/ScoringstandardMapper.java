package com.stableload.Mapper;

import com.stableload.model.Scoringstandard;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface ScoringstandardMapper {
    boolean addCase(Scoringstandard c);
    boolean deleteCaseById(String id);
    boolean updateCaseById(Scoringstandard c);
    Scoringstandard getCaseById();
    @Select("SELECT * FROM scoringstandard")
    List<Scoringstandard> getAllCases();
}
