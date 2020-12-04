package com.stableload.Mapper;

import com.stableload.model.Stable;

import java.util.List;


public interface StableMapper {
    boolean addCase(Stable c);
    boolean deleteCaseById(String id);
    boolean updateCaseById(Stable c);
    Stable getCaseById();
    List<Stable> getAllCases();
}
