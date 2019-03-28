package com.hxf.mall.bean;

import java.util.Date;

public class T_MALL_SKU_ATTR_VALUE {
	private Integer id;
	private Integer shxm_id;//属性名id
	private Integer shxzh_id;//属性值id
	private Integer shp_id;//商品id
	private String is_sku;//是否sku
	private Date chjshj;//创建时间
	private Integer sku_id;

	public T_MALL_SKU_ATTR_VALUE() {
	}

	public T_MALL_SKU_ATTR_VALUE(Integer shxm_id, Integer shxzh_id, Integer shp_id, Integer sku_id) {
		this.shxm_id = shxm_id;
		this.shxzh_id = shxzh_id;
		this.shp_id = shp_id;
		this.sku_id = sku_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getShxm_id() {
		return shxm_id;
	}

	public void setShxm_id(Integer shxm_id) {
		this.shxm_id = shxm_id;
	}

	public Integer getShxzh_id() {
		return shxzh_id;
	}

	public void setShxzh_id(Integer shxzh_id) {
		this.shxzh_id = shxzh_id;
	}

	public Integer getShp_id() {
		return shp_id;
	}

	public void setShp_id(Integer shp_id) {
		this.shp_id = shp_id;
	}

	public String getIs_sku() {
		return is_sku;
	}

	public void setIs_sku(String is_sku) {
		this.is_sku = is_sku;
	}

	public Date getChjshj() {
		return chjshj;
	}

	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}

	public Integer getSku_id() {
		return sku_id;
	}

	public void setSku_id(Integer sku_id) {
		this.sku_id = sku_id;
	}

}
