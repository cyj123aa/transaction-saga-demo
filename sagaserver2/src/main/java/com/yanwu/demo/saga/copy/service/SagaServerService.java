package com.yanwu.demo.saga.copy.service;

import com.yanwu.demo.saga.copy.dao.model.DemoServer;

/**
 * @author XuBaofeng.
 * @date 2018-09-03 15:08.
 * <p>
 * description:
 */
public interface SagaServerService {
    /**
     * 新建DemoServerEntity
     * @param demoServer
     * @return
     * @throws Exception
     */
    int create(DemoServer demoServer) throws Exception;
}
