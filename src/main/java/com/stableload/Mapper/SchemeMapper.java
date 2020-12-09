package com.stableload.Mapper;

import com.stableload.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SchemeMapper {
    @Insert("INSERT INTO SCHEME(id,name) values(#{id},#{name})")
    boolean addScheme(Scheme c);

    @Delete("DELETE FROM SCHEME WHERE id = #{id}")
    boolean deleteSchemeById(String id);

    @Update("UPDATE SCHEME set name=#{name} where id=#{id}")
    boolean updateSchemeById(Scheme c);

    @Select("SELECT * FROM SCHEME WHERE id = #{id}")
    Scheme getSchemeById(String id);

    @Select("SELECT * FROM SCHEME WHERE name = #{name}")
    Scheme getSchemeByName(String name);

    @Select("SELECT * FROM scheme")
    List<Scheme> getAllCases();
}
