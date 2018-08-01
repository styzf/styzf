package com.styzf.test.mapper;

import com.styzf.test.entity.TestMain;
import com.styzf.test.entity.TestMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestMainMapper {
    int countByExample(TestMainExample example);

    int deleteByExample(TestMainExample example);

    int deleteByPrimaryKey(Integer testId);

    int insert(TestMain record);

    int insertSelective(TestMain record);

    List<TestMain> selectByExample(TestMainExample example);

    TestMain selectByPrimaryKey(Integer testId);

    int updateByExampleSelective(@Param("record") TestMain record, @Param("example") TestMainExample example);

    int updateByExample(@Param("record") TestMain record, @Param("example") TestMainExample example);

    int updateByPrimaryKeySelective(TestMain record);

    int updateByPrimaryKey(TestMain record);
}