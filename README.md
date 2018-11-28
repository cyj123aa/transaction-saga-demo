demo一共三个springboot项目，和一个pojo项目
SagaClient，是saga的起点，
sagaservice 和sagaservice2 是两个子事务

> * 1,启动saga的alpha服务，使用你们当前的alpha即可 
> * 2，启动注册中心（使用的是servicecomb）
> * 3，运行SagaClientApplication.calss （启动SagaClient服务） 
> * 4，运行SagaServerApplication.calss （启动sagaservice服务） 
> * 5，运行SagaCopyApplication.calss （启动sagaservice2服务）

>启动SagaClient服务当中
第一步为调用启动sagaservice服务
第二步为调用启动sagaservice2服务
第三步为，抛出异常

当com.yanwu.demo.saga.copy.service.impl.SagaServerServiceImpl 的create方法
参数为（java.lang.String）时，saga可以正常进入补偿方法 

当参数为（com.yanwu.demo.pojo.pojo.DemoClientPojo）时，事务无法进入补偿

出现异常argument type mismatch

当alpha服务，导入
 ```
 <dependency>
            <groupId>com.yanwu.demo.pojo</groupId>
            <artifactId>pojo</artifactId>
            <version>1.0-SNAPSHOT</version>
 </dependency>
 ```
      
之后问题解决saga正常使用