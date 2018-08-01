package com.styzf.test.mapper;

import com.styzf.test.entity.StyzfTestMain;
import com.styzf.test.entity.StyzfTestMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StyzfTestMainMapper {
    int countByExample(StyzfTestMainExample example);

    int deleteByExample(StyzfTestMainExample example);

    int deleteByPrimaryKey(String testId);

    int insert(StyzfTestMain record);

    int insertSelective(StyzfTestMain record);

    List<StyzfTestMain> selectByExample(StyzfTestMainExample example);

    StyzfTestMain selectByPrimaryKey(String testId);

    int updateByExampleSelective(@Param("record") StyzfTestMain record, @Param("example") StyzfTestMainExample example);

    int updateByExample(@Param("record") StyzfTestMain record, @Param("example") StyzfTestMainExample example);

    int updateByPrimaryKeySelective(StyzfTestMain record);

    int updateByPrimaryKey(StyzfTestMain record);
}