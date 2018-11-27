package com.yanwu.demo.saga.copy.service.impl;

import com.yanwu.demo.saga.copy.dao.mapper.DemoServerMapper;
import com.yanwu.demo.saga.copy.dao.model.DemoServer;
import com.yanwu.demo.saga.copy.dao.model.DemoServerExample;
import com.yanwu.demo.saga.copy.service.SagaServerService;
import org.apache.servicecomb.saga.omega.transaction.annotations.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author XuBaofeng.
 * @date 2018-09-03 15:08.
 * <p>
 * description:
 */
@Service
public class SagaServerServiceImpl  {

    @Autowired
    private DemoServerMapper demoServerMapper;

    /**
     * 该方法提供给saga-client调用
     * 1. 我们手动创建除零异常, 测试saga-client是否会触发补偿事务
     * 2. 打断点卡住方法不让执行, 测试saga-client是否会触发补偿事务
     * @param demoServer
     * @return
     * @throws Exception
     */
    //@Override
    @Compensable(timeout = 200, compensationMethod = "createRollback")
    //@Transactional(rollbackFor = Exception.class)
    public int create(DemoServer demoServer) throws Exception {
        System.out.println("========== saga2 transaction test start ==========");
        int index = demoServerMapper.insert(demoServer);
      //  index = 1 / 0;
        throw new IllegalArgumentException("can not order the peo large than 10  all Rollback");
        //System.out.println("========== saga2 transaction test end ==========");
        //return index;
    }


    /**
     * 事务回滚的补偿措施
     * @param demoServer
     * @return
     */
    public int createRollback(DemoServer demoServer) {
        System.out.println("========== create server2 rollback ==========");
        DemoServerExample example = new DemoServerExample();
        DemoServerExample.Criteria criteria = example.createCriteria();
        criteria.andServerNameEqualTo(demoServer.getServerName());
        criteria.andServerPasswordEqualTo(demoServer.getServerPassword());
        return demoServerMapper.deleteByExample(example);
    }
}
