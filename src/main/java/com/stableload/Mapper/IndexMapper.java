package com.stableload.Mapper;

import com.stableload.model.Index;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface IndexMapper {
    boolean addCase(Index c);
    boolean deleteCaseById(String id);
    boolean updateCaseById(Index c);
    Index getCaseById();
    List<Index> getAllCases();
}
