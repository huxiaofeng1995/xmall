package com.hxf.mall.bean;

import java.util.Date;

public class T_MALL_PRODUCT_SKU_INFO {
	private int sku_id;//编号
	private String shp_msh;
	private String shp_lb;//商品类别
	private int pp_id;
	private String shp_chc;//尺寸
	private String shpz_zhl;//重量
	private int shp_ys;
	private int shp_bb;
	private Date chjshj;

	public int getSku_id() {
		return sku_id;
	}

	public void setSku_id(int sku_id) {
		this.sku_id = sku_id;
	}

	public String getShp_msh() {
		return shp_msh;
	}

	public void setShp_msh(String shp_msh) {
		this.shp_msh = shp_msh;
	}

	public String getShp_lb() {
		return shp_lb;
	}

	public void setShp_lb(String shp_lb) {
		this.shp_lb = shp_lb;
	}

	public int getPp_id() {
		return pp_id;
	}

	public void setPp_id(int pp_id) {
		this.pp_id = pp_id;
	}

	public String getShp_chc() {
		return shp_chc;
	}

	public void setShp_chc(String shp_chc) {
		this.shp_chc = shp_chc;
	}

	public String getShpz_zhl() {
		return shpz_zhl;
	}

	public void setShpz_zhl(String shpz_zhl) {
		this.shpz_zhl = shpz_zhl;
	}

	public int getShp_ys() {
		return shp_ys;
	}

	public void setShp_ys(int shp_ys) {
		this.shp_ys = shp_ys;
	}

	public int getShp_bb() {
		return shp_bb;
	}

	public void setShp_bb(int shp_bb) {
		this.shp_bb = shp_bb;
	}

	public Date getChjshj() {
		return chjshj;
	}

	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}
}
