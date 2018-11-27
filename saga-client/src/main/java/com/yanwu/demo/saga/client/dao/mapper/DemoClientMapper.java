package com.yanwu.demo.saga.client.dao.mapper;

import com.yanwu.demo.saga.client.dao.model.DemoClient;
import com.yanwu.demo.saga.client.dao.model.DemoClientExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DemoClientMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_client
     *
     * @mbggenerated
     */
    int countByExample(DemoClientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_client
     *
     * @mbggenerated
     */
    int deleteByExample(DemoClientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_client
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer clientId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_client
     *
     * @mbggenerated
     */
    int insert(DemoClient record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_client
     *
     * @mbggenerated
     */
    int insertSelective(DemoClient record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_client
     *
     * @mbggenerated
     */
    List<DemoClient> selectByExample(DemoClientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_client
     *
     * @mbggenerated
     */
    DemoClient selectByPrimaryKey(Integer clientId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_client
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") DemoClient record, @Param("example") DemoClientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_client
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") DemoClient record, @Param("example") DemoClientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_client
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(DemoClient record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table demo_client
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DemoClient record);
}