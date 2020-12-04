package com.stableload.Mapper;

import com.stableload.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface CaseMapper {
    @Insert("INSERT INTO ")
    boolean addCase(Case c);
    boolean deleteCaseById(String id);
    boolean updateCaseById(Case c);
    Case getCaseById();
    List<Case> getAllCases();
}
