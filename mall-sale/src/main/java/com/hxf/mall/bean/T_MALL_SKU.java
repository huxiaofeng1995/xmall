package com.hxf.mall.bean;

import java.math.BigDecimal;
import java.util.Date;

public class T_MALL_SKU {

	private Integer id;
	private Integer shp_id;
	private Integer kc;
	private BigDecimal jg;
	private Date chjshj;
	private String sku_mch;
	private String kcdz;
	private long sku_xl;

	public long getSku_xl() {
		return sku_xl;
	}

	public void setSku_xl(long sku_xl) {
		this.sku_xl = sku_xl;
	}

	public String getKcdz() {
		return kcdz;
	}

	public void setKcdz(String kcdz) {
		this.kcdz = kcdz;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getShp_id() {
		return shp_id;
	}

	public void setShp_id(Integer shp_id) {
		this.shp_id = shp_id;
	}

	public Integer getKc() {
		return kc;
	}

	public void setKc(Integer kc) {
		this.kc = kc;
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

	public BigDecimal getJg() {
		return jg;
	}

	public void setJg(BigDecimal jg) {
		this.jg = jg;
	}

}
