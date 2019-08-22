package com.hbase.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hbase.bean.FlightInfo;

@Service
public class HBaseService {

	private static final Logger log = LoggerFactory.getLogger(HBaseService.class);

	@Autowired
	private HbaseTemplate hbaseTemplate;

	private final String tableName = "flight_info";

	private final String[] columnFamily = { "flight_info", "flight_psth" };

	public void createTable() throws Exception {
		@SuppressWarnings({ "deprecation", "resource" })
		HBaseAdmin admin = new HBaseAdmin(hbaseTemplate.getConfiguration());
		if (admin.tableExists(Bytes.toBytes(tableName))) {
			admin.deleteTable(tableName);
		}

		HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
		for (String column : columnFamily) {
			tableDescriptor.addFamily(new HColumnDescriptor(column));
			// region maxSize
			// tableDescriptor.setMaxFileSize(maxFileSize)
		}
		// 分区
		// admin.splitRegion(regionName);
		admin.createTable(tableDescriptor);
		log.info("create table success");
	}

	public void insert(FlightInfo info) {
		
		hbaseTemplate.execute(tableName, table -> {
			Put p = new Put(Bytes.toBytes(info.getId()));
			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("FLNO"), Bytes.toBytes(info.getFlno()));
			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("SOBT"), Bytes.toBytes(info.getSobt()));
			table.put(p);
			log.info("数据存储成功");
			return info;
		});
	
	}
	
	public void insertBatch(List<FlightInfo> list) {
		
		hbaseTemplate.execute(tableName, table -> {
			List<Put> listPut = Lists.newArrayList();
			Put p;
			for (FlightInfo info : list) {
				p = new Put(Bytes.toBytes(info.getId()));
				p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("FLNO"), Bytes.toBytes(info.getFlno()));
				p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("SOBT"), Bytes.toBytes(info.getSobt()));
				listPut.add(p);
				
			}
			table.put(listPut);
			return list;
		});
		
	}

	public Object select(String id) {
		
		return hbaseTemplate.execute(tableName, table -> {
			Map<String, String> map = Maps.newHashMap();
			Result result = table.get(new Get(Bytes.toBytes(id)));
			map.put("flno", Bytes.toString(result.getValue(Bytes.toBytes("flight_info"), Bytes.toBytes("FLNO"))));
			map.put("sobt", Bytes.toString(result.getValue(Bytes.toBytes("flight_info"), Bytes.toBytes("SOBT"))));
			return map;
		});
		
	}

	public List<FlightInfo> selectByFlno(String flno) {

		List<Filter> filterList = new ArrayList<Filter>();
		filterList.add(new SingleColumnValueFilter(Bytes.toBytes("flight_info"), Bytes.toBytes("FLNO"), CompareOp.EQUAL,
				Bytes.toBytes(flno)));

		Scan scan = new Scan();
		scan.setFilter(new FilterList(filterList));
		log.info("scan 执行完毕");
		
		/**
		 * 一个RegionService中有一个HLog，记录每次操作，多个region
		 * 一个region对应多个store,一个列族对应一个或多个store
		 * store中包含mem store(Sorted Memory Buffer),store File(HFile)
		 */
		
		return hbaseTemplate.find(tableName, scan, (result, rowNum) -> {
			FlightInfo item = new FlightInfo();
			log.info(rowNum + "");
			item.setFlno(Bytes.toString(result.getValue(Bytes.toBytes("flight_info"), Bytes.toBytes("FLNO"))));
			return item;
		});

	}

	public List<FlightInfo> getAll() {

		return hbaseTemplate.find(tableName, "flight_info", new RowMapper<FlightInfo>() {

			@Override
			public FlightInfo mapRow(Result result, int rowNum) throws Exception {
				FlightInfo item = new FlightInfo();
				item.setFlno(Bytes.toString(result.getValue(Bytes.toBytes("flight_info"), Bytes.toBytes("FLNO"))));
				return item;
			}

		});
	}

	public List<FlightInfo> get(String startRow, String endRow) {
		Scan scan = new Scan();
		scan.withStartRow(Bytes.toBytes(startRow));
		scan.withStopRow(Bytes.toBytes(endRow));
		return hbaseTemplate.find(tableName, scan, new RowMapper<FlightInfo>() {

			@Override
			public FlightInfo mapRow(Result result, int rowNum) throws Exception {
				FlightInfo item = new FlightInfo();
				item.setFlno(Bytes.toString(result.getValue(Bytes.toBytes("flight_info"), Bytes.toBytes("FLNO"))));
				return item;
			}

		});
	}

	public FlightInfo getInfo() {
		FlightInfo info = new FlightInfo();
		info.setId("row1");
		info.setFlno("ABC");
		info.setSobt("20190801000000");
		return info;
	}

}
