package com.yanwu.demo.saga.client.service;

import com.yanwu.demo.saga.client.dao.model.DemoClient;
import com.yanwu.demo.saga.client.dao.model.DemoClientEntity;

/**
 * @author XuBaofeng.
 * @date 2018-09-03 15:08.
 * <p>
 * description:
 */
public interface SagaClientService {
    /**
     * 新建DemoServerEntity
     *
     * @param entity
     * @return
     * @throws Exception
     */
    int create(DemoClient entity) throws Exception;
}
