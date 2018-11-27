package com.yanwu.demo.saga.copy.dao.mapper;

import com.yanwu.demo.saga.copy.dao.model.DemoServer;
import com.yanwu.demo.saga.copy.dao.model.DemoServerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DemoServerMapper {
    /**
     *
     * @param example
     */
    int countByExample(DemoServerExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(DemoServerExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param serverId
     */
    int deleteByPrimaryKey(Integer serverId);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(DemoServer record);

    /**
     *
     * @param record
     */
    int insertSelective(DemoServer record);

    /**
     *
     * @param example
     */
    List<DemoServer> selectByExample(DemoServerExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param serverId
     */
    DemoServer selectByPrimaryKey(Integer serverId);

    /**
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") DemoServer record, @Param("example") DemoServerExample example);

    /**
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") DemoServer record, @Param("example") DemoServerExample example);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeySelective(DemoServer record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(DemoServer record);
}