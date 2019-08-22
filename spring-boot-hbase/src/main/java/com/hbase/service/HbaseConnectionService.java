//package com.hbase.service;
//
//import java.util.Map;
//
//import org.apache.hadoop.hbase.HColumnDescriptor;
//import org.apache.hadoop.hbase.HTableDescriptor;
//import org.apache.hadoop.hbase.TableName;
//import org.apache.hadoop.hbase.client.Admin;
//import org.apache.hadoop.hbase.client.Get;
//import org.apache.hadoop.hbase.client.Put;
//import org.apache.hadoop.hbase.client.Result;
//import org.apache.hadoop.hbase.client.Table;
//import org.apache.hadoop.hbase.util.Bytes;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import com.google.common.collect.Maps;
//import com.hbase.bean.FlightInfo;
//import com.hbase.bean.TFlightPstn;
//import com.hbase.config.HbaseConnectFactory;
//
//@Service
//public class HbaseConnectionService {
//	
//	public static final Logger log = LoggerFactory.getLogger(HbaseConnectionService.class);
//	
//	private String tableName = "flight";
//	
//	private final String[] columnFamily = {"T_FLIGHT_INFO", "T_FLIGHT_PSTN"};
//	
//	public void createTable() {
//		try (Admin admin = HbaseConnectFactory.connection.getAdmin();) {
//			log.info("get admin success");
//			HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
//			for (String column : columnFamily) {
//				tableDescriptor.addFamily(new HColumnDescriptor(column));
//			}
//			admin.createTable(tableDescriptor);
//			log.info("create table success");
//		} catch(Exception e) {
//			log.info("create table failure");
//		}
//	}
//	
//	public Map<String, Object> insert() {
//		Map<String, Object> result = Maps.newHashMap();
//		try (Table table = HbaseConnectFactory.connection.getTable(TableName.valueOf(tableName))) {
//			FlightInfo info = HBaseService.getInfoInstance();
//			TFlightPstn entity = HBaseService.getPstnInstance();
//			Put p = new Put(Bytes.toBytes(info.getId()));
//			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("FLNO"), Bytes.toBytes(info.getFlno()));
//			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("SOBT"), Bytes.toBytes(info.getSobt()));
//			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("SIBT"), Bytes.toBytes(info.getSibt()));
//			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("EOBT"), Bytes.toBytes(info.getEobt()));
//			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("EIBT"), Bytes.toBytes(info.getEibt()));
//			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("ELDT"), Bytes.toBytes(info.getEldt()));
//			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("COBT"), Bytes.toBytes(info.getCobt()));
//			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("CTOT"), Bytes.toBytes(info.getCtot()));
//			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("ALDT"), Bytes.toBytes(info.getAldt()));
//			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("ATOT"), Bytes.toBytes(info.getAtot()));
//			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("ADEPA"), Bytes.toBytes(info.getAdepa()));
//			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("ADEPE"), Bytes.toBytes(info.getAdepe()));
//			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("ADEPS"), Bytes.toBytes(info.getAdeps()));
//			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("ADESA"), Bytes.toBytes(info.getAdesa()));
//			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("ADESE"), Bytes.toBytes(info.getAdese()));
//			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("ADESS"), Bytes.toBytes(info.getAdess()));
//			p.addColumn(Bytes.toBytes(columnFamily[0]), Bytes.toBytes("ISEXEC"), Bytes.toBytes(info.getIsExec()));
//			p.addColumn(Bytes.toBytes(columnFamily[1]), Bytes.toBytes("AIRPORT"), Bytes.toBytes(entity.getAirPort()));
//			p.addColumn(Bytes.toBytes(columnFamily[1]), Bytes.toBytes("A_OR_S"), Bytes.toBytes(entity.getaOrs()));
//			p.addColumn(Bytes.toBytes(columnFamily[1]), Bytes.toBytes("PSTH"), Bytes.toBytes(entity.getPsth()));
//			p.addColumn(Bytes.toBytes(columnFamily[1]), Bytes.toBytes("ABEGINTIME"), Bytes.toBytes(entity.getaBeginTime()));
//			p.addColumn(Bytes.toBytes(columnFamily[1]), Bytes.toBytes("AENDTIME"), Bytes.toBytes(entity.getaEndTime()));
//			p.addColumn(Bytes.toBytes(columnFamily[1]), Bytes.toBytes("SBEGINTIME"), Bytes.toBytes(entity.getsBeginTime()));
//			p.addColumn(Bytes.toBytes(columnFamily[1]), Bytes.toBytes("SENDTIME"), Bytes.toBytes(entity.getsEndTime()));
//			table.put(p);
//			result.put("FlightInfo", info);
//			result.put("TFlightPstn", entity);
//			log.info("数据存储成功:" + result);
//		} catch (Exception e) {
//			log.info("数据存储失败：" + e.getMessage());
//		}
//		return result;
//	}
//	
//	public Map<String, Object> selectByRowkey() {
//		Map<String, Object> map = Maps.newHashMap();
//		try (Table table = HbaseConnectFactory.connection.getTable(TableName.valueOf(tableName))) {
//			Get get = new Get(Bytes.toBytes(HBaseService.getInfoInstance().getId()));
//			Result result = table.get(get);
//			map.put("result", result);
//			log.info("数据查询成功：" + result);
//		} catch (Exception e) {
//			log.info("数据查询失败：" + e.getMessage());
//		}
//		return map;
//	}
//	
//}
