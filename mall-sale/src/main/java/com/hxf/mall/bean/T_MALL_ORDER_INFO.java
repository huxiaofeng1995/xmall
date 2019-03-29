package com.hxf.mall.bean;

import java.util.Date;

public class T_MALL_ORDER_INFO {

	private Integer id;
	private Integer dd_id;
	private Integer sku_id;
	private Date chjshj;
	private String sku_mch;
	private String shp_tp;
	private double sku_jg;
	private Integer sku_shl;
	private String sku_kcdz;
	private Integer gwch_id;
	private Integer flow_id;

	public Integer getFlow_id() {
		return flow_id;
	}

	public void setFlow_id(Integer flow_id) {
		this.flow_id = flow_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDd_id() {
		return dd_id;
	}

	public void setDd_id(Integer dd_id) {
		this.dd_id = dd_id;
	}

	public Integer getSku_id() {
		return sku_id;
	}

	public void setSku_id(Integer sku_id) {
		this.sku_id = sku_id;
	}

	public Date getChjshj() {
		return chjshj;
	}

	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}

	public String getSku_mch() {
		return sku_mch;
	}

	public void setSku_mch(String sku_mch) {
		this.sku_mch = sku_mch;
	}

	public String getShp_tp() {
		return shp_tp;
	}

	public void setShp_tp(String shp_tp) {
		this.shp_tp = shp_tp;
	}

	public double getSku_jg() {
		return sku_jg;
	}

	public void setSku_jg(double sku_jg) {
		this.sku_jg = sku_jg;
	}

	public Integer getSku_shl() {
		return sku_shl;
	}

	public void setSku_shl(Integer sku_shl) {
		this.sku_shl = sku_shl;
	}

	public String getSku_kcdz() {
		return sku_kcdz;
	}

	public void setSku_kcdz(String sku_kcdz) {
		this.sku_kcdz = sku_kcdz;
	}

	public Integer getGwch_id() {
		return gwch_id;
	}

	public void setGwch_id(Integer gwch_id) {
		this.gwch_id = gwch_id;
	}

}
