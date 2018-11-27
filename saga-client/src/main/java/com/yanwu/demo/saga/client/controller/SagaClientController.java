package com.yanwu.demo.saga.client.controller;

import com.yanwu.demo.pojo.pojo.DemoClientPojo;
import com.yanwu.demo.pojo.pojo.DemoServerPojo;
import com.yanwu.demo.saga.client.dao.model.DemoClient;
import com.yanwu.demo.saga.client.service.impl.SagaClientServiceImpl;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.apache.servicecomb.saga.omega.context.annotations.SagaStart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author XuBaofeng.
 * @date 2018-09-03 15:08.
 * <p>
 * description:
 */
@RestController
@RequestMapping(value = "/client/saga")
@RestSchema(schemaId = "sagaClient")
public class SagaClientController {


    private RestTemplate restTemplate = RestTemplateBuilder.create();

    /**
     * client 无数据库操作
     * @param pojo
     * @return
     * @throws Exception
     */
    @SagaStart
    @PostMapping(value = "/invoke")
    public int invoke(@RequestBody DemoClientPojo pojo)  {
        DemoServerPojo demoServerPojo = new DemoServerPojo();
        demoServerPojo.setServerName(pojo.getClientName());
        demoServerPojo.setServerPassword(pojo.getClientPassword());
        Integer index = restTemplate.postForObject("cse://saga-server/server/saga/create", demoServerPojo, Integer.class);

        Integer index2 = restTemplate.postForObject("cse://saga-copy/copy/saga/create", demoServerPojo, Integer.class);
        //   saga1Consumer.sageServerCreate(demoServerPojo);
        //   saga2Consumer.sageServerCreate(demoServerPojo);
        return 0;
    }




}
