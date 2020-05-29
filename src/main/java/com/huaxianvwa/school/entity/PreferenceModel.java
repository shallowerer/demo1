package com.huaxianvwa.school.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PreferenceModel {

    @Id
    private Integer itemId;
	private Integer times;
	private String commodityName;
	private Integer commodityId;
	private Float price;
	private Integer allamount;
	private Float totalmoney;
	
	
	public PreferenceModel(Integer itemId, Integer times, String commodityName, Integer commodityId, Float price, Integer allamount,Float totalmoney ) {
        this.itemId = itemId;
        this.times = times;
        this.commodityName = commodityName;
        this.commodityId = commodityId;
        this.price = price;
        this.allamount = allamount;
        this.totalmoney = totalmoney;
    }
 
    public PreferenceModel() {
    }

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getAllamount() {
		return allamount;
	}

	public void setAllamount(Integer allamount) {
		this.allamount = allamount;
	}

	public Float getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(Float totalmoney) {
		this.totalmoney = totalmoney;
	}

	@Override
	public String toString() {
		return "PreferenceModel [itemId=" + itemId + ", times=" + times + ", commodityName=" + commodityName
				+ ", commodityId=" + commodityId + ", price=" + price + ", allamount=" + allamount + ", totalmoney="
				+ totalmoney + "]";
	}

	
	
}
