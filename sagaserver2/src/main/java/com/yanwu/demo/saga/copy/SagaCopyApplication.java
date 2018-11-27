package com.yanwu.demo.saga.copy;

import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.apache.servicecomb.saga.omega.spring.EnableOmega;
import org.apache.servicecomb.springboot.starter.provider.EnableServiceComb;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author XuBaofeng.
 * @date 2018-09-03 15:08.
 * <p>
 * description:
 */
@EnableOmega
@EnableServiceComb
@EnableSwagger2
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"com.yanwu.demo.saga.copy.dao.mapper"})
public class SagaCopyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SagaCopyApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return RestTemplateBuilder.create();
    }

}
