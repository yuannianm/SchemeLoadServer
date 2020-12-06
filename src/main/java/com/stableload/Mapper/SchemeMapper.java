package com.stableload.Mapper;

import com.stableload.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SchemeMapper {
    boolean addCase(Scheme c);
    boolean deleteCaseById(String id);
    boolean updateCaseById(Scheme c);
    Scheme getCaseById();
    @Select("SELECT * FROM scheme")
    List<Scheme> getAllCases();
}
