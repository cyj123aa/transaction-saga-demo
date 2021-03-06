package com.yanwu.demo.saga.client;

import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.apache.servicecomb.saga.omega.spring.EnableOmega;
import org.apache.servicecomb.springboot.starter.provider.EnableServiceComb;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author XuBaofeng.
 * @date 2018-09-03 15:08.
 * <p>
 * description:
 */
@SpringBootApplication
@EnableServiceComb
@EnableOmega
@MapperScan(basePackages = {"com.yanwu.demo.saga.client.dao.mapper"})
public class SagaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SagaClientApplication.class, args);
    }

}
