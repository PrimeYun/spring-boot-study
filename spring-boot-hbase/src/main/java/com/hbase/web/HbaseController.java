package com.hbase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbase.common.Result;
import com.hbase.service.HBaseService;

@RestController
@RequestMapping("/hbase")
public class HbaseController {
	
	@Autowired
	private HBaseService hbaseService;
	
	@GetMapping("createTable")
	public Object createTable() throws Exception {
		hbaseService.createTable();
		return Result.success();
	}
	
	@GetMapping("insert")
	public Object insert() {
		hbaseService.insert(hbaseService.getInfo());
		return Result.success();
	}
	
	@GetMapping("select")
	public Object select() {
		return Result.success(hbaseService.select(hbaseService.getInfo().getId()));
	}
	
}
