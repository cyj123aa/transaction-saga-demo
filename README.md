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

出现异常 
 Omega服务的错误栈
 ```
io.grpc.StatusRuntimeException: CANCELLED: Failed to read message.
	at io.grpc.Status.asRuntimeException(Status.java:526)
	at io.grpc.stub.ClientCalls$StreamObserverToCallListenerAdapter.onClose(ClientCalls.java:420)
	at io.grpc.PartialForwardingClientCallListener.onClose(PartialForwardingClientCallListener.java:39)
	at io.grpc.ForwardingClientCallListener.onClose(ForwardingClientCallListener.java:23)
	at io.grpc.ForwardingClientCallListener$SimpleForwardingClientCallListener.onClose(ForwardingClientCallListener.java:40)
	at io.grpc.internal.CensusStatsModule$StatsClientInterceptor$1$1.onClose(CensusStatsModule.java:684)
	at io.grpc.PartialForwardingClientCallListener.onClose(PartialForwardingClientCallListener.java:39)
	at io.grpc.ForwardingClientCallListener.onClose(ForwardingClientCallListener.java:23)
	at io.grpc.ForwardingClientCallListener$SimpleForwardingClientCallListener.onClose(ForwardingClientCallListener.java:40)
	at io.grpc.internal.CensusTracingModule$TracingClientInterceptor$1$1.onClose(CensusTracingModule.java:403)
	at io.grpc.internal.ClientCallImpl.closeObserver(ClientCallImpl.java:459)
	at io.grpc.internal.ClientCallImpl.access$300(ClientCallImpl.java:63)
	at io.grpc.internal.ClientCallImpl$ClientStreamListenerImpl.close(ClientCallImpl.java:546)
	at io.grpc.internal.ClientCallImpl$ClientStreamListenerImpl.access$600(ClientCallImpl.java:467)
	at io.grpc.internal.ClientCallImpl$ClientStreamListenerImpl$1MessagesAvailable.runInContext(ClientCallImpl.java:531)
	at io.grpc.internal.ContextRunnable.run(ContextRunnable.java:37)
	at io.grpc.internal.SerializingExecutor.run(SerializingExecutor.java:123)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
Caused by: java.lang.IllegalArgumentException: argument type mismatch
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.apache.servicecomb.saga.omega.context.CallbackContext.apply(CallbackContext.java:50)
	at org.apache.servicecomb.saga.omega.transaction.CompensationMessageHandler.onReceive(CompensationMessageHandler.java:35)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:333)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:207)
	at com.sun.proxy.$Proxy87.onReceive(Unknown Source)
	at org.apache.servicecomb.saga.omega.connector.grpc.saga.GrpcCompensateStreamObserver.onNext(GrpcCompensateStreamObserver.java:52)
	at org.apache.servicecomb.saga.omega.connector.grpc.saga.GrpcCompensateStreamObserver.onNext(GrpcCompensateStreamObserver.java:31)
	at io.grpc.stub.ClientCalls$StreamObserverToCallListenerAdapter.onMessage(ClientCalls.java:407)
	at io.grpc.ForwardingClientCallListener.onMessage(ForwardingClientCallListener.java:33)
	at io.grpc.ForwardingClientCallListener.onMessage(ForwardingClientCallListener.java:33)
	at io.grpc.internal.ClientCallImpl$ClientStreamListenerImpl$1MessagesAvailable.runInContext(ClientCallImpl.java:519)
	... 5 common frames omitted
2018-11-28 10:56:08:008 [pool-4-thread-1] [INFO ] o.a.s.s.o.c.g.c.PushBackReconnectRunnable >> Retry connecting to alpha at 127.0.0.1:8080
2018-11-28 10:56:08:008 [pool-4-thread-1] [ERROR] o.a.s.s.o.c.g.c.PushBackReconnectRunnable >> Failed to reconnect to alpha at 127.0.0.1:8080
io.grpc.StatusRuntimeException: UNKNOWN
	at io.grpc.stub.ClientCalls.toStatusRuntimeException(ClientCalls.java:222)
	at io.grpc.stub.ClientCalls.getUnchecked(ClientCalls.java:203)
	at io.grpc.stub.ClientCalls.blockingUnaryCall(ClientCalls.java:132)
	at org.apache.servicecomb.saga.pack.contract.grpc.TxEventServiceGrpc$TxEventServiceBlockingStub.onDisconnected(TxEventServiceGrpc.java:280)
	at org.apache.servicecomb.saga.omega.connector.grpc.saga.GrpcSagaClientMessageSender.onDisconnected(GrpcSagaClientMessageSender.java:75)
	at org.apache.servicecomb.saga.omega.connector.grpc.core.PushBackReconnectRunnable.run(PushBackReconnectRunnable.java:52)
	at org.apache.servicecomb.saga.omega.connector.grpc.core.PendingTaskRunner$1.run(PendingTaskRunner.java:44)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.runAndReset(FutureTask.java:308)
	at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.access$301(ScheduledThreadPoolExecutor.java:180)
	at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:294)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
 ```
 alpha服务的错误栈
 ```
 org.eclipse.persistence.exceptions.DatabaseException: 
 Internal Exception: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry '354' for key 'eventId'
 Error Code: 1062
 Call: INSERT INTO Command (COMPENSATIONMETHOD, EVENTID, GLOBALTXID, INSTANCEID, LASTMODIFIED, LOCALTXID, PARENTTXID, PAYLOADS, SERVICENAME, STATUS, VERSION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
 	bind => [11 parameters bound]
 Query: InsertObjectQuery(Command{eventId=354, serviceName='saga-server', instanceId='saga-server-192.168.56.1', globalTxId='3bedb7e5-5555-4fab-b2e5-d52731522194', localTxId='2a30e122-ed2e-421a-9d7d-9b1d258b336e', parentTxId='3bedb7e5-5555-4fab-b2e5-d52731522194', compensationMethod='public int com.yanwu.demo.saga.server.service.impl.SagaServerServiceImpl.createRollback(com.yanwu.demo.pojo.pojo.DemoClientPojo)'})
 	at org.eclipse.persistence.exceptions.DatabaseException.sqlException(DatabaseException.java:331) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.executeDirectNoSelect(DatabaseAccessor.java:905) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.executeNoSelect(DatabaseAccessor.java:967) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.basicExecuteCall(DatabaseAccessor.java:637) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.executeCall(DatabaseAccessor.java:564) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.sessions.AbstractSession.basicExecuteCall(AbstractSession.java:2093) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.sessions.server.ClientSession.executeCall(ClientSession.java:309) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.queries.DatasourceCallQueryMechanism.executeCall(DatasourceCallQueryMechanism.java:270) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.queries.DatasourceCallQueryMechanism.executeCall(DatasourceCallQueryMechanism.java:256) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.queries.DatasourceCallQueryMechanism.insertObject(DatasourceCallQueryMechanism.java:405) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.queries.StatementQueryMechanism.insertObject(StatementQueryMechanism.java:165) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.queries.StatementQueryMechanism.insertObject(StatementQueryMechanism.java:180) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.queries.DatabaseQueryMechanism.insertObjectForWrite(DatabaseQueryMechanism.java:502) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.queries.InsertObjectQuery.executeCommit(InsertObjectQuery.java:80) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.queries.InsertObjectQuery.executeCommitWithChangeSet(InsertObjectQuery.java:90) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.queries.DatabaseQueryMechanism.executeWriteWithChangeSet(DatabaseQueryMechanism.java:314) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.queries.WriteObjectQuery.executeDatabaseQuery(WriteObjectQuery.java:58) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.queries.DatabaseQuery.execute(DatabaseQuery.java:911) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.queries.DatabaseQuery.executeInUnitOfWork(DatabaseQuery.java:810) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.queries.ObjectLevelModifyQuery.executeInUnitOfWorkObjectLevelModifyQuery(ObjectLevelModifyQuery.java:108) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.queries.ObjectLevelModifyQuery.executeInUnitOfWork(ObjectLevelModifyQuery.java:85) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.sessions.UnitOfWorkImpl.internalExecuteQuery(UnitOfWorkImpl.java:2979) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.sessions.AbstractSession.executeQuery(AbstractSession.java:1892) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.sessions.AbstractSession.executeQuery(AbstractSession.java:1874) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.sessions.AbstractSession.executeQuery(AbstractSession.java:1824) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.sessions.CommitManager.commitNewObjectsForClassWithChangeSet(CommitManager.java:227) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.sessions.CommitManager.commitAllObjectsWithChangeSet(CommitManager.java:126) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.sessions.AbstractSession.writeAllObjectsWithChangeSet(AbstractSession.java:4384) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.sessions.UnitOfWorkImpl.commitToDatabase(UnitOfWorkImpl.java:1491) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.sessions.UnitOfWorkImpl.commitToDatabaseWithChangeSet(UnitOfWorkImpl.java:1581) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.sessions.RepeatableWriteUnitOfWork.commitRootUnitOfWork(RepeatableWriteUnitOfWork.java:278) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.sessions.UnitOfWorkImpl.commitAndResume(UnitOfWorkImpl.java:1218) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	at org.eclipse.persistence.internal.jpa.transaction.EntityTransactionImpl.commit(EntityTransactionImpl.java:134) ~[org.eclipse.persistence.jpa-2.7.1.jar:?]
 	at org.springframework.orm.jpa.JpaTransactionManager.doCommit(JpaTransactionManager.java:517) ~[spring-orm-4.3.18.RELEASE.jar:4.3.18.RELEASE]
 	at org.springframework.transaction.support.AbstractPlatformTransactionManager.processCommit(AbstractPlatformTransactionManager.java:765) ~[spring-tx-4.3.18.RELEASE.jar:4.3.18.RELEASE]
 	at org.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:734) ~[spring-tx-4.3.18.RELEASE.jar:4.3.18.RELEASE]
 	at org.springframework.transaction.interceptor.TransactionAspectSupport.commitTransactionAfterReturning(TransactionAspectSupport.java:518) ~[spring-tx-4.3.18.RELEASE.jar:4.3.18.RELEASE]
 	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:292) ~[spring-tx-4.3.18.RELEASE.jar:4.3.18.RELEASE]
 	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:96) ~[spring-tx-4.3.18.RELEASE.jar:4.3.18.RELEASE]
 	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) ~[spring-aop-4.3.18.RELEASE.jar:4.3.18.RELEASE]
 	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:136) ~[spring-tx-4.3.18.RELEASE.jar:4.3.18.RELEASE]
 	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) ~[spring-aop-4.3.18.RELEASE.jar:4.3.18.RELEASE]
 	at org.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:133) ~[spring-data-jpa-1.11.13.RELEASE.jar:?]
 	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) ~[spring-aop-4.3.18.RELEASE.jar:4.3.18.RELEASE]
 	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92) ~[spring-aop-4.3.18.RELEASE.jar:4.3.18.RELEASE]
 	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) ~[spring-aop-4.3.18.RELEASE.jar:4.3.18.RELEASE]
 	at org.springframework.data.repository.core.support.SurroundingTransactionDetectorMethodInterceptor.invoke(SurroundingTransactionDetectorMethodInterceptor.java:57) ~[spring-data-commons-1.13.13.RELEASE.jar:?]
 	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179) ~[spring-aop-4.3.18.RELEASE.jar:4.3.18.RELEASE]
 	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:213) ~[spring-aop-4.3.18.RELEASE.jar:4.3.18.RELEASE]
 	at com.sun.proxy.$Proxy116.save(Unknown Source) ~[?:?]
 	at org.apache.servicecomb.saga.alpha.server.SpringCommandRepository.saveCompensationCommands(SpringCommandRepository.java:67) ~[classes/:?]
 	at org.apache.servicecomb.saga.alpha.server.SpringCommandRepository$$FastClassBySpringCGLIB$$20938f25.invoke(<generated>) ~[classes/:?]
 	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204) ~[spring-core-4.3.18.RELEASE.jar:4.3.18.RELEASE]
 	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:667) ~[spring-aop-4.3.18.RELEASE.jar:4.3.18.RELEASE]
 	at org.apache.servicecomb.saga.alpha.server.SpringCommandRepository$$EnhancerBySpringCGLIB$$e958a331.saveCompensationCommands(<generated>) ~[classes/:?]
 	at org.apache.servicecomb.saga.alpha.core.EventScanner.lambda$saveUncompensatedEventsToCommands$2(EventScanner.java:114) ~[classes/:?]
 	at java.util.Vector.forEach(Vector.java:1275) ~[?:1.8.0_191]
 	at org.apache.servicecomb.saga.alpha.core.EventScanner.saveUncompensatedEventsToCommands(EventScanner.java:111) ~[classes/:?]
 	at org.apache.servicecomb.saga.alpha.core.EventScanner.lambda$pollEvents$0(EventScanner.java:84) ~[classes/:?]
 	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511) ~[?:1.8.0_191]
 	at java.util.concurrent.FutureTask.runAndReset(FutureTask.java:308) ~[?:1.8.0_191]
 	at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.access$301(ScheduledThreadPoolExecutor.java:180) ~[?:1.8.0_191]
 	at java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:294) ~[?:1.8.0_191]
 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) [?:1.8.0_191]
 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) [?:1.8.0_191]
 	at java.lang.Thread.run(Thread.java:748) [?:1.8.0_191]
 Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry '354' for key 'eventId'
 	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) ~[?:1.8.0_191]
 	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62) ~[?:1.8.0_191]
 	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) ~[?:1.8.0_191]
 	at java.lang.reflect.Constructor.newInstance(Constructor.java:423) ~[?:1.8.0_191]
 	at com.mysql.jdbc.Util.handleNewInstance(Util.java:425) ~[mysql-connector-java-5.1.44.jar:5.1.44]
 	at com.mysql.jdbc.Util.getInstance(Util.java:408) ~[mysql-connector-java-5.1.44.jar:5.1.44]
 	at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:935) ~[mysql-connector-java-5.1.44.jar:5.1.44]
 	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3973) ~[mysql-connector-java-5.1.44.jar:5.1.44]
 	at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:3909) ~[mysql-connector-java-5.1.44.jar:5.1.44]
 	at com.mysql.jdbc.MysqlIO.sendCommand(MysqlIO.java:2527) ~[mysql-connector-java-5.1.44.jar:5.1.44]
 	at com.mysql.jdbc.MysqlIO.sqlQueryDirect(MysqlIO.java:2680) ~[mysql-connector-java-5.1.44.jar:5.1.44]
 	at com.mysql.jdbc.ConnectionImpl.execSQL(ConnectionImpl.java:2487) ~[mysql-connector-java-5.1.44.jar:5.1.44]
 	at com.mysql.jdbc.PreparedStatement.executeInternal(PreparedStatement.java:1858) ~[mysql-connector-java-5.1.44.jar:5.1.44]
 	at com.mysql.jdbc.PreparedStatement.executeUpdateInternal(PreparedStatement.java:2079) ~[mysql-connector-java-5.1.44.jar:5.1.44]
 	at com.mysql.jdbc.PreparedStatement.executeUpdateInternal(PreparedStatement.java:2013) ~[mysql-connector-java-5.1.44.jar:5.1.44]
 	at com.mysql.jdbc.PreparedStatement.executeLargeUpdate(PreparedStatement.java:5104) ~[mysql-connector-java-5.1.44.jar:5.1.44]
 	at com.mysql.jdbc.PreparedStatement.executeUpdate(PreparedStatement.java:1998) ~[mysql-connector-java-5.1.44.jar:5.1.44]
 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[?:1.8.0_191]
 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[?:1.8.0_191]
 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[?:1.8.0_191]
 	at java.lang.reflect.Method.invoke(Method.java:498) ~[?:1.8.0_191]
 	at org.apache.tomcat.jdbc.pool.StatementFacade$StatementProxy.invoke(StatementFacade.java:114) ~[tomcat-jdbc-8.5.31.jar:?]
 	at com.sun.proxy.$Proxy119.executeUpdate(Unknown Source) ~[?:?]
 	at org.eclipse.persistence.internal.databaseaccess.DatabaseAccessor.executeDirectNoSelect(DatabaseAccessor.java:895) ~[org.eclipse.persistence.core-2.7.1.jar:?]
 	... 64 more
 10:54:41.853 [pool-3-thread-1] WARN  org.apache.servicecomb.saga.alpha.server.SpringCommandRepository - Failed to save some command Command{eventId=354, serviceName='saga-server', instanceId='saga-server-192.168.56.1', globalTxId='3bedb7e5-5555-4fab-b2e5-d52731522194', localTxId='2a30e122-ed2e-421a-9d7d-9b1d258b336e', parentTxId='3bedb7e5-5555-4fab-b2e5-d52731522194', compensationMethod='public int com.yanwu.demo.saga.server.service.impl.SagaServerServiceImpl.createRollback(com.yanwu.demo.pojo.pojo.DemoClientPojo)'}
 10:54:41.853 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.server.SpringCommandRepository - Saved compensation command Command{eventId=354, serviceName='saga-server', instanceId='saga-server-192.168.56.1', globalTxId='3bedb7e5-5555-4fab-b2e5-d52731522194', localTxId='2a30e122-ed2e-421a-9d7d-9b1d258b336e', parentTxId='3bedb7e5-5555-4fab-b2e5-d52731522194', compensationMethod='public int com.yanwu.demo.saga.server.service.impl.SagaServerServiceImpl.createRollback(com.yanwu.demo.pojo.pojo.DemoClientPojo)'}
 10:54:41.862 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.core.EventScanner - Found compensated event TxEvent{surrogateId=325, serviceName='base', instanceId='base-192.168.56.1', creationTime=Tue Nov 27 15:32:37 CST 2018, globalTxId='704fd181-ff9a-4a9c-b623-2e44daf17f41', localTxId='e056f933-520c-41f9-b8f7-082b850fc593', parentTxId='704fd181-ff9a-4a9c-b623-2e44daf17f41', type='TxCompensatedEvent', compensationMethod='public com.hoolink.sdk.bo.BackBO com.hoolink.base.build.impl.crud.SagaDemoImpl.createCancel(com.hoolink.sdk.bo.rpc.SagaDemoParamBO)', expiryTime=Fri Dec 31 08:00:00 CST 9999, retryMethod='', retries=0}
 10:54:41.888 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.core.EventScanner - Transaction with globalTxId 704fd181-ff9a-4a9c-b623-2e44daf17f41 and localTxId e056f933-520c-41f9-b8f7-082b850fc593 was compensated
 10:54:41.946 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.core.EventScanner - Marked end of transaction with globalTxId 704fd181-ff9a-4a9c-b623-2e44daf17f41
 10:54:42.509 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.core.EventScanner - Found compensated event TxEvent{surrogateId=335, serviceName='hotel', instanceId='hotel-192.168.56.1', creationTime=Tue Nov 27 16:12:06 CST 2018, globalTxId='a7c96474-564b-4bba-b63d-0df0d309f4b5', localTxId='f698caaa-e98f-42c3-b041-d31e02b23ed5', parentTxId='a7c96474-564b-4bba-b63d-0df0d309f4b5', type='TxCompensatedEvent', compensationMethod='void org.apache.servicecomb.saga.demo.scb.hotel.HotelBookingService.cancel(org.apache.servicecomb.saga.demo.scb.hotel.HotelBooking)', expiryTime=Fri Dec 31 08:00:00 CST 9999, retryMethod='', retries=0}
 10:54:42.545 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.core.EventScanner - Transaction with globalTxId a7c96474-564b-4bba-b63d-0df0d309f4b5 and localTxId f698caaa-e98f-42c3-b041-d31e02b23ed5 was compensated
 10:54:42.603 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.core.EventScanner - Marked end of transaction with globalTxId a7c96474-564b-4bba-b63d-0df0d309f4b5
 10:54:43.182 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.core.EventScanner - Found compensated event TxEvent{surrogateId=336, serviceName='car', instanceId='car-192.168.56.1', creationTime=Tue Nov 27 16:12:07 CST 2018, globalTxId='a7c96474-564b-4bba-b63d-0df0d309f4b5', localTxId='32b256ca-c4ed-49bc-90c2-7a66d6d7d399', parentTxId='a7c96474-564b-4bba-b63d-0df0d309f4b5', type='TxCompensatedEvent', compensationMethod='public void org.apache.servicecomb.saga.demo.scb.car.CarBookingService.cancel(org.apache.servicecomb.saga.demo.scb.car.entity.Book,org.apache.servicecomb.saga.demo.scb.car.entity.Book)', expiryTime=Fri Dec 31 08:00:00 CST 9999, retryMethod='', retries=0}
 10:54:43.213 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.core.EventScanner - Transaction with globalTxId a7c96474-564b-4bba-b63d-0df0d309f4b5 and localTxId 32b256ca-c4ed-49bc-90c2-7a66d6d7d399 was compensated
 10:54:43.245 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.core.EventScanner - Marked end of transaction with globalTxId a7c96474-564b-4bba-b63d-0df0d309f4b5
 10:54:43.802 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.core.EventScanner - Found compensated event TxEvent{surrogateId=344, serviceName='hotel', instanceId='hotel-192.168.56.1', creationTime=Tue Nov 27 16:17:53 CST 2018, globalTxId='8c18d202-5f54-4381-ab11-ef38b34e5aa7', localTxId='2df4fdaf-d777-4fb6-83c2-e1cd98e9c575', parentTxId='8c18d202-5f54-4381-ab11-ef38b34e5aa7', type='TxCompensatedEvent', compensationMethod='void org.apache.servicecomb.saga.demo.scb.hotel.HotelBookingService.cancel(org.apache.servicecomb.saga.demo.scb.hotel.HotelBooking)', expiryTime=Fri Dec 31 08:00:00 CST 9999, retryMethod='', retries=0}
 10:54:43.854 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.core.EventScanner - Transaction with globalTxId 8c18d202-5f54-4381-ab11-ef38b34e5aa7 and localTxId 2df4fdaf-d777-4fb6-83c2-e1cd98e9c575 was compensated
 10:54:43.903 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.core.EventScanner - Marked end of transaction with globalTxId 8c18d202-5f54-4381-ab11-ef38b34e5aa7
 10:54:44.466 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.core.EventScanner - Found compensated event TxEvent{surrogateId=345, serviceName='car', instanceId='car-192.168.56.1', creationTime=Tue Nov 27 16:17:54 CST 2018, globalTxId='8c18d202-5f54-4381-ab11-ef38b34e5aa7', localTxId='9bd02862-1d42-4d5d-9917-23ebb2a166c9', parentTxId='8c18d202-5f54-4381-ab11-ef38b34e5aa7', type='TxCompensatedEvent', compensationMethod='public void org.apache2.servicecomb.saga.demo.scb.car.CarBookingService.cancel(org.apache2.servicecomb.saga.demo.scb.car.entity.Book,org.apache2.servicecomb.saga.demo.scb.car.entity.Book)', expiryTime=Fri Dec 31 08:00:00 CST 9999, retryMethod='', retries=0}
 10:54:44.525 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.core.EventScanner - Transaction with globalTxId 8c18d202-5f54-4381-ab11-ef38b34e5aa7 and localTxId 9bd02862-1d42-4d5d-9917-23ebb2a166c9 was compensated
 10:54:44.590 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.core.EventScanner - Marked end of transaction with globalTxId 8c18d202-5f54-4381-ab11-ef38b34e5aa7
 10:55:25.382 [grpc-default-executor-0] INFO  org.apache.servicecomb.saga.alpha.server.tcc.GrpcTccEventService - Established connection service [saga-client] instanceId [saga-client-192.168.56.1].
 10:55:40.566 [grpc-default-executor-0] INFO  org.apache.servicecomb.saga.alpha.server.tcc.GrpcTccEventService - Established connection service [saga-server] instanceId [saga-server-192.168.56.1].
 10:55:50.023 [grpc-default-executor-0] INFO  org.apache.servicecomb.saga.alpha.server.tcc.GrpcTccEventService - Established connection service [saga-copy] instanceId [saga-copy-192.168.56.1].
 10:56:08.200 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.core.EventScanner - Found uncompensated event TxEvent{surrogateId=367, serviceName='saga-server', instanceId='saga-server-192.168.56.1', creationTime=Wed Nov 28 10:56:07 CST 2018, globalTxId='e45fab06-e4a2-4e7d-9c56-6937776417a5', localTxId='edfd9dc8-ab1b-44f3-905e-420ac170a01f', parentTxId='e45fab06-e4a2-4e7d-9c56-6937776417a5', type='TxEndedEvent', compensationMethod='public int com.yanwu.demo.saga.server.service.impl.SagaServerServiceImpl.createRollback(com.yanwu.demo.pojo.pojo.DemoClientPojo)', expiryTime=Fri Dec 31 08:00:00 CST 9999, retryMethod='', retries=0}
 10:56:08.201 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.server.SpringCommandRepository - Saving compensation command Command{eventId=366, serviceName='saga-server', instanceId='saga-server-192.168.56.1', globalTxId='e45fab06-e4a2-4e7d-9c56-6937776417a5', localTxId='edfd9dc8-ab1b-44f3-905e-420ac170a01f', parentTxId='e45fab06-e4a2-4e7d-9c56-6937776417a5', compensationMethod='public int com.yanwu.demo.saga.server.service.impl.SagaServerServiceImpl.createRollback(com.yanwu.demo.pojo.pojo.DemoClientPojo)'}
 10:56:08.266 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.server.SpringCommandRepository - Saved compensation command Command{eventId=366, serviceName='saga-server', instanceId='saga-server-192.168.56.1', globalTxId='e45fab06-e4a2-4e7d-9c56-6937776417a5', localTxId='edfd9dc8-ab1b-44f3-905e-420ac170a01f', parentTxId='e45fab06-e4a2-4e7d-9c56-6937776417a5', compensationMethod='public int com.yanwu.demo.saga.server.service.impl.SagaServerServiceImpl.createRollback(com.yanwu.demo.pojo.pojo.DemoClientPojo)'}
 10:56:08.333 [pool-3-thread-1] INFO  org.apache.servicecomb.saga.alpha.core.EventScanner - Compensating transaction with globalTxId e45fab06-e4a2-4e7d-9c56-6937776417a5 and localTxId edfd9dc8-ab1b-44f3-905e-420ac170a01f
 10:56:08.365 [grpc-default-executor-1] ERROR io.grpc.internal.SerializingExecutor - Exception while executing runnable io.grpc.internal.ServerImpl$JumpToApplicationThreadServerStreamListener$1HalfClosed@5dfce430
 io.grpc.StatusRuntimeException: CANCELLED: call already cancelled
 	at io.grpc.Status.asRuntimeException(Status.java:517) ~[grpc-core-1.14.0.jar:1.14.0]
 	at io.grpc.stub.ServerCalls$ServerCallStreamObserverImpl.onCompleted(ServerCalls.java:356) ~[grpc-stub-1.14.0.jar:1.14.0]
 	at org.apache.servicecomb.saga.alpha.server.GrpcOmegaCallback.disconnect(GrpcOmegaCallback.java:53) ~[classes/:?]
 	at org.apache.servicecomb.saga.alpha.server.GrpcTxEventEndpointImpl.onDisconnected(GrpcTxEventEndpointImpl.java:74) ~[classes/:?]
 	at org.apache.servicecomb.saga.pack.contract.grpc.TxEventServiceGrpc$MethodHandlers.invoke(TxEventServiceGrpc.java:350) ~[pack-contract-grpc-0.3.0-SNAPSHOT.jar:0.3.0-SNAPSHOT]
 	at io.grpc.stub.ServerCalls$UnaryServerCallHandler$UnaryServerCallListener.onHalfClose(ServerCalls.java:171) ~[grpc-stub-1.14.0.jar:1.14.0]
 	at io.grpc.internal.ServerCallImpl$ServerStreamListenerImpl.halfClosed(ServerCallImpl.java:283) ~[grpc-core-1.14.0.jar:1.14.0]
 	at io.grpc.internal.ServerImpl$JumpToApplicationThreadServerStreamListener$1HalfClosed.runInContext(ServerImpl.java:707) ~[grpc-core-1.14.0.jar:1.14.0]
 	at io.grpc.internal.ContextRunnable.run(ContextRunnable.java:37) ~[grpc-core-1.14.0.jar:1.14.0]
 	at io.grpc.internal.SerializingExecutor.run(SerializingExecutor.java:123) ~[grpc-core-1.14.0.jar:1.14.0]
 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) [?:1.8.0_191]
 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) [?:1.8.0_191]
 	at java.lang.Thread.run(Thread.java:748) [?:1.8.0_191]
```
 
 
当alpha服务，导入
 ```
 <dependency>
            <groupId>com.yanwu.demo.pojo</groupId>
            <artifactId>pojo</artifactId>
            <version>1.0-SNAPSHOT</version>
 </dependency>
 ```
      
之后问题解决saga正常使用