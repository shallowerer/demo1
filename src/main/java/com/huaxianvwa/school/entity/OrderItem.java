package com.huaxianvwa.school.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "order_item")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private Integer commodityId;
    private Integer amount;
    private Float price;
    private String commodityName;
    private Float lttAccount;
    
    
	@ManyToOne
    @JoinColumn(name="order_id")
    private CommodityOrder commodityOrder;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public Float getLttAccount() {
		return lttAccount;
	}
	public void setLttAccount(Float lttAccount) {
		this.lttAccount = lttAccount;
	}
	public CommodityOrder getCommodityOrder() {
		return commodityOrder;
	}
	public void setCommodityOrder(CommodityOrder commodityOrder) {
		this.commodityOrder = commodityOrder;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", commodityId=" + commodityId + ", amount=" + amount
				+ ", price=" + price + ", commodityName=" + commodityName + ", lttAccount=" + lttAccount
				+ ", commodityOrder=" + commodityOrder + "]";
	}

}
