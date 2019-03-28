package com.hxf.mall.bean;

import java.util.Date;

public class T_MALL_PRODUCT_VERSION {
	private int id;//编号
	private String shp_bb;//版本
	private int shp_id;//商品id
	private String shfqy;
	private Date chjshj;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShp_bb() {
		return shp_bb;
	}

	public void setShp_bb(String shp_bb) {
		this.shp_bb = shp_bb;
	}

	public int getShp_id() {
		return shp_id;
	}

	public void setShp_id(int shp_id) {
		this.shp_id = shp_id;
	}

	public String getShfqy() {
		return shfqy;
	}

	public void setShfqy(String shfqy) {
		this.shfqy = shfqy;
	}

	public Date getChjshj() {
		return chjshj;
	}

	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}
}
