//package com.hbase.config;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.client.Connection;
//import org.apache.hadoop.hbase.client.ConnectionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//
//public class HbaseConnectFactory {
//	
//	public static final Logger log = LoggerFactory.getLogger(HbaseConnectFactory.class);
//	
//	@Value("${hbase.zookeeper.quorum}")
//	private String zookeeperQuorum;
//	
//	@Value("${hbase.zookeeper.porperty.clientPort}")
//	private String clientPort;
//	
//	@Value("${zookeeper.znode.parent}")
//	private String parent;
//	
//	private static Configuration config = HBaseConfiguration.create();
//	
//	public static Connection connection;
//	
//	
//	public void afterPropertiesSet() throws Exception {
//		try {
//			config.set("hbase.zookeeper.quorum", zookeeperQuorum);
//			config.set("hbase.zookeeper.port", clientPort);
//			config.set("zookeeper.znode.parent", parent);
//			connection = ConnectionFactory.createConnection(config);
//			log.info("获取连接成功");
//		} catch (Exception e) {
//			log.info("获取连接失败");
//		}
//		
//	}
//
//}
