package com.yanwu.demo.saga.server.service.impl;

import com.yanwu.demo.pojo.pojo.DemoClientPojo;
import com.yanwu.demo.saga.server.dao.mapper.DemoServerMapper;
import com.yanwu.demo.saga.server.dao.model.DemoServer;
import com.yanwu.demo.saga.server.dao.model.DemoServerExample;
import com.yanwu.demo.saga.server.service.SagaServerService;
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



    /**
     * 该方法提供给saga-client调用
     * 1. 我们手动创建除零异常, 测试saga-client是否会触发补偿事务
     * 2. 打断点卡住方法不让执行, 测试saga-client是否会触发补偿事务
     * @param demoServer
     * @return
     * @throws Exception
     */
    //@Override
    @Compensable( compensationMethod = "createRollback")
    //@Transactional(rollbackFor = Exception.class)
    public int create(DemoClientPojo demoServer){
        System.out.println("========== saga transaction test start =========="+demoServer);
       // int index = demoServerMapper.insert(demoServer);
        //index = 1 / 0;
        System.out.println("========== saga transaction test end ==========");
        return 1;
    }


    /**
     * 事务回滚的补偿措施
     * @param demoServer
     * @return
     */
    public int createRollback(DemoClientPojo  demoServer) {
        System.out.println("========== create copy rollback ==========");
       /* DemoServerExample example = new DemoServerExample();
        DemoServerExample.Criteria criteria = example.createCriteria();
        criteria.andServerNameEqualTo(demoServer.getServerName());
        criteria.andServerPasswordEqualTo(demoServer.getServerPassword());
        int i=demoServerMapper.deleteByExample(example);*/
        System.out.println(demoServer);
        return 1;
    }
}
