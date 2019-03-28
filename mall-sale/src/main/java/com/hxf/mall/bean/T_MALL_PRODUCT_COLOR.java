package com.hxf.mall.bean;

import java.util.Date;

public class T_MALL_PRODUCT_COLOR {
	private int id;//编号
	private String shp_ys;//颜色
	private int shp_id;//商品id
	private String shfqy;
	private Date chjshj;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShp_ys() {
		return shp_ys;
	}

	public void setShp_ys(String shp_ys) {
		this.shp_ys = shp_ys;
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
