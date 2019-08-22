package com.hbase.bean;

import java.io.Serializable;

public class FlightInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String flno; /* 航班号 */
	
	private String sobt; /* 记起时间 */
	
	private String sibt; /* 记达时间 */
	
	private String eobt; /* 预起时间 */
	
	private String eibt; /* 预达时间 */
	
	private String eldt; /* 预计落地时间 */
	
	private String cobt; /* 计算撤轮挡时间 */
	
	private String ctot; /* 计算起飞时间 */
	
	private String atot; /* 实起时间 */
	
	private String aldt; /* 实到时间 */
	
	private String adeps; /* 计划起站 */
	
	private String adepe; /* 预计起站*/
	
	private String adepa; /* 实际起站 */
	
	private String adess; /* 计划到站 */
	
	private String adese; /* 预计到站 */
	
	private String adesa; /* 实际到站 */
	
	private Integer isExec;
	
	private String remk;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFlno() {
		return flno;
	}

	public void setFlno(String flno) {
		this.flno = flno;
	}

	public String getSobt() {
		return sobt;
	}

	public void setSobt(String sobt) {
		this.sobt = sobt;
	}

	public String getSibt() {
		return sibt;
	}

	public void setSibt(String sibt) {
		this.sibt = sibt;
	}

	public String getEobt() {
		return eobt;
	}

	public void setEobt(String eobt) {
		this.eobt = eobt;
	}

	public String getEibt() {
		return eibt;
	}

	public void setEibt(String eibt) {
		this.eibt = eibt;
	}

	public String getEldt() {
		return eldt;
	}

	public void setEldt(String eldt) {
		this.eldt = eldt;
	}

	public String getCobt() {
		return cobt;
	}

	public void setCobt(String cobt) {
		this.cobt = cobt;
	}

	public String getCtot() {
		return ctot;
	}

	public void setCtot(String ctot) {
		this.ctot = ctot;
	}

	public String getAtot() {
		return atot;
	}

	public void setAtot(String atot) {
		this.atot = atot;
	}

	public String getAldt() {
		return aldt;
	}

	public void setAldt(String aldt) {
		this.aldt = aldt;
	}

	public String getAdeps() {
		return adeps;
	}

	public void setAdeps(String adeps) {
		this.adeps = adeps;
	}

	public String getAdepe() {
		return adepe;
	}

	public void setAdepe(String adepe) {
		this.adepe = adepe;
	}

	public String getAdepa() {
		return adepa;
	}

	public void setAdepa(String adepa) {
		this.adepa = adepa;
	}

	public String getAdess() {
		return adess;
	}

	public void setAdess(String adess) {
		this.adess = adess;
	}

	public String getAdese() {
		return adese;
	}

	public void setAdese(String adese) {
		this.adese = adese;
	}

	public String getAdesa() {
		return adesa;
	}

	public void setAdesa(String adesa) {
		this.adesa = adesa;
	}

	public Integer getIsExec() {
		return isExec;
	}

	public void setIsExec(Integer isExec) {
		this.isExec = isExec;
	}

	public String getRemk() {
		return remk;
	}

	public void setRemk(String remk) {
		this.remk = remk;
	}
	
	
	
	
	
}
