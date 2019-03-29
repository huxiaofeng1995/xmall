package com.hxf.mall.bean;

import java.util.Date;

public class T_MALL_PRODUCT_VERSION {
	private Integer id;//编号
	private String shp_bb;//版本
	private Integer shp_id;//商品id
	private String shfqy;
	private Date chjshj;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShp_bb() {
		return shp_bb;
	}

	public void setShp_bb(String shp_bb) {
		this.shp_bb = shp_bb;
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
