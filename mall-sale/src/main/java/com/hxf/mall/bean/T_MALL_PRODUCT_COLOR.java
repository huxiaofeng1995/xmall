package com.hxf.mall.bean;

import java.util.Date;

public class T_MALL_PRODUCT_COLOR {
	private Integer id;//编号
	private String shp_ys;//颜色
	private Integer shp_id;//商品id
	private String shfqy;
	private Date chjshj;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShp_ys() {
		return shp_ys;
	}

	public void setShp_ys(String shp_ys) {
		this.shp_ys = shp_ys;
	}

	public Integer getShp_id() {
		return shp_id;
	}

	public void setShp_id(Integer shp_id) {
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
