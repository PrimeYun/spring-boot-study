package com.hbase.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

@Configuration
public class HbaseConfig {
	
	@Value("${hbase.zookeeper.quorum}")
	private String zookeeperQuorum;
	
	@Value("${hbase.zookeeper.property.clientPort}")
	private String clientPort;
	
	@Value("${zookeeper.znode.parent}")
	private String parent;
	
	@Value("${hbase.rootdir}")
	private String rootDir;
	
	@Value("${hbase.master}")
	private String master;
	
	@Bean
	public HbaseTemplate hbaseTemplate() {
		org.apache.hadoop.conf.Configuration config = new org.apache.hadoop.conf.Configuration();
		config.set("hbase.zookeeper.quorum", zookeeperQuorum);
		config.set("hbase.zookeeper.port", clientPort);
		config.set("zookeeper.znode.parent", parent);
		config.set("hbase.master", master);
		config.set("hbase.rootdir", rootDir);
//		HbaseTemplate template = new HbaseTemplate(config);
//		// 当进行大量的读写时，AutoFlush要关闭，否则每执行一次Put就要向区域服务器发送一个请求。
//		template.setAutoFlush(false);
		return new HbaseTemplate(config);
	}
	
}
