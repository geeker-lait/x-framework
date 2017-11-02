# TasfeUid 分布式主键生成系统
TasfeUid是一个以snowflake算法为基础的轻量、高效的分布式主键生成系统。生成的ID是一个32/64位的 长整型，全局唯一，保持递增，相对有序。

# 特点
 -  轻量级，微服务部署，部署方便，不需要单独的服务器，以jar包的形式提供服务；
 -  一次编译，多处部署，无需多余配置，方便扩容与缩容；
 -  无延迟，客户端从redis队列中获取ID，服务端保证队列中始终有值，保证应用平滑不停顿；
 -  高并发；
 -  高可用，可部署多台TasfeUid服务，通过zookeeper集中管理服务的注册与退出；
 -  负载均衡，所有在同一zookeeper中管理的服务端，采用轮询方式创建ID。
 -  redis队列自动扩容与缩容，创建的ID存放在redis队列中供客户端提取，redis队列的大小根据服务端应用数量，自动扩大或缩小；
  
# 技术选型
 -  基于snowflake算法生成ID；
 -  基于netty实现远程通信；
 -  基于zookeeper实现服务注册、负载均衡；
 -  基于redis实现批量存储；
# 服务部署
 -  将本地ip地址配置到hosts文件中；
 -  部署tasfe-uid-server：java -jar tasfe-uid-server.jar -zookeeper127.0.0.1:2181 -redis127.0.0.1:6379


# 客户端
 -  添加tasfe-uid-client项目的依赖；
 -  new TasfeUid(zk,redis)；TasfeUid需要两个构造参数。其中，zk表示zookeeper的服务地址，redis表示redis的服务地址。详情请参考tasfe-uid-demo项目Main.java
 -  通过TasfeUid类的nextId()，获取id。